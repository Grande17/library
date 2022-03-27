package com.kodilla.library.domain.dao;

import com.kodilla.library.domain.Borrowed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BorrowedDao extends CrudRepository<Borrowed, Integer> {
    Optional<Borrowed> findById(int id);
    List<Borrowed> findByLibraryQuantityId(int id);
    List<Borrowed> findByUserId(int id);
    List<Borrowed> findByBorrowedAfter(LocalDate date);
    List<Borrowed> findByBorrowedBefore(LocalDate date);
    List<Borrowed> findByDeadlineAfter(LocalDate date);
    List<Borrowed> findByDeadlineBefore(LocalDate date);
}
