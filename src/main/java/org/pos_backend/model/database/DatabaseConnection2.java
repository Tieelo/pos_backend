package org.pos_backend.model.database;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnection2 {
    @Value("${spring.datasource.url}")
    private String DB_URL;

    private Connection dbConn;

    @PostConstruct
    private void initialize() {
        try {
            Class.forName("org.sqlite.JDBC");
            dbConn = DriverManager.getConnection(DB_URL);
            dbConn.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return this.dbConn;
    }
}