package com.kodilla.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRequest {

    private BorrowedDto borrowedDto;
    private int userId;
    private int bookId;
}
