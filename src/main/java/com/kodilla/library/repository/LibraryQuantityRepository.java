package com.kodilla.library.repository;

import com.kodilla.library.domain.LibraryQuantity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryQuantityRepository extends CrudRepository<LibraryQuantity, Integer> {


   List<LibraryQuantity> findAll();
   LibraryQuantity save(LibraryQuantity libraryQuantity);
   Optional<LibraryQuantity> findById(int id);

}
