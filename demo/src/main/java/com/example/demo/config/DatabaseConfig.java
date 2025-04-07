package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    
    @Bean
    public DatabaseConnection databaseConnection(DataSource dataSource) {
        return DatabaseConnection.getInstance(dataSource);
    }
}