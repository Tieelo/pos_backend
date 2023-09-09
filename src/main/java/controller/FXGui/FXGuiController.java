package controller.FXGui;


import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import model.Inventory;
import model.*;
import model.gui.ButtonCreation;


public class FXGuiController {



    private Inventory inventory;
    private Cart cart = Cart.getInstance();




    public FXGuiController() {
        inventory = Inventory.getInstance();
    }

    private static double total = view.FXGui.FXGui.getTotal();
    private static HBox totalAmount = view.FXGui.FXGui.getTotalAmount();

    private static Label totalAmountLabel = view.FXGui.FXGui.getTotalAmountLabel();

    private static ListView<String> listView = view.FXGui.FXGui.getListView();

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
        controller.FXGui.FXGuiController.setTotal(0);
        controller.FXGui.FXGuiController.updateTotal();
        controller.FXGui.FXGuiController.updateLabel();
        totalAmount.getChildren().clear();
        totalAmount.getChildren().add(totalAmountLabel);
    }

    public static void addToList() {
        Cart cart = Cart.getInstance();
        ListView<String> listView = view.FXGui.FXGui.getListView();
        listView.setItems(ButtonCreation.MapToList(cart.getItemsInCart()));
        controller.FXGui.FXGuiController.updateTotal();
        controller.FXGui.FXGuiController.updateLabel();
        totalAmount.getChildren().clear();
        totalAmount.getChildren().add(view.FXGui.FXGui.getTotalAmountLabel());
    }
}
