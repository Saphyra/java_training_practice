package com.github.training.service;

import com.github.training.database.LoginSession;
import com.github.training.database.LoginSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LoginSessionService {
    private final LoginSessionRepository loginSessionRepository;
    public LoginSession createSession(UUID userId, boolean remember){
        LoginSession loginSession = new LoginSession();

        loginSession.setUserId(userId);
        loginSession.setLastAccess(LocalDateTime.now());
        loginSession.setRemember(remember);
        loginSession.setSessionId(UUID.randomUUID());

        loginSessionRepository.save(loginSession);
        return loginSession;
    }
}
