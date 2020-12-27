package com.github.training.service;

import com.github.training.config.ConfigProperties;
import com.github.training.database.LoginSession;
import com.github.training.database.LoginSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class LoginSessionService {
    private final LoginSessionRepository loginSessionRepository;
    private final ConfigProperties configProperties;

    public LoginSession createSession(UUID userId, boolean remember) {
        LoginSession loginSession = new LoginSession();

        loginSession.setUserId(userId);
        loginSession.setLastAccess(LocalDateTime.now());
        loginSession.setRemember(remember);
        loginSession.setSessionId(UUID.randomUUID());

        loginSessionRepository.save(loginSession);
        return loginSession;
    }

    public Optional<LoginSession> findBySessionId(UUID sessionId) {
        if (isNull(sessionId)) {
            return Optional.empty();
        }
        Optional<LoginSession> maybeSession = loginSessionRepository.findById(sessionId);
        if (maybeSession.isPresent()) {
            LoginSession loginSession = maybeSession.get();
            LocalDateTime lastAccess = loginSession.getLastAccess();
            LocalDateTime currentTime = LocalDateTime.now();
            int expirationSeconds = configProperties.getSessionExpirationSeconds();
            boolean isSessionActive = lastAccess.plusSeconds(expirationSeconds).isAfter(currentTime);
            return isSessionActive ? maybeSession : Optional.empty();
        } else {
            return Optional.empty();
        }
    }

    public void logout(UUID sessionId) {
        loginSessionRepository.deleteById(sessionId);
    }
}
