package com.kodilla.library.domain.dao;

import com.kodilla.library.domain.LibraryQuantity;
import com.kodilla.library.enums.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface LibraryQuantityDao extends CrudRepository<LibraryQuantity, Integer> {
    Optional<LibraryQuantity> findById(int id);
    List<LibraryQuantity> findByStatus(Status status);
    List<LibraryQuantity> findByBookId(int id);
    LibraryQuantity save(LibraryQuantity libraryQuantity);
}
