package com.kodilla.library.service;

import com.kodilla.library.enums.Status;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Borrowed;
import com.kodilla.library.domain.LibraryQuantity;
import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dao.LibraryQuantityDao;
import com.kodilla.library.domain.dao.UserDao;
import com.kodilla.library.domain.dto.BorrowRequest;
import com.kodilla.library.domain.dto.BorrowedDto;
import com.kodilla.library.exceptions.BorrowedNotFound;
import com.kodilla.library.exceptions.LibraryQuantityNotFound;
import com.kodilla.library.exceptions.MaximumBooksBorrowedException;
import com.kodilla.library.exceptions.UserNotFoundException;
import com.kodilla.library.mapper.BorrowedMapper;
import com.kodilla.library.repository.BorrowedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowedDbService {
    private final BorrowedRepository repository;
    private final LibraryQuantityDao libraryQuantityDao;
    private final BorrowedMapper mapper;
    private  final UserDao userDao;

    public List<Borrowed> getAll(){
        return repository.findAll();
    }
    public Borrowed save(final Borrowed borrowed){
        return repository.save(borrowed);
    }
    public void deleteById(int id){
        repository.deleteById(id);
    }



    public ResponseEntity<Void> borrowProcessor(BorrowRequest borrowRequest) throws UserNotFoundException, LibraryQuantityNotFound, MaximumBooksBorrowedException {

        User user = userDao.findById(borrowRequest.getUserId()).orElseThrow(UserNotFoundException::new);
        if (getAllBooksBorrowedByUser(borrowRequest.getUserId()).size() >=3){
            throw new MaximumBooksBorrowedException();
        }
        List<LibraryQuantity> list = libraryQuantityDao.findByBookId(borrowRequest.getBookId()).stream()
                .filter(x->x.getStatus().equals(Status.AVAILABLE))
                .collect(Collectors.toList());


        LibraryQuantity libraryQuantity;
        if (list.size() > 0){
            libraryQuantity = list.get(0);
            Borrowed borrowed = mapper.mapToBorrowed(new BorrowedDto(
                    borrowRequest.getBorrowedDto().getId(),
                    libraryQuantity,
                    user,
                    LocalDate.now(),
                    LocalDate.now().plusDays(30)
            ));
            libraryQuantityDao.save(new LibraryQuantity(
                    borrowed.getLibraryQuantity().getId(),
                    Status.NOT_AVAILABLE,
                    new Book(libraryQuantity.getBook().getId(),
                            libraryQuantity.getBook().getTitle(),
                            libraryQuantity.getBook().getAuthor(),
                            libraryQuantity.getBook().getPublished())
            ));
            save(borrowed);
        }else {
            throw new LibraryQuantityNotFound();
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> returnProcessor (int id) throws BorrowedNotFound {
        Borrowed borrowed = repository.findById(id).orElseThrow(BorrowedNotFound::new);
        deleteById(id);

        libraryQuantityDao.save(new LibraryQuantity(
                borrowed.getLibraryQuantity().getId(),
                Status.AVAILABLE,
                borrowed.getLibraryQuantity().getBook()
        ));
        return ResponseEntity.ok().build();
    }

    public List<Borrowed> getAllBooksBorrowedByUser(int userId){
        List<Borrowed> result = getAll().stream()
                .filter(x->x.getUser().getId() == userId)
                .collect(Collectors.toList());
        return result;
    }
}
