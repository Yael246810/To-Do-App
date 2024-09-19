package com.example.demo.services;

import com.example.demo.beans.Customer;
import com.example.demo.beans.User;
import com.example.demo.exceptions.ErrorMessage;
import com.example.demo.exceptions.ToDoException;
import com.example.demo.login.LoginManager;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LoginManager loginManager;
    @Override
    public LoginResponseData Login(User user) throws ToDoException {
        if (!userRepository.existsByEmailAndPassword(user.getEmail(),user.getPassword())){
            throw new ToDoException(ErrorMessage.SECURITY_EXCEPTION);
        }
        loginManager.login(user.getEmail(), user.getPassword());
        String token = tokenService.addToTokenList(user).toString();
        long id = userRepository.getIdByEmail(user.getEmail());
        LoginResponseData loginResponseData = new LoginResponseData(token,id);
        return loginResponseData;
    }

    @Override
    public RegisterResponseData Register(Customer customer) throws ToDoException {
        if (userRepository.existsByEmail(customer.getEmail())){
            throw new ToDoException(ErrorMessage.EMAIL_ALREADY_IN_USE);
        }

        User user = new User();
        user.setEmail(customer.getEmail());
        user.setPassword(customer.getPassword());
        user.setId(customer.getId());
        customerRepository.save(customer);
        RegisterResponseData registerResponseData = new RegisterResponseData(customer.getId(),customer.getEmail(), customer.getFirstName(),customer.getLastName(),customer.getPassword());
        return registerResponseData;
    }

    public class LoginResponseData{
        private String token;
        private long id;
        public LoginResponseData(String token, long id){
            this.token = token;
            this.id = id;
        }
        public long getId(){
            return id;
        }
        public String getToken(){
            return token;
        }
    }

    public class RegisterResponseData{
        private long id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;

        public RegisterResponseData(long id,String firstName, String lastName, String email, String password){
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
        }

        public long getId(){
            return id;
        }
        public String getFirstName(){
            return firstName;
        }
        public String getLastName(){
            return lastName;
        }
        public String getEmail(){
            return email;
        }
        public String getPassword(){
            return password;
        }
    }
}
