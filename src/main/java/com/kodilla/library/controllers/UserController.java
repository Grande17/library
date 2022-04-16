package com.kodilla.library.controllers;

import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.UserDto;
import com.kodilla.library.exceptions.RoleNotFoundException;
import com.kodilla.library.exceptions.UserNotFoundException;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserDbService userDbService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        List<User> list = userDbService.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(list));
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody @Valid UserDto userDto){
        User user = userMapper.mapToUser(userDto);
        userDbService.saveUser(user);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto){
        User user = userMapper.mapToUser(userDto);
        User savedUser = userDbService.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        userDbService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) throws UserNotFoundException{
        return ResponseEntity.ok(userMapper.mapToUserDto(userDbService.getById(id)));
    }
    @GetMapping(value = "nameContains={contains}")
    public ResponseEntity<List<UserDto>> getByNameContains(@PathVariable String contains){
        List<User> list = userDbService.getByNameContains(contains);
        return ResponseEntity.ok(userMapper.mapToUserDtoList(list));
    }
    @GetMapping(value = "surnameContains={contains}")
    public ResponseEntity<List<UserDto>> getBySurnameContains(@PathVariable String contains){
        List<User> list = userDbService.getBySurnameContains(contains);
        return ResponseEntity.ok(userMapper.mapToUserDtoList(list));
    }
    @PutMapping(value = "role/{userId}/{role}")
    public ResponseEntity<UserDto> changeRole(@PathVariable int userId, @PathVariable String role) throws UserNotFoundException, RoleNotFoundException {
        userDbService.changeRole(userId,role);
        return ResponseEntity.ok().build();
    }
}
