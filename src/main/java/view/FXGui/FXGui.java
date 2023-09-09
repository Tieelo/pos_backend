package view.FXGui;

import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import model.gui.ButtonCreation;
import model.Cart;


public class FXGui extends Application {

    static Cart cart = Cart.getInstance();
    private static ListView<String> listView = new ListView<>();
    private static double total;
    public static Label totalAmountLabel = new Label("   Total: " + total + "$   ");
    static HBox totalAmount = new HBox();


    public static void main(String[] args) {
        launch(args);
    }

    public GridPane ButtonView = new GridPane();
    public GridPane CartView = new GridPane();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PoS Kassensystem");
        primaryStage.setResizable(false);


        //LEFT VIEW -- LEFT VIEW -- LEFT VIEW -- LEFT VIEW -- LEFT VIEW -- LEFT VIEW --

        // GridPane CartView for Cart
        CartView.setVgap(10);
        CartView.setHgap(10);
        CartView.setPadding(new Insets(10));
        CartView.setAlignment(Pos.BASELINE_LEFT);

        // Add listView to CartView
        CartView.add(listView, 0, 0);


        // Total Amount on Bottom of left Side
        totalAmount.setAlignment(Pos.CENTER);
        Label totalAmountLabel = controller.FXGui.FXGuiController.getTotalAmountLabel();
        totalAmount.getChildren().add(totalAmountLabel);
        CartView.add(totalAmount, 0, 1);




        //RIGHT VIEW -- RIGHT VIEW -- RIGHT VIEW -- RIGHT VIEW -- RIGHT VIEW -- RIGHT VIEW --

        // GridPane ButtonView for Buttons
        ButtonView.setVgap(10);
        ButtonView.setHgap(10);
        ButtonView.setPadding(new Insets(10));
        ButtonView.setAlignment(Pos.BASELINE_RIGHT);
        ButtonCreation.initButtonView(ButtonView);


        // HBox for MiscButtons
        HBox buttonRow1 = new HBox();
        buttonRow1.setAlignment(Pos.TOP_CENTER);
        buttonRow1.setSpacing(10);

        // Buttons for Row1 (MiscButtons)

        // TODO
        Button rechnungen = new Button("Rechnungen");
        rechnungen.setId("rechnungen");
        buttonRow1.getChildren().add(rechnungen);
        rechnungen.setMinWidth(100);
        rechnungen.setMinHeight(50);

        // Empty Cart Button
        Button warenkorbLeeren = new Button("Warenkorb leeren");
        warenkorbLeeren.setId("warenkorb-leeren");
        buttonRow1.getChildren().add(warenkorbLeeren);
        warenkorbLeeren.setMinWidth(100);
        warenkorbLeeren.setMinHeight(50);
        warenkorbLeeren.setOnAction(new EventHandler<ActionEvent>() {
            // Event on ButtonClick
            @Override
            public void handle(ActionEvent actionEvent) {
                cart.putCartBackToInventory();
                controller.FXGui.FXGuiController.clearCart();

            }
        });

        // TODO
        Button sofortStorno = new Button("SofortStorno");
        sofortStorno.setId("sofort-storno");
        buttonRow1.getChildren().add(sofortStorno);
        sofortStorno.setMinWidth(100);
        sofortStorno.setMinHeight(50);


        // Proceed to Checkout and Confirm Order
        Button checkout = new Button("Checkout");
        checkout.setId("checkout");
        buttonRow1.getChildren().add(checkout);
        checkout.setMinWidth(100);
        checkout.setMinHeight(50);
        checkout.setOnAction(new EventHandler<ActionEvent>() {
            // Event on ButtonClick
            @Override
            public void handle(ActionEvent actionEvent) {
                cart.sellCart();
                controller.FXGui.FXGuiController.clearCart();
            }
        });

        // Add buttons to GridPane (ButtonView)
        ButtonView.add(buttonRow1, 0, 0);


        // Buttons for Groups
        HBox groupRow1 = ButtonCreation.createGroupRow(1,4);
        HBox groupRow2 = ButtonCreation.createGroupRow(5,8);
        ButtonView.add(groupRow1, 0, 3);
        ButtonView.add(groupRow2, 0, 4);

        // Buttons for Items
        HBox itemRow1 = ButtonCreation.createItemRow(1,4);
        HBox itemRow2 = ButtonCreation.createItemRow(5,8);
        HBox itemRow3 = ButtonCreation.createItemRow(9,12);
        HBox itemRow4 = ButtonCreation.createItemRow(13,16);
        ButtonView.add(itemRow1, 0, 7);
        ButtonView.add(itemRow2, 0, 8);
        ButtonView.add(itemRow3, 0, 9);
        ButtonView.add(itemRow4, 0, 10);



        // HBox for Layout
        HBox Kassensystem = new HBox(CartView, ButtonView);

        Scene scene = new Scene(Kassensystem , 720, 480);
        primaryStage.setScene(scene);

        //css style sheet
        scene.getStylesheets().add
                (getClass().getResource("/styles.css").toExternalForm());

        primaryStage.show();
    }

    public static HBox getTotalAmount() {
        return totalAmount;
    }

    public static Label getTotalAmountLabel() {
        return totalAmountLabel;
    }

    public static double getTotal() {
        return total;
    }

    public static Cart getCart() {
        return cart;
    }

    public GridPane getButtonView() {
        return ButtonView;
    }

    public static ListView<String> getListView() {
        return listView;
    }


}
