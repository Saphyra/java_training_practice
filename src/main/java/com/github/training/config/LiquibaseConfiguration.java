package com.github.training.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
@Slf4j
public class LiquibaseConfiguration {
    private final static String CREATE_SCHEMA_SQL = "create schema if not exists %s";

    @Bean
    public SpringLiquibase liquibase(
            DataSource dataSource,
            @Value("${liquibase.changelog.location}") String changeLogLocation,
            @Value("${liquibase.schema}") String schema
    ) throws SQLException {
        createSchema(dataSource, schema);
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(changeLogLocation);
        liquibase.setLiquibaseSchema(schema);
        return liquibase;
    }

    private void createSchema(DataSource dataSource, String schema) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute(String.format(CREATE_SCHEMA_SQL, schema));

        statement.close();
        connection.close();
    }
}
