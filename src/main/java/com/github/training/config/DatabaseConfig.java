package com.github.training.config;

import com.github.training.Application;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.github.training")
@EntityScan(basePackageClasses = Application.class)
@Configuration
public class DatabaseConfig {

}
