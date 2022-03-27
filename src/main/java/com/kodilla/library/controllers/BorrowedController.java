package com.kodilla.library.controllers;

import com.kodilla.library.Status;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Borrowed;
import com.kodilla.library.domain.LibraryQuantity;
import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.BookDto;
import com.kodilla.library.domain.dto.BorrowRequest;
import com.kodilla.library.domain.dto.BorrowedDto;
import com.kodilla.library.exceptions.BorrowedNotFound;
import com.kodilla.library.exceptions.LibraryQuantityNotFound;
import com.kodilla.library.exceptions.UserNotFoundException;
import com.kodilla.library.mapper.BorrowedMapper;
import com.kodilla.library.service.BorrowedDbService;
import com.kodilla.library.service.LibraryQuantityDbService;
import com.kodilla.library.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/borrowed")
@RequiredArgsConstructor
@Validated
public class BorrowedController {

    private final BorrowedMapper mapper;
    private final BorrowedDbService service;

    @GetMapping(value = "test")
    public BorrowedDto checkAllBorrowed(){
        return new BorrowedDto(1,
                new LibraryQuantity(1, Status.AVAILABLE,
                        new Book(1,"title","author",1111)),
                new User(1,"Adam","Micki", LocalDate.of(2020,7,11)),
                LocalDate.now(),LocalDate.now().plusDays(30));
    }
    @GetMapping(value = "borrowrq")
    public BorrowRequest get(){
        return new BorrowRequest(new BorrowedDto(1,new LibraryQuantity(),new User(),LocalDate.now(),LocalDate.now().plusDays(30)),11,11);
    }
    @GetMapping
    public ResponseEntity<List<BorrowedDto>> checkAll(){
        List<Borrowed> list = service.getAll();
        return ResponseEntity.ok(mapper.mapToBorrowedDtoList(list));
    }
    @PostMapping(value = "new")
    public ResponseEntity<Void> borrow(@RequestBody @Valid BorrowRequest borrowRequest) throws LibraryQuantityNotFound, UserNotFoundException {
        service.borrowProcessor(borrowRequest);
       return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> returnBook(@PathVariable int id) throws BorrowedNotFound {
        service.returnProcessor(id);
        return ResponseEntity.ok().build();
    }

}
