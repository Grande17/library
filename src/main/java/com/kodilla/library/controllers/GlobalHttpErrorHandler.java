package com.kodilla.library.controllers;

import com.kodilla.library.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;


@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFound(BookNotFoundException e){
        return new ResponseEntity<>("Book with given search parameters was not found!", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException e){
        return new ResponseEntity<>("User with given search parameters was not found", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(LibraryQuantityNotFound.class)
    public ResponseEntity<Object> handleLibraryQuantityNotFound(LibraryQuantityNotFound e){
        return new ResponseEntity<>("There are no available books with given ID",HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(StatusNotFoundException.class)
    public ResponseEntity<Object> handleStatusNotFound(StatusNotFoundException e){
        return new ResponseEntity<>("Status not found. n/" +
                "Status type: available, damaged, not_available", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BorrowedNotFound.class)
    public ResponseEntity<Object> handleBorrowedNotFound(BorrowedNotFound e){
        return new ResponseEntity<Object>("Borrowed was not found",HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e){
        return new ResponseEntity<Object>("One or more of the requested fileds are empty!", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MaximumBooksBorrowedException.class)
    public ResponseEntity<Object> handleMaxBooksException( MaximumBooksBorrowedException e){
        return new ResponseEntity<Object>("Maximum amount of books is already borrowed, please return the books",HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> handleRoleNotFound(RoleNotFoundException e){
        return new ResponseEntity<Object>("Given role does not exists",HttpStatus.BAD_REQUEST);
    }

}
