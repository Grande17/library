package com.kodilla.library.domain;

import com.kodilla.library.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "STOCK")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Table
public class LibraryQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private int id;


    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Library Quantity Status must not be null")
    private Status status;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOK_ID")
    @NotNull
    private Book book;

    public LibraryQuantity(Status status, Book book) {
        this.status = status;
        this.book = book;
    }
}
