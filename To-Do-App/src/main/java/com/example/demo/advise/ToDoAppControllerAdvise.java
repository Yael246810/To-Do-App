package com.example.demo.advise;

import com.example.demo.exceptions.ToDoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ToDoAppControllerAdvise {
    @ExceptionHandler(value = {ToDoException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDetails handleExceptions(Exception e){
        return new ErrDetails(e.getMessage());
    }
}
