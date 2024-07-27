package com.example.demo.services;

import com.example.demo.beans.User;
import com.example.demo.exceptions.ErrorMessage;
import com.example.demo.exceptions.ToDoException;
import com.example.demo.login.LoginManager;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepository;
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
}
