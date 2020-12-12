package com.github.training.validation;


import com.github.training.controller.request.RegisterAccountRequest;
import com.github.training.database.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

@RequiredArgsConstructor
@Component

public class RegisterAccountRequestValidation {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final AccountRepository accountRepository;


    public boolean registerAccountValidation(RegisterAccountRequest registerAccountRequest) {
        //Username
        if (isBlank(registerAccountRequest.getUsername())) {
            return false;
        }
        if (registerAccountRequest.getUsername().length() < 6 || registerAccountRequest.getUsername().length() > 32) {
            return false;
        }
        if (accountRepository.existsByUsername(registerAccountRequest.getUsername())) {
            return false;
        }
        //password
        if (isNull(registerAccountRequest.getPassword())) {
            return false;
        }
        if (registerAccountRequest.getPassword().length() < 6 || registerAccountRequest.getPassword().length() > 20) {
            return false;
        }
        //email
        if (isBlank(registerAccountRequest.getEmail())) {
            return false;
        }
        if (!EMAIL_PATTERN.matcher(registerAccountRequest.getEmail()).matches()) {
            return false;
        }
        if (accountRepository.existsByEmail(registerAccountRequest.getEmail())) {
            return false;
        }
        //name
        if (!isBlank(registerAccountRequest.getName()) && registerAccountRequest.getName().length() > 64) {
            return false;
        }
        return true;

    }
}