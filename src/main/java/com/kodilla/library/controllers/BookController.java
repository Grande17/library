package com.kodilla.library.controllers;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.BookDto;
import com.kodilla.library.exceptions.BookNotFoundException;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
@Validated
public class BookController {
    private final BookDbService bookDbService;
    private final BookMapper bookMapper;


    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks(){
        List<Book> books = bookDbService.getAllBooks();
        return ResponseEntity.ok(bookMapper.mapToBookDtoList(books));
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable int id) throws BookNotFoundException {
        return ResponseEntity.ok(bookMapper.mapToBookDto(bookDbService.getBook(id)));

    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addBook(@RequestBody @Valid BookDto bookDto){
        Book book = bookMapper.mapToBook(bookDto);
        bookDbService.saveBook(book);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<BookDto> updateBook(@RequestBody @Valid  BookDto bookDto){
        Book book = bookMapper.mapToBook(bookDto);
        Book savedBook = bookDbService.saveBook(book);
        return ResponseEntity.ok(bookMapper.mapToBookDto(savedBook));
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id){
        bookDbService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "authorContains={contains}")
    public ResponseEntity<List<BookDto>> getBookByAuthorContains(@PathVariable String contains){
        List<Book> list = bookDbService.getBookByAuthorContains(contains);
        return ResponseEntity.ok(bookMapper.mapToBookDtoList(list));
    }
    @GetMapping(value = "titleContains={contains}")
    public ResponseEntity<List<BookDto>> getBookByTitleContains(@PathVariable String contains){
        List<Book> list = bookDbService.getBookByTitleContains(contains);
        return ResponseEntity.ok(bookMapper.mapToBookDtoList(list));
    }
    @GetMapping(value = "published={year}")
    public ResponseEntity<List<BookDto>> getByPublished(@PathVariable int year){
        List<Book> list = bookDbService.getByPublished(year);
        return ResponseEntity.ok(bookMapper.mapToBookDtoList(list));
    }


}
