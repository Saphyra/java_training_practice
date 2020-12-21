package com.github.training.controller;

import com.github.training.controller.request.LoginAccountRequest;
import com.github.training.database.Account;
import com.github.training.database.AccountRepository;
import com.github.training.database.LoginSession;
import com.github.training.service.LoginSessionService;
import com.github.training.validation.LoginAccountRequestValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Slf4j
@RestController
public class LoginController {
    private final AccountRepository accountRepository;
    private final LoginAccountRequestValidation loginAccountRequestValidation;
    private final LoginSessionService loginSessionService;

    @PostMapping("/login")
    public void login(@RequestBody LoginAccountRequest loginAccountRequest){
        log.info("Login request test arrived: {}", loginAccountRequest);
        boolean logAccVal = loginAccountRequestValidation.loginAccountValidation(loginAccountRequest);
        if (!logAccVal) {
            log.error("Nem megfelelő adatok");
            throw new IllegalArgumentException("Nem megfelelő adatok");
        }
        Account account = accountRepository.findByEmail(loginAccountRequest.getEmail());

        if (isNull(account)){
            //TODO
        }
        if (!account.getPassword().equals(loginAccountRequest.getPassword())){
            //toDo
        }
        LoginSession loginSession = loginSessionService.createSession(account.getUserId(), loginAccountRequest.isRemember());

    }
}
