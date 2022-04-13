package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;


    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void itShouldFindAllBooks() {
        //given
        List<Book> books =Arrays.asList( new Book("Title","Author",1999),
         new Book("Title2", "Author2",2000));

        repository.saveAll(books);
        //when
        List<Book> result = repository.findAll();
        //then
        assertEquals(2,result.size());
    }
    @Test
    void testFindAllMethodWhenResultIs0() {
        //given
        //when
        List<Book> result = repository.findAll();
        //then
        assertEquals(0,result.size());
    }

    @Test
    void findBookByIdWhenBookExists() {
        //given
        Book book = new Book("111","111",1999);
        repository.save(book);
        //when
        Optional<Book> result = repository.findById(book.getId());
        System.out.println(result);
        //then
        assertTrue(result.isPresent());
    }
    @Test
    void findBookByIdWhenBookNotExists(){
        //given
        Book book = new Book("111","111",1999);
        //when
        Optional<Book> result = repository.findById(2);
        //then
        assertFalse(result.isPresent());
    }


    @Test
    void findByAuthorContainsWhileAuthorExists() {
        //given
        Book book = new Book("To fast","Tomasz Krawczyk",1999);
        repository.save(book);
        //when
        List<Book> result = repository.findByAuthorContains("czyk");
        //then
        assertEquals(1,result.size());
    }
    @Test
    void findByAuthorContainsWhileAuthorDoesNotExists() {
        //given
        Book book = new Book("To fast","Tomasz Krawczyk",1999);
        repository.save(book);
        //when
        List<Book> result = repository.findByAuthorContains("kak");
        //then
        assertEquals(0,result.size());
    }

    @Test
    void findByTitleContainsWhileBookExists() {
        //given
        Book book = new Book("To fast","Tomasz Krawczyk",1999);
        repository.save(book);
        //when
        List<Book> result = repository.findByTitleContains("fast");
        //then
        assertEquals(1,result.size());
    }
    @Test
    void findByTitleContainsWhileBookDoesNotExists() {
        //given
        Book book = new Book("To fast","Tomasz Krawczyk",1999);
        repository.save(book);
        //when
        List<Book> result = repository.findByTitleContains("ker");
        //then
        assertEquals(0,result.size());
    }

    @Test
    void findByPublishedIfExists() {
        //given
        List<Book> books =Arrays.asList( new Book("Title","Author",1999),
                new Book("Title2", "Author2",2000));

        repository.saveAll(books);
        //when
        List<Book> result = repository.findByPublished(1999);
        //then
        assertEquals(1,result.size());
    }
    @Test
    void findByPublishedThatDoesNotExists(){
        //given
        List<Book> books =Arrays.asList( new Book("Title","Author",1999),
                new Book("Title2", "Author2",2000));

        repository.saveAll(books);
        //when
        List<Book> result = repository.findByPublished(2002);
        //then
        assertTrue(result.isEmpty());
    }

    @Test
    void findByPublishedAfterWhileExists() {
        //given
        List<Book> books =Arrays.asList( new Book("Title","Author",1999),
                new Book("Title2", "Author2",2000));

        repository.saveAll(books);
        //when
        List<Book> result = repository.findByPublishedAfter(1995);
        //then
        assertEquals(2,result.size());
    }
    @Test
    void findByPublishedAfterWhenDoesNotExists(){
        //given
        List<Book> books =Arrays.asList( new Book("Title","Author",1999),
                new Book("Title2", "Author2",2000));

        repository.saveAll(books);
        //when
        List<Book> result = repository.findByPublishedAfter(2002);
        //then
        assertTrue(result.isEmpty());
    }

    @Test
    void findByPublishedBeforeIfExists() {
        //given
        List<Book> books =Arrays.asList( new Book("Title","Author",1999),
                new Book("Title2", "Author2",2000));

        repository.saveAll(books);
        //when
        List<Book> result = repository.findByPublishedBefore(2005);
        //then
        assertEquals(2,result.size());
    }
}