/*
package model.gui;


import controller.FXGuiController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.objects.Cart;
import model.objects.Groups;
import model.database.Inventory;
import model.objects.Item;
import view.FXGui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;





public class ButtonCreation {
    static Inventory inventory = Inventory.getInstance();
    static Cart cart = Cart.getInstance();
    private static GridPane buttonView;


    // Rows for ItemButtonPlaceHolder
    public static HBox createItemRow(int start, int end) {
        HBox itemRow = new HBox();
        itemRow.setAlignment(Pos.TOP_CENTER);
        itemRow.setSpacing(10);

        for (int i = start; i <= end; i++) {
            Button itemButton = new Button();
            itemButton.setMinWidth(100);
            itemButton.setMinHeight(50);
            itemRow.getChildren().add(itemButton);

        }

        return itemRow;
    }


    // Rows for GroupButtons
    public static HBox createGroupRow(int start, int end) {
        HBox groupRow = new HBox();
        groupRow.setAlignment(Pos.TOP_CENTER);
        groupRow.setSpacing(10);

        List<Groups> groupsList = inventory.getGroups();

        for (Groups group : groupsList) {
            if (group.getId() >= start && group.getId() <= end) {
                String groupName = group.getName();
                int groupId = group.getId();
                Button groupButton = new Button(groupName);
                String buttonId = "" + groupId;

                groupButton.setId(buttonId);
                groupButton.setMinWidth(100);
                groupButton.setMinHeight(50);
                groupButton.setOnAction(event -> {
                    String buttonIdStr = ((Button) event.getSource()).getId();
                    int buttonIdInt = Integer.parseInt(buttonIdStr);
                    removeRowsFromGridPane(buttonView, 7, 10);
                    createRealItemRow(buttonIdInt);
                });
                groupRow.getChildren().add(groupButton);
            }
        }

        return groupRow;
    }

    // Item-Buttons corresponding to Group_ID
    public static void createRealItemRow(int buttonID) {
        int itemCount = 0;
        HBox totalAmount = FXGui.getTotalAmount();

        List<Item> itemsList = inventory.getItems(buttonID);

        HBox[] itemRows = new HBox[4];
        for (int i = 0; i < 4; i++) {
            itemRows[i] = new HBox();
            itemRows[i].setAlignment(Pos.TOP_CENTER);
            itemRows[i].setSpacing(10);
        }



        for (Item item : itemsList) {
            int[] idAndAmount = {0, 1};
            if (itemCount < 16) {
                String itemName = item.getName();
                int itemId = item.getId();

                //TODO: Abfrage für Amount

                Button itemButton = new Button(itemName);
                itemButton.setId("" + itemId);
                itemButton.setMinWidth(100);
                itemButton.setMinHeight(50);
                idAndAmount[0] = itemId;
                itemButton.setOnAction(new EventHandler<ActionEvent>() {
                    // Add Item to List when Button pressed
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        cart.fillCart(idAndAmount);
                        FXGuiController.addToList();
                    }
                });

                // 4 Buttons in a Row
                itemRows[itemCount / 4].getChildren().add(itemButton);
                if (itemCount % 4 == 3) {
                    addToButtonView(itemRows[itemCount / 4], 0, 7 + itemCount / 4);
                }

                itemCount++;
            }
        }

        // 4 Buttons in a Row
        for (int i = 0; i < (itemCount + 3) / 4; i++) {
            if (itemRows[i].getChildren().isEmpty() == false) {
                addToButtonView(itemRows[i], 0, 7 + i);
            }
        }
    }


    public static void initButtonView(GridPane view) {
        buttonView = view;
    }

    public static void addToButtonView(Node node, int columnIndex, int rowIndex) {
        if (buttonView != null) {
            buttonView.add(node, columnIndex, rowIndex);
        }
    }

    // Remove current Items
    public static void removeRowsFromGridPane(GridPane gridPane, int startRowIndex, int endRowIndex) {
        ObservableList<Node> children = gridPane.getChildren();
        children.removeIf(node -> GridPane.getRowIndex(node) >= startRowIndex && GridPane.getRowIndex(node) <= endRowIndex);
    }

    // For Items to Cart
    public static ObservableList<String> MapToList(Map<Item, Integer> itemsInCartMap) {
        List<String> itemsInCartList = new ArrayList<>();

        for (Map.Entry<Item, Integer> entry : itemsInCartMap.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();

            String itemDescription = item.getName() + " (" + quantity + "x)";
            itemsInCartList.add(itemDescription);
        }
        return FXCollections.observableArrayList(itemsInCartList);
    }

}

*/
