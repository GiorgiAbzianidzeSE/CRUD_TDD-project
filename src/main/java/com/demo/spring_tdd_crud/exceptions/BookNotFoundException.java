package com.demo.spring_tdd_crud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND , reason = "Book not found on given id")
public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(final String message){
        super(message);
    }

    public BookNotFoundException(){
        this("Book is not found on given id");
    }

}
