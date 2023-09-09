package controller;


import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import model.database.Inventory;
import model.gui.ButtonCreation;
import model.objects.Cart;
import view.FXGui;


public class FXGuiController {



    private Inventory inventory;
    private Cart cart = Cart.getInstance();




    public FXGuiController() {
        inventory = Inventory.getInstance();
    }

    private static double total = FXGui.getTotal();
    private static HBox totalAmount = FXGui.getTotalAmount();

    private static Label totalAmountLabel = FXGui.getTotalAmountLabel();

    private static ListView<String> listView = FXGui.getListView();

    public static double updateTotal() {
        Cart cart = Cart.getInstance();
            total = cart.getTotalCost();
        return total;
    }

    public static void setTotal(double total) {
        FXGuiController.total = total;
    }

    public static Label getTotalAmountLabel() {
        return totalAmountLabel;
    }

    public static Label updateLabel() {
        double total = updateTotal();
        String formattedTotal = String.format("%.1f", total);
        totalAmountLabel.setText("Total: " + formattedTotal + "$");

        return totalAmountLabel;
    }

    public static void clearCart() {
        listView.getItems().clear();
        FXGuiController.setTotal(0);
        FXGuiController.updateTotal();
        FXGuiController.updateLabel();
        totalAmount.getChildren().clear();
        totalAmount.getChildren().add(totalAmountLabel);
    }

    public static void addToList() {
        Cart cart = Cart.getInstance();
        ListView<String> listView = FXGui.getListView();
        listView.setItems(ButtonCreation.MapToList(cart.getItemsInCart()));
        FXGuiController.updateTotal();
        FXGuiController.updateLabel();
        totalAmount.getChildren().clear();
        totalAmount.getChildren().add(FXGui.getTotalAmountLabel());
    }
}
