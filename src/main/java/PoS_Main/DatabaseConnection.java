package PoS_Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

  private Connection conn;

  public DatabaseConnection(String url) {
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(url);
      conn.setAutoCommit(false);
    } catch (SQLException e) {
      System.out.println("Failed to connect to database.");
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public Connection getConnection() {
    return conn;
  }
}
