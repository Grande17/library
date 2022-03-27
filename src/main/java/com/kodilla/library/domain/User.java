package com.kodilla.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity(name = "USERS")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID", unique = true)
    private int id;

    @Column(name = "NAME")
    @NotNull(message = "User Name must not be null")
    private String name;

    @Column(name = "SURNAME")
    @NotNull(message = "User Surname must not be null")
    private String surname;

    @Column(name = "CREATED")
    @NotNull(message = "User Created must not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;



}
