package controller.FXGui;

import javafx.scene.control.ListView;
import model.Inventory;
import model.Item;

import java.util.List;

public class ItemController {

    private Inventory inventory;

    public ItemController() {
        inventory = Inventory.getInstance();
    }
    public List<Item> fetchItemsByGroup(int group, ListView<Item> itemListView) {
        List<Item> items = inventory.getItems(group);

        // Clear the existing items in itemListView
        itemListView.getItems().clear();

        // Add the extracted items to itemListView
        itemListView.getItems().addAll(items);

        return items;
    }


}
