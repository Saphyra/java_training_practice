package com.github.training.controller;

import com.github.saphyra.encryption.impl.PasswordService;
import com.github.training.config.ConfigProperties;
import com.github.training.controller.request.LoginAccountRequest;
import com.github.training.database.Account;
import com.github.training.database.AccountRepository;
import com.github.training.database.LoginSession;
import com.github.training.error_handler.RestException;
import com.github.training.service.LoginSessionService;
import com.github.training.validation.LoginAccountRequestValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Slf4j
@RestController
public class LoginController {
    private final AccountRepository accountRepository;
    private final LoginAccountRequestValidation loginAccountRequestValidation;
    private final LoginSessionService loginSessionService;
    private final ConfigProperties configProperties;
    private final PasswordService passwordService;

    @PostMapping("/login")
    public void login(@RequestBody LoginAccountRequest loginAccountRequest, HttpServletResponse response) {
        log.info("Login request test arrived: {}", loginAccountRequest);
        boolean logAccVal = loginAccountRequestValidation.loginAccountValidation(loginAccountRequest);
        if (!logAccVal) {
            log.error("Nem megfelelő adatok");
            throw new IllegalArgumentException("Nem megfelelő adatok");
        }
        Account account = accountRepository.findByEmail(loginAccountRequest.getEmail());

        if (isNull(account)) {
            throw new RestException("Nem letezik ilyen felhasznalo.", HttpStatus.UNAUTHORIZED, "Nem letezik ilyen felhasznalo.");
        }
        if (!passwordService.authenticate(loginAccountRequest.getPassword(), account.getPassword())) {
            throw new RestException("Nem megfelelo jelszo.", HttpStatus.UNAUTHORIZED, "Nem megfelelo jelszo.");
        }
        LoginSession loginSession = loginSessionService.createSession(account.getUserId(), loginAccountRequest.isRemember());

        Cookie cookie = new Cookie("session-id", loginSession.getSessionId().toString());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        int expiry = loginSession.isRemember() ? Integer.MAX_VALUE : configProperties.getSessionExpirationSeconds();
        cookie.setMaxAge(expiry);

        response.addCookie(cookie);
    }
}
