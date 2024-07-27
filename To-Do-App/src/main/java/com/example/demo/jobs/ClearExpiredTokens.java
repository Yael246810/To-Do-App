package com.example.demo.jobs;

import com.example.demo.security.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClearExpiredTokens {
    @Autowired
    private TokenService tokenService;
    @Transactional
    @Scheduled(fixedRate = 1000*60*60*24)
    public void clearExpiredTokens(){
        System.out.println("thread of expired tokens starts");
        tokenService.clear();
        System.out.println("thread deleted expired tokens");
    }
}
