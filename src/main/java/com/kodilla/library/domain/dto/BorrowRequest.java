package com.kodilla.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BorrowRequest {

    private BorrowedDto borrowedDto;
    private int userId;
    private int bookId;
}
