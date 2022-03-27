package com.kodilla.library.domain.dao;

import com.kodilla.library.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    Optional<User> findById(int id);
    List<User> findByName(String name);
    List<User> findBySurname(String surname);
    List<User> findByCreatedAfter(LocalDate date);
    List<User> findByCreatedBefore(LocalDate date);


}
