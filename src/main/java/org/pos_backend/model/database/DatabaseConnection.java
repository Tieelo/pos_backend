package org.pos_backend.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {
  private static final String DB_URL = "jdbc:sqlite::resource:PoS_SQLite.sqlite";
  private static DatabaseConnection instance;
  private final Connection dbConn;

  private DatabaseConnection() {
    try {
      Class.forName("org.sqlite.JDBC");
      dbConn = DriverManager.getConnection(DB_URL);
      dbConn.setAutoCommit(false);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      System.out.println("Failed to connect to database.");
      throw new RuntimeException(e);
    }
  }

  public static DatabaseConnection getInstance() {
    if(instance == null) {
      instance = new DatabaseConnection();
    } else {
      try {
        if(instance.getConnection().isClosed()) {
          instance = new DatabaseConnection();
        }
      } catch(SQLException e) {
        throw new RuntimeException(e);
      }
    }

    return instance;
  }

  public Connection getConnection() {
    return dbConn;
  }
}