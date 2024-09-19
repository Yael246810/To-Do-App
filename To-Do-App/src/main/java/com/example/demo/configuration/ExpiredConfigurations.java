package com.example.demo.configuration;

import com.example.demo.jobs.ClearExpiredTokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
//TODO: what is all of this for?
@Configuration
@EnableScheduling
public class ExpiredConfigurations {
    @Autowired
    ClearExpiredTokens clearExpiredTokens;
    ExpiredConfigurations expiredConfigurations;
}
