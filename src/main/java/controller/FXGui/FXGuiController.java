package controller.FXGui;


import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Inventory;
import model.Item;
import model.*;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


public class FXGuiController {



    private Inventory inventory;
    private Cart cart = Cart.getInstance();
    //private MenuView menuView;
    //private InputController inputController;

    public FXGuiController() {
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

    public void addItemToCart() {
        Cart.getInstance();

    }

    private void updateListView(ListView<String> listView, Cart cart) {
        listView.setItems(FXCollections.observableArrayList(cart.getItemsInCart().keySet().stream().map(Item::getName).collect(Collectors.toList())));
    }








}
