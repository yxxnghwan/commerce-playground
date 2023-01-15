package com.playground.commerce.config;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class DatabaseCleanerConfig {

    @Autowired
    private EntityManager entityManager;

    @Bean
    public DatabaseCleaner databaseCleaner() {
        return new DatabaseCleaner(entityManager);
    }
}
