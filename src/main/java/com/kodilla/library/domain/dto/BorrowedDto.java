package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.LibraryQuantity;
import com.kodilla.library.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BorrowedDto {
    private int id;
    private LibraryQuantity libraryQuantity;
    private User user;
    private LocalDate borrowed;
    private LocalDate deadline;
}
