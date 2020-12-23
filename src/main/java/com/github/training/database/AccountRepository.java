package com.github.training.database;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, UUID> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Account findByEmail(String email);

    Account findByUserId(UUID userId);

}
