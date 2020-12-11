package com.github.training.controller.request;

import lombok.Data;

@Data
public class RegisterAccountRequest {
    private String username;
    private String password;
    private String email;
    private String name;
    private AddressRequest address;
    private String phoneNumber;
    private String birthDate;
    private String currency;
}
