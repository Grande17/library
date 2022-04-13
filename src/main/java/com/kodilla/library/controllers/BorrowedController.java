package com.kodilla.library.controllers;

import com.kodilla.library.domain.Borrowed;
import com.kodilla.library.domain.dto.BorrowRequest;
import com.kodilla.library.domain.dto.BorrowedDto;
import com.kodilla.library.exceptions.BorrowedNotFound;
import com.kodilla.library.exceptions.LibraryQuantityNotFound;
import com.kodilla.library.exceptions.MaximumBooksBorrowedException;
import com.kodilla.library.exceptions.UserNotFoundException;
import com.kodilla.library.mapper.BorrowedMapper;
import com.kodilla.library.service.BorrowedDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/borrowed")
@RequiredArgsConstructor
@Validated
public class BorrowedController {

    private final BorrowedMapper mapper;
    private final BorrowedDbService service;



    @GetMapping
    public ResponseEntity<List<BorrowedDto>> checkAll(){
        List<Borrowed> list = service.getAll();
        return ResponseEntity.ok(mapper.mapToBorrowedDtoList(list));
    }
    @PostMapping(value = "new")
    public ResponseEntity<Void> borrow(@RequestBody @Valid BorrowRequest borrowRequest) throws LibraryQuantityNotFound, UserNotFoundException, MaximumBooksBorrowedException {
        service.borrowProcessor(borrowRequest);
       return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "return/{id}")
    public ResponseEntity<Void> returnBook(@PathVariable int id) throws BorrowedNotFound {
        service.returnProcessor(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value ="userId/{id}" )
    public ResponseEntity<List<BorrowedDto>> getAllBooksBorrowedByUserWithId(@PathVariable int id){
        List<Borrowed> result =service.getAllBooksBorrowedByUser(id);
        return ResponseEntity.ok(mapper.mapToBorrowedDtoList(result));
    }

}
