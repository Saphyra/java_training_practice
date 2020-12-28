package com.github.training.endpoint_protection;

import lombok.Data;

@Data
public class Endpoint {
    private String pathPattern;
    private String method;
}
