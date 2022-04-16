package com.kodilla.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity(name = "USERS")
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private int id;

    @Column(name = "NAME")
    @NotNull(message = "User Name must not be null")
    private String name;

    @Column(name = "SURNAME")
    @NotNull(message = "User Surname must not be null")
    private String surname;

    @Column(name = "CREATED")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;


    public User(int id, String name, String surname, LocalDate created) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.created = LocalDate.now();
    }

    public User(String name, String surname, LocalDate created) {
        this.name = name;
        this.surname = surname;
        this.created = created;
    }
}
