package model.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Cart;
import model.Groups;
import model.Inventory;
import model.Item;

import java.util.List;

public class ButtonCreation {
    static Inventory inventory = Inventory.getInstance();
    static Cart cart = Cart.getInstance();
    private static GridPane buttonView;

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

        List<Groups> groupsList = inventory.getGroups();
        //int currentId = start;

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
                    String buttonIdStr = ((Button) event.getSource()).getId(); // Die Button-ID als String abrufen
                    int buttonIdInt = Integer.parseInt(buttonIdStr); // Die Button-ID in einen Integer umwandeln
                    removeRowsFromGridPane(buttonView, 7, 10);
                    createRealItemRow(buttonIdInt);
                });
                groupRow.getChildren().add(groupButton);
                //currentId++;
            }
        }

        return groupRow;
    }

    public static void createRealItemRow(int buttonID) {
        int itemCount = 0;

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

                Button itemButton = new Button(itemName);
                itemButton.setId(""+itemId);
                itemButton.setMinWidth(100);
                itemButton.setMinHeight(50);
                idAndAmount[0] = itemId;
                itemButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        cart.fillCart(idAndAmount);
                    }
                });

                itemRows[itemCount / 4].getChildren().add(itemButton);
                if (itemCount % 4 == 3) {
                    addToButtonView(itemRows[itemCount / 4], 0, 7 + itemCount / 4);
                }

                itemCount++;
            }
        }

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

    public static void removeRowsFromGridPane(GridPane gridPane, int startRowIndex, int endRowIndex) {
        ObservableList<Node> children = gridPane.getChildren();
        children.removeIf(node -> GridPane.getRowIndex(node) >= startRowIndex && GridPane.getRowIndex(node) <= endRowIndex);
    }






}
