package PoS_Main;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
    private static final String DB_URL =
            "jdbc:sqlite::resource:PoS_SQLite.sqlite";
    private static DatabaseConnection dbConnection;
    public static void main (String[] args) throws SQLException{
        dbConnection = new DatabaseConnection(DB_URL);

            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from items");

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()){
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }

    }

}
