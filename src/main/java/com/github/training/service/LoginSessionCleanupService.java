package com.github.training.service;

import com.github.training.config.ConfigProperties;
import com.github.training.database.LoginSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class LoginSessionCleanupService {
    private final LoginSessionRepository repository;
    private final ConfigProperties properties;

    @Transactional
    @Scheduled(fixedRateString = "${loginSession.cleanup.intervalMillis}")
    public void cleanUpExpiredSessions() {
        log.info("Deleting expired LoginSessions...");
        LocalDateTime actualTime = LocalDateTime.now();
        LocalDateTime expirationTime = actualTime.minusSeconds(properties.getSessionExpirationSeconds());

        repository.deleteByRememberAndLastAccessBefore(false, expirationTime);

        LocalDateTime rememberExpirationTime = actualTime.minusYears(1);
        repository.deleteByRememberAndLastAccessBefore(true, rememberExpirationTime);
        log.info("Expired LoginSessions are cleaned up.");
    }
}
