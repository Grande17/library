package com.kodilla.library.repository;

import com.kodilla.library.domain.Borrowed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedRepository extends CrudRepository<Borrowed, Integer> {
    List<Borrowed> findAll();
    Borrowed save(Borrowed borrowed);
    void deleteById(int id);
}
