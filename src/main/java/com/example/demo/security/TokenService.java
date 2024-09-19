package com.example.demo.security;

import com.example.demo.beans.User;

import java.util.UUID;

public interface TokenService {
    UUID addToTokenList(User user);
    boolean isUserAllowed(UUID token);
    void clear();
}
