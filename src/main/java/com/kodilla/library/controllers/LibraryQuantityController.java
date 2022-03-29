package com.kodilla.library.controllers;

import com.kodilla.library.Status;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.LibraryQuantity;
import com.kodilla.library.domain.dto.LibraryQuantityDto;
import com.kodilla.library.exceptions.LibraryQuantityNotFound;
import com.kodilla.library.exceptions.StatusNotFoundException;
import com.kodilla.library.mapper.LibraryQuantityMapper;
import com.kodilla.library.service.LibraryQuantityDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/qty")
@RequiredArgsConstructor
@Validated
public class LibraryQuantityController {
    private final LibraryQuantityDbService libraryQuantityDbService;
    private final LibraryQuantityMapper mapper;

    @GetMapping
    public ResponseEntity<List<LibraryQuantityDto>> getStock(){
        List<LibraryQuantity> list = libraryQuantityDbService.getAll();
        return ResponseEntity.ok(mapper.mapToLibraryQuantityDtoList(list));
    }
    @GetMapping(value = "test")
    public LibraryQuantityDto test(){
        return new LibraryQuantityDto(1,Status.AVAILABLE,new Book(1,"Title","Author",1999));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addLibraryQty(@RequestBody @Valid LibraryQuantityDto libraryQuantityDto){
        LibraryQuantity libraryQuantity = mapper.mapToLibraryQuantity(libraryQuantityDto);
        libraryQuantityDbService.saveLibraryQuantity(libraryQuantity);
        return ResponseEntity.ok().build();
    }
    @PutMapping(value = "update/{id}/{status}")
    public ResponseEntity<Void> updateStatus(@PathVariable int id, @PathVariable String status) throws LibraryQuantityNotFound, StatusNotFoundException {
        libraryQuantityDbService.updateStatusProcessor(id,status);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "titleContains={contains}")
    public ResponseEntity<Long> getAllAvailableByTitle(@PathVariable String contains){
        long result = libraryQuantityDbService.getAllAvailableByTitleProcessor(contains);
        return ResponseEntity.ok(result);
    }

}
