package com.example.demo.services;

import com.example.demo.beans.Customer;
import com.example.demo.beans.User;
import com.example.demo.exceptions.ToDoException;

public interface AuthService {
    AuthServiceImpl.LoginResponseData Login(User user) throws ToDoException;
    AuthServiceImpl.RegisterResponseData Register(Customer customer) throws ToDoException;
}
