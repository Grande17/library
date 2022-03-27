package com.kodilla.library.mapper;

import com.kodilla.library.domain.Borrowed;
import com.kodilla.library.domain.dto.BorrowedDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowedMapper {

    public Borrowed mapToBorrowed(BorrowedDto borrowedDto){
        return new Borrowed(
                borrowedDto.getId(),
                borrowedDto.getLibraryQuantity(),
                borrowedDto.getUser(),
                borrowedDto.getBorrowed()
        );
    }
    public BorrowedDto mapToBorrowedDto(Borrowed borrowed){
        return new BorrowedDto(borrowed.getId(),
                borrowed.getLibraryQuantity(),
                borrowed.getUser(),
                borrowed.getBorrowed(),
                borrowed.getDeadline());
    }
    public List<BorrowedDto> mapToBorrowedDtoList(List<Borrowed> list){
        return list.stream()
                .map(this::mapToBorrowedDto)
                .collect(Collectors.toList());
    }
}
