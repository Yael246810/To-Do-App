package com.example.demo.services;

import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected TaskRepository taskRepository;

    public abstract boolean login(String email, String password);
}
