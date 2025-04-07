package com.example.demo.config;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private final DataSource dataSource;

    private DatabaseConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static DatabaseConnection getInstance(DataSource dataSource) {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection(dataSource);
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}