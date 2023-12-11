package org.pos_backend.model.database;

import org.pos_backend.model.objects.Groups;
import org.pos_backend.model.objects.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ItemsManager {
    private final DatabaseConnection dbConnection;

    public ItemsManager() {
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

    public List<Item> getItems(Integer groupId) {
        List<Item> items = new ArrayList<>();
        try {
            Statement stmt = dbConnection.getConnection().createStatement();
            String query = "SELECT i.items_id, i.item_name, g.measurement, i.item_price, i.item_amount, g.group_names, i.groups_id " +
                    "FROM items i " +
                    "JOIN groups g " +
                    "ON i.groups_id = g.groups_id";
            if (groupId != null) {
                query += " WHERE i.groups_id=" + groupId;
            }

            query += " ORDER BY i.groups_id;";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Item item = new Item(
                        rs.getInt("items_id"),
                        rs.getString("item_name"),
                        rs.getString("measurement"),
                        rs.getDouble("item_price"),
                        rs.getDouble("item_amount"),
                        rs.getString("group_names"),
                        rs.getInt("groups_id")

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

    public void updateDatabaseFromInventory(Item item) {
        String query = "UPDATE items SET item_amount = ? WHERE items_id = ?";
        Connection conn = null;
        try {
            conn = dbConnection.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, item.getAmount());
            pstmt.setInt(2, item.getId());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    System.err.println("Transaction is being rolled back");
                    conn.rollback();
                } catch (Exception excep) {
                    System.err.println(excep.getClass().getName() + ": " + excep.getMessage());
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (Exception ignored) {
                }
            }
        }
    }
}
