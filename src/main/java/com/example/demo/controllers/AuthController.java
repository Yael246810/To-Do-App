package com.example.demo.controllers;

import com.example.demo.beans.Customer;
import com.example.demo.beans.User;
import com.example.demo.exceptions.ToDoException;
import com.example.demo.services.AuthService;
import com.example.demo.services.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthServiceImpl.LoginResponseData login(@RequestBody User user) throws ToDoException {
        System.out.println("login process has started the user: " +user);
        return authService.Login(user);
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthServiceImpl.RegisterResponseData register(@RequestBody Customer customer) throws ToDoException {
        System.out.println("registration process just started");
        return authService.Register(customer);
    }
}
