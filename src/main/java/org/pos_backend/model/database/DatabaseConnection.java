package org.pos_backend.model.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class DatabaseConnection {
  private static DatabaseConnection instance;
  private final Connection dbConn;
  private static final String DB_PROP_FILE= "/opt/application.properties"; // Stellen Sie sicher, dass Sie Ihren eigenen Pfad hier einfügen
  private static final String DB_URL_PROP = "spring.datasource.url";
  private String DB_URL;

  private DatabaseConnection() {
    try {
      // Load the database URL manually from the application properties
      Properties props = new Properties();
      // Using FileReader here to read the properties file
      props.load(new FileReader(DB_PROP_FILE));
      DB_URL = props.getProperty(DB_URL_PROP);

      Class.forName("org.sqlite.JDBC");
      dbConn = DriverManager.getConnection(DB_URL);
      dbConn.setAutoCommit(false);
    } catch (ClassNotFoundException | SQLException | IOException e) {
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