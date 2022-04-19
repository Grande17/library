package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.Book;
import com.kodilla.library.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryQuantityDto {
    private int id;
    private Status status;
    private Book book;
}
