package com.github.training.endpoint_protection;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

@Configuration
public class EndpointProtectionConfig {
    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }

    @Bean
    public FilterRegistrationBean<EndpointProtectionFilter> endpointProtectionFilterFilterRegistrationBean(EndpointProtectionFilter filter) {
        FilterRegistrationBean<EndpointProtectionFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(filter);
        return filterFilterRegistrationBean;
    }
}
