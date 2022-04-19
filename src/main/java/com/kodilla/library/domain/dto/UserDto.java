package com.kodilla.library.domain.dto;

import com.kodilla.library.enums.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private AppUserRole appUserRole;
    private LocalDate created;
}
