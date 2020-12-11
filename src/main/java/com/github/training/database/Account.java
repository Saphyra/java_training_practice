package com.github.training.database;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Account {
    @Id
    private UUID userId;
    private String username;
    private String password;
    private String email;
    private String currency;

}
