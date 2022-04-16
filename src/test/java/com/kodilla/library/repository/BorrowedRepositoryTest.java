package com.kodilla.library.repository;

import com.kodilla.library.Status;
import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.Borrowed;
import com.kodilla.library.domain.LibraryQuantity;
import com.kodilla.library.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BorrowedRepositoryTest {

    @Autowired
    private  BorrowedRepository borrowedRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryQuantityRepository libraryQuantityRepository;


    @Test
    void findAllWhenBorrowedIsNotEmptyList() {
        //given
        User u1 = new User("Name1","Surname1","nnn","pass");
        User u2 = new User("Name2","Surname2","nnn","pass");
        userRepository.save(u1);
        userRepository.save(u2);
        Book b1 = new Book("Title1","Author1",2000);
        Book b2 = new Book("Title2","Author2",2001);
        bookRepository.save(b1);
        bookRepository.save(b2);
        LibraryQuantity l1 = new LibraryQuantity(Status.AVAILABLE,b1);
        LibraryQuantity l2 = new LibraryQuantity(Status.AVAILABLE,b2);
        libraryQuantityRepository.save(l1);
        libraryQuantityRepository.save(l2);
        Borrowed borrowed1 = new Borrowed(l1,u1,LocalDate.now());
        Borrowed borrowed2 = new Borrowed(l2,u2,LocalDate.now());
        borrowedRepository.save(borrowed1);
        borrowedRepository.save(borrowed2);
        //when
        List<Borrowed> result = borrowedRepository.findAll();
        //then
        assertFalse(result.isEmpty());
        assertEquals(2,result.size());
    }

    @Test
    void findAllWhenBorrowedIsEmpty(){
        //given
        //when
        List<Borrowed> result = borrowedRepository.findAll();
        //then
        assertTrue(result.isEmpty());
    }

    @Test
    void deleteById() {
        //given
        User u1 = new User("Name1","Surname1","nnn","pass");
        User u2 = new User("Name2","Surname2","nnn","pass");
        userRepository.save(u1);
        userRepository.save(u2);
        Book b1 = new Book("Title1","Author1",2000);
        Book b2 = new Book("Title2","Author2",2001);
        bookRepository.save(b1);
        bookRepository.save(b2);
        LibraryQuantity l1 = new LibraryQuantity(Status.AVAILABLE,b1);
        LibraryQuantity l2 = new LibraryQuantity(Status.AVAILABLE,b2);
        libraryQuantityRepository.save(l1);
        libraryQuantityRepository.save(l2);
        Borrowed borrowed1 = new Borrowed(l1,u1,LocalDate.now());
        Borrowed borrowed2 = new Borrowed(l2,u2,LocalDate.now());
        borrowedRepository.save(borrowed1);
        borrowedRepository.save(borrowed2);
        //when
        int id = borrowed1.getId();
        borrowedRepository.deleteById(id);
        //then
        assertEquals(1,borrowedRepository.findAll().size());
    }
}