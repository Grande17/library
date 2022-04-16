package com.kodilla.library.repository;

import com.kodilla.library.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();
    User save(User user);
    void deleteById(int id);
    Optional<User> findById(int id);
    List<User> findByNameContains(String contains);
    List<User> findBySurnameContains(String contains);
    Optional<User> findByUsername(String username);

}
