package com.github.training.config;

import com.github.saphyra.encryption.impl.PasswordService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public PasswordService passwordService(){
        return new PasswordService();
    }
}
