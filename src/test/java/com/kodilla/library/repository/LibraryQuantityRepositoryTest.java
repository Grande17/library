package com.kodilla.library.repository;

import com.kodilla.library.enums.Status;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.LibraryQuantity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LibraryQuantityRepositoryTest {

    @Autowired
    private LibraryQuantityRepository repository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void findAllIfAnyExists() {
        //given
        Book b1 = new Book("Title", "Author",1999);
        Book b2 = new Book("T22","A22",2000);
        bookRepository.save(b1);
        bookRepository.save(b2);

        LibraryQuantity q1 = new LibraryQuantity( Status.AVAILABLE,
                        b1);
        LibraryQuantity q2 = new LibraryQuantity( Status.AVAILABLE,
                        b2);
        repository.save(q1);
        repository.save(q2);
        //when
        List<LibraryQuantity> result = repository.findAll();
        //then
        assertEquals(2,result.size());
    }
    @Test
    void findAllWhenNoResulExists(){
        //given
        //when
        List<LibraryQuantity>result = repository.findAll();
        //then
        assertTrue(result.isEmpty());
    }

    @Test
    void findByIdWhenExists() {
        //given
        Book b1 = new Book("Title", "Author",1999);
        Book b2 = new Book("T22","A22",2000);
        bookRepository.save(b1);
        bookRepository.save(b2);

        LibraryQuantity q1 = new LibraryQuantity( Status.AVAILABLE,
                        b1);
        LibraryQuantity q2 = new LibraryQuantity( Status.AVAILABLE,
                        b2);

        repository.save(q1);
        repository.save(q2);
        //when
        int id = q1.getId();
        Optional<LibraryQuantity>result = repository.findById(id);
        //then
        assertFalse(result.isEmpty());
        assertEquals(id,result.get().getId());

    }
    @Test
    void findByIdWhenDoesNotExists(){
        //given
        //when
        Optional<LibraryQuantity> result = repository.findById(1);
        //then
        assertTrue(result.isEmpty());
    }
}