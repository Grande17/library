package com.kodilla.library.service;

import com.kodilla.library.domain.User;
import com.kodilla.library.exceptions.UserNotFoundException;
import com.kodilla.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDbService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
    public User getById(int id) throws UserNotFoundException{
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
    public List<User> getByNameContains(String contains){
        return userRepository.findByNameContains(contains);
    }
    public List<User> getBySurnameContains(String contains){
        return userRepository.findBySurnameContains(contains);
    }
}
