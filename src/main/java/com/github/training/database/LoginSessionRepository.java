package com.github.training.database;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LoginSessionRepository extends CrudRepository<LoginSession, UUID> {

}
