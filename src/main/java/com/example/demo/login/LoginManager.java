package com.example.demo.login;

import com.example.demo.exceptions.ErrorMessage;
import com.example.demo.exceptions.ToDoException;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.services.ClientService;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LoginManager {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TaskRepository taskRepository;
    public ClientService login(String email, String password) throws ToDoException {
        System.out.println("login");

        if (((ClientService)customerService).login(email,password)){
            return (ClientService) customerService;
        }
        throw new ToDoException(ErrorMessage.LOGIN_DETAILS_INCORRECT);
    }
}
