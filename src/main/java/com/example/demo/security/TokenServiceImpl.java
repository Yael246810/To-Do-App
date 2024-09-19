package com.example.demo.security;

import com.example.demo.beans.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
public class TokenServiceImpl implements TokenService {
    private Map<UUID, Information> tokens = new HashMap<>();

    @Override
    public UUID addToTokenList(User user) {
        UUID token = UUID.randomUUID();
        Information information = Information.builder()
                .id((int) user.getId())
                .time(LocalDateTime.now())
                .build();
        tokens.put(token, information);
        return token;
    }

    @Override
    public boolean isUserAllowed(UUID token) {
        Information information = tokens.get(token);
        UUID uuid = UUID.fromString(String.valueOf(token));
        return tokens.containsKey(token);
    }

    @Override
    public void clear() {
        LocalDateTime timeToDeleteTokens = LocalDateTime.now().plusMinutes(30);
        this.tokens.entrySet().removeIf(token -> token.getValue().getTime().isAfter(timeToDeleteTokens));
    }
}
