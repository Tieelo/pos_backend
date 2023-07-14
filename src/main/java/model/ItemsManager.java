package model;

import database.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemsManager {
    private final DatabaseConnection dbConnection;
    public ItemsManager(){
        dbConnection = DatabaseConnection.getInstance();
    }
    public List<Groups> getGroups() {
        List<Groups> groups = new ArrayList<>();
        try {
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT groups_id, group_names FROM groups order by groups_id;"
            );
            while (rs.next()) {
                int groups_id = rs.getInt("groups_id");
                String group_names = rs.getString("group_names");
                groups.add(new Groups(groups_id, group_names));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return groups;
    }
    public List<Items> getItems(Integer groupId) {
        List<Items> items = new ArrayList<>();
        try {
            Statement stmt = dbConnection.getConnection().createStatement();
            String query = "SELECT i.items_id, i.item_name, g.measurement, i.item_price, i.item_amount, g.group_names " +
                    "FROM items i " +
                    "JOIN groups g " +
                    "ON i.groups_id = g.groups_id";
            if(groupId != null) {
                query += " WHERE i.groups_id=" + groupId;
            }

            query += " ORDER BY i.groups_id;";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Items item = new Items(
                        rs.getInt("items_id"),
                        rs.getString("item_name"),
                        rs.getString("measurement"),
                        rs.getDouble("item_price"),
                        rs.getDouble("item_amount"),
                        rs.getString("group_names")
                );
                items.add(item);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return items;
    }

    // Other methods (addItem, deleteItem, updateItem) would be similar and accept an Item object as parameter
    // They would execute the corresponding INSERT, DELETE or UPDATE query on the database
}
