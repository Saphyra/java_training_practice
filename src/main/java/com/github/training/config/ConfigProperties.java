package com.github.training.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ConfigProperties {
    @Value("${session.expirationSeconds}")
    private int sessionExpirationSeconds;
}
