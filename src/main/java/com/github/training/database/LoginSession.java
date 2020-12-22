package com.github.training.database;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class LoginSession {
    @Id
    private UUID sessionId;
    private UUID userId;
    private boolean remember;
    private LocalDateTime lastAccess;
}
