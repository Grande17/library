package com.kodilla.library.repository;

import com.kodilla.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {


    List<Book> findAll();
    Optional<Book> findById(int id);
    Book save(Book book);
    void deleteById(int id);
    List<Book> findByAuthorContains(String contains);
    List<Book> findByTitleContains(String contains);
    List<Book> findByPublished(int year);
    List<Book> findByPublishedAfter(int year);
    List<Book> findByPublishedBefore(int year);

}
