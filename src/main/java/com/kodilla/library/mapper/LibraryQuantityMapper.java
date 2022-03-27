package com.kodilla.library.mapper;

import com.kodilla.library.domain.LibraryQuantity;
import com.kodilla.library.domain.dto.LibraryQuantityDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryQuantityMapper {

    public LibraryQuantity mapToLibraryQuantity(LibraryQuantityDto libraryQuantityDto){
        return new LibraryQuantity(
                libraryQuantityDto.getId(),
                libraryQuantityDto.getStatus(),
                libraryQuantityDto.getBook()
                );
    }
    public LibraryQuantityDto mapToLibraryQuantityDto(LibraryQuantity libraryQuantity){
        return new LibraryQuantityDto(
                libraryQuantity.getId(),
                libraryQuantity.getStatus(),
                libraryQuantity.getBook());
    }
    public List<LibraryQuantityDto> mapToLibraryQuantityDtoList(List<LibraryQuantity> list){
        return list.stream()
                .map(this::mapToLibraryQuantityDto)
                .collect(Collectors.toList());
    }
}
