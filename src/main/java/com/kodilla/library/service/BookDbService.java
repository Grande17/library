package com.kodilla.library.service;

import com.kodilla.library.domain.Book;
import com.kodilla.library.exceptions.BookNotFoundException;
import com.kodilla.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookDbService {
    @Autowired
    private final BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book getBook(final int id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }
    public Book saveBook(final Book book){
        return bookRepository.save(book);
    }
    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }
    public List<Book> getBookByAuthorContains(String contains){
        return bookRepository.findByAuthorContains(contains);
    }
    public List<Book> getBookByTitleContains(String contains){
        return bookRepository.findByTitleContains(contains);
    }
    public List<Book> getByPublished(int year){
        return bookRepository.findByPublished(year);
    }
    public List<Book> getByPublishedAfter(int year){
        return bookRepository.findByPublishedAfter(year);
    }
    public List<Book> getByPublishedBefore(int year){
        return bookRepository.findByPublishedBefore(year);
    }
    public void deleteAll(){
        bookRepository.deleteAll();
    }


}
