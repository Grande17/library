package com.kodilla.library.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "BOOKS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Book ID must not be null")
    @Column(name = "ID", unique = true)
    private int id;

    @Column(name = "TITLE")
    @Size(min = 3,max = 50)
    @NotNull(message = "Book Title must not be null")
    private String title;

    @Column(name = "AUTHOR")
    @Size(min = 3,max = 50)
    @NotNull(message = "Book Author must not be null")
    private String author;

    @Column(name = "PUBLISHED")
    @Min(value = 1000 ,message = "Book Published must not be null")
    private int published;

}
