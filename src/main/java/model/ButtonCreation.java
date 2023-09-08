package model;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;


public class ButtonCreation {
    private static GridPane buttonView;

    private Inventory inventory;

    public static HBox createItemRow(int start, int end) {
        HBox itemRow = new HBox();
        itemRow.setAlignment(Pos.TOP_CENTER);
        itemRow.setSpacing(10);

        for (int i = start; i <= end; i++) {
            Button itemButton = new Button();
            itemButton.setId("itembutton");
            itemButton.setMinWidth(100);
            itemButton.setMinHeight(50);
            itemRow.getChildren().add(itemButton);

        }

        return itemRow;
    }

    public static HBox createGroupRow(int start, int end) {
        HBox groupRow = new HBox();
        groupRow.setAlignment(Pos.TOP_CENTER);
        groupRow.setSpacing(10);

        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        Connection connection = dbConnection.getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT group_names FROM groups WHERE groups_id BETWEEN " + start + " AND " + end;
            ResultSet resultSet = statement.executeQuery(query);
            int currentId = start;

            while (resultSet.next()) {
                String groupName = resultSet.getString("group_names");
                Button groupButton = new Button(groupName);
                String buttonId = "" + currentId ;

                groupButton.setId(buttonId);
                groupButton.setMinWidth(100);
                groupButton.setMinHeight(50);
                groupButton.setOnAction(event -> {
                    String buttonIdStr = ((Button) event.getSource()).getId(); // Die Button-ID als String abrufen
                    int buttonIdInt = Integer.parseInt(buttonIdStr); // Die Button-ID in einen Integer umwandeln
                    removeRowsFromGridPane(buttonView, 7, 10);
                    //createRealItemRow(buttonIdInt);

                });
                groupRow.getChildren().add(groupButton);
                currentId++;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return groupRow;
    }

    public static void test() {

    }


    /*public static void createRealItemRow(int buttonID) {
        int itemCount = 0;

        String query = "SELECT item_name FROM items WHERE groups_id = ?";
        try (PreparedStatement preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, buttonID); // Die Button-ID als Parameter setzen
            ResultSet resultSet = preparedStatement.executeQuery();

            HBox itemRow1 = new HBox();
            itemRow1.setAlignment(Pos.TOP_CENTER);
            itemRow1.setSpacing(10);
            int count = 1;

            while (resultSet.next() & itemCount < 4) {

                String itemName = resultSet.getString("item_name");

                Button itemButton = new Button(itemName);
                itemButton.setId("itembutton");
                itemButton.setMinWidth(100);
                itemButton.setMinHeight(50);
                itemButton.setId();
                itemButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                    }
                });
                itemRow1.getChildren().add(itemButton);





                itemCount++;
            }
            addToButtonView(itemRow1, 0, 7);

            HBox itemRow2 = new HBox();
            itemRow2.setAlignment(Pos.TOP_CENTER);
            itemRow2.setSpacing(10);

            while (resultSet.next() & itemCount < 8 & itemCount >= 4) {

                String itemName = resultSet.getString("item_name");

                Button itemButton = new Button(itemName);
                itemButton.setId("itembutton");
                itemButton.setMinWidth(100);
                itemButton.setMinHeight(50);
                itemRow2.getChildren().add(itemButton);





                itemCount++;
            }
            addToButtonView(itemRow2, 0, 8);

            HBox itemRow3 = new HBox();
            itemRow3.setAlignment(Pos.TOP_CENTER);
            itemRow3.setSpacing(10);

            while (resultSet.next() & itemCount < 12 & itemCount >= 8) {

                String itemName = resultSet.getString("item_name");

                Button itemButton = new Button(itemName);
                itemButton.setId("itembutton");
                itemButton.setMinWidth(100);
                itemButton.setMinHeight(50);
                itemRow3.getChildren().add(itemButton);





                itemCount++;
            }
            addToButtonView(itemRow3, 0, 9);

            HBox itemRow4 = new HBox();
            itemRow4.setAlignment(Pos.TOP_CENTER);
            itemRow4.setSpacing(10);

            while (resultSet.next() & itemCount < 16 & itemCount >= 12) {

                String itemName = resultSet.getString("item_name");

                Button itemButton = new Button(itemName);
                itemButton.setId("itembutton");
                itemButton.setMinWidth(100);
                itemButton.setMinHeight(50);
                itemRow4.getChildren().add(itemButton);





                itemCount++;
            }
            addToButtonView(itemRow4, 0, 10);



        } catch (SQLException e) {
            e.printStackTrace();
        }


    }*/

    public static void initButtonView(GridPane view) {
        buttonView = view;
    }

    public static void addToButtonView(Node node, int columnIndex, int rowIndex) {
        if (buttonView != null) {
            buttonView.add(node, columnIndex, rowIndex);
        }
    }

    public static void removeRowsFromGridPane(GridPane gridPane, int startRowIndex, int endRowIndex) {
        ObservableList<Node> children = gridPane.getChildren();
        children.removeIf(node -> GridPane.getRowIndex(node) >= startRowIndex && GridPane.getRowIndex(node) <= endRowIndex);
    }






}
