package com.example.demo.exceptions;

public class ToDoException extends Exception{
    public ToDoException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
    }
}
