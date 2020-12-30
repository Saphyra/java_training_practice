package com.github.training.endpoint_protection;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "endpoint-protection")
@Configuration
@Slf4j
@Data
public class EndpointProtectionProperties {
    private List<Endpoint> protectedEndpoints;
}
