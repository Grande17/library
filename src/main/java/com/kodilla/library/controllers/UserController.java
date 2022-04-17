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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserDbService userDbService;
    private final UserMapper userMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('super_admin')")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<User> list = userDbService.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(list));
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto){
        return userDbService.updateUserProcessor(userDto);
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
