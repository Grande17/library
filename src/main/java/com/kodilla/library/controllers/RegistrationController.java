package com.kodilla.library.controllers;

import com.kodilla.library.domain.dto.UserDto;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserMapper mapper;
    private final UserDbService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registerUser(@RequestBody UserDto userDto){
        service.signUp(userDto);
        return ResponseEntity.ok().build();
    }

}
