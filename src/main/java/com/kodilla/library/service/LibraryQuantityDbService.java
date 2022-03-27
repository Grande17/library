package com.kodilla.library.service;

import com.kodilla.library.Status;
import com.kodilla.library.domain.LibraryQuantity;
import com.kodilla.library.domain.dao.LibraryQuantityDao;
import com.kodilla.library.domain.dto.LibraryQuantityDto;
import com.kodilla.library.exceptions.LibraryQuantityNotFound;
import com.kodilla.library.exceptions.StatusNotFoundException;
import com.kodilla.library.mapper.LibraryQuantityMapper;
import com.kodilla.library.repository.LibraryQuantityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryQuantityDbService {

    private final LibraryQuantityRepository repository;
    private final LibraryQuantityDao libraryQuantityDao;
    private final LibraryQuantityMapper mapper;

    public LibraryQuantity saveLibraryQuantity(final LibraryQuantity libraryQuantity){
        return repository.save(libraryQuantity);
    }
    public List<LibraryQuantity> getAll(){
        return repository.findAll();
    }
    public LibraryQuantity getById(int id) throws LibraryQuantityNotFound{
        return repository.findById(id).orElseThrow(LibraryQuantityNotFound::new);
    }

    public ResponseEntity<LibraryQuantityDto> updateStatusProcessor(int id, String status) throws LibraryQuantityNotFound, StatusNotFoundException{
        LibraryQuantity library = libraryQuantityDao.findById(id).orElseThrow(LibraryQuantityNotFound::new);
        LibraryQuantity saved;
        if(status.equalsIgnoreCase("available") || status.equalsIgnoreCase("damaged") || status.equalsIgnoreCase("not_available")) {

            if (status.equalsIgnoreCase(Status.AVAILABLE.toString())) {
                saved = libraryQuantityDao.save(new LibraryQuantity(library.getId(), Status.AVAILABLE, library.getBook()));
                return ResponseEntity.ok(mapper.mapToLibraryQuantityDto(saved));
            } else if (status.equalsIgnoreCase(Status.DAMAGED.toString())) {
                saved = libraryQuantityDao.save(new LibraryQuantity(library.getId(), Status.DAMAGED, library.getBook()));
                return ResponseEntity.ok(mapper.mapToLibraryQuantityDto(saved));
            } else if (status.equalsIgnoreCase(Status.NOT_AVAILABLE.toString())) {
                saved = libraryQuantityDao.save(new LibraryQuantity(library.getId(), Status.NOT_AVAILABLE, library.getBook()));
                return ResponseEntity.ok(mapper.mapToLibraryQuantityDto(saved));
            }
        }else throw new StatusNotFoundException();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
