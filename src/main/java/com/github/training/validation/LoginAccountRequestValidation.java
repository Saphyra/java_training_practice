package com.github.training.validation;

import com.github.training.controller.request.LoginAccountRequest;
import com.github.training.database.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

@RequiredArgsConstructor
@Component
public class LoginAccountRequestValidation {
    private final AccountRepository accountRepository;
    public boolean loginAccountValidation(LoginAccountRequest loginAccountRequest){
        //Username
        if(isBlank(loginAccountRequest.getEmail())){
            return false;
        }
        //Password
        if (isNull(loginAccountRequest.getPassword())){
            return false;
        }
        return true;
    }
}
