package com.example.demo.controllers;

import com.example.demo.exceptions.ErrorMessage;
import com.example.demo.exceptions.ToDoException;
import com.example.demo.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public abstract class BaseController {
    @Autowired
    private TokenService tokenService;

    protected void validateToken(String token) throws ToDoException {
        UUID tokenUUID = UUID.fromString(token);

        if (!tokenService.isUserAllowed(tokenUUID)){
            throw new ToDoException(ErrorMessage.SECURITY_EXCEPTION_USER_NOT_ALLOWED);
        }
    }
}
