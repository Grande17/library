package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.LibraryQuantity;
import com.kodilla.library.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowedDto {
    private int id;
    private LibraryQuantity libraryQuantity;
    private User user;
    private LocalDate borrowed;
    private LocalDate deadline;
}
