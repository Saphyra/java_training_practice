package com.github.training.controller.request;

import lombok.Data;

@Data
public class LoginAccountRequest {
    private String email;
    private String password;
    private boolean remember;
}
