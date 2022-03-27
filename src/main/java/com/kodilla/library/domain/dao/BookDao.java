package com.kodilla.library.domain.dao;

import com.kodilla.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface BookDao extends CrudRepository<Book, Integer> {
    Book findById(int id);
    Book findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByPublished(int year);
}
