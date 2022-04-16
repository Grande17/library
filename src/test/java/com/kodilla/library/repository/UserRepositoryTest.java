package com.kodilla.library.repository;

import com.kodilla.library.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void findAll() {
        //given
        List<User> users = List.of(
                new User("Name1","Surname1","nnn","pass"),
                new User("Name2","Surname2","nnn","pass")
        );
        repository.saveAll(users);
        //when
        List<User> result = repository.findAll();
        //then
        assertEquals(2,result.size());
    }

    @Test
    void deleteById() {
        //given
        List<User> users = List.of(
                new User("Name1","Surname1","nnn","pass"),
                new User("Name2","Surname2","nnn","pass")
        );
        repository.saveAll(users);
        //when
        int id = repository.findAll().get(0).getId();
        repository.deleteById(id);
        //then
        assertEquals(1,repository.findAll().size());
        assertFalse(repository.findById(id).isPresent());
    }

    @Test
    void findById() {
        //given
        List<User> users = List.of(
                new User("Name1","Surname1","nnn","pass"),
                new User("Name2","Surname2","nnn","pass")
        );
        repository.saveAll(users);
        //when
        int id = repository.findAll().get(0).getId();
        Optional<User> user = repository.findById(id);
        //then
        assertTrue(user.isPresent());
    }

    @Test
    void findByNameContains() {
        //given
        List<User> users = List.of(
                new User("Name1","Surname1","nnn","pass"),
                new User("Name2","Surname2","nnn","pass")
        );
        repository.saveAll(users);
        //when
        List<User> result = repository.findByNameContains("ame");
        //then
        assertEquals(2,result.size());
    }

    @Test
    void findBySurnameContains() {
        //given
        List<User> users = List.of(
                new User("Name1","Surname1","nnn","pass"),
                new User("Name2","Surname2","nnn","pass")
        );
        repository.saveAll(users);
        //when
        List<User> result = repository.findBySurnameContains("name");
        //then
        assertEquals(2,result.size());
    }
}