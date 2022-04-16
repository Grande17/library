package com.kodilla.library.domain.dto;

import com.kodilla.library.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private AppUserRole appUserRole;
    private LocalDate created;
}
