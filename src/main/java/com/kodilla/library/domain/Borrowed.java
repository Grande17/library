package com.kodilla.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "BORROWED")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Borrowed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",unique = true)
    private int id;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "QTY_ID")
    @NotNull
    private LibraryQuantity libraryQuantity;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user;

    @Column(name = "BORROWED")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Borrow date must not be null")
    private LocalDate borrowed;

    @Column(name = "DEADLINE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private  LocalDate deadline;


    public Borrowed(int id, LibraryQuantity libraryQuantity, User user, LocalDate borrowed) {
        this.id = id;
        this.libraryQuantity = libraryQuantity;
        this.user = user;
        this.borrowed = borrowed;
        this.deadline = borrowed.plusDays(30);
    }
}
