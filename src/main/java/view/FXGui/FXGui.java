package view.FXGui;

import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.scene.control.ListView;
import model.ButtonCreation;
import model.Cart;
import model.Item;

import java.util.stream.Collectors;


public class FXGui extends Application {

    private ObservableList<String> items = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    public GridPane ButtonView = new GridPane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PoS Kassensystem");
        primaryStage.setResizable(false);


        //LEFT VIEW -- LEFT VIEW -- LEFT VIEW -- LEFT VIEW -- LEFT VIEW -- LEFT VIEW --

        // GridPane Cart and Total Amount
        GridPane CartView = new GridPane();
        CartView.setVgap(10);
        CartView.setHgap(10);
        CartView.setPadding(new Insets(10));
        CartView.setAlignment(Pos.BASELINE_LEFT);

        // Cart
        ListView<String> listView = new ListView<>(items);
        Cart cart = Cart.getInstance();
        CartView.add(listView, 0,0);
        listView.setItems(FXCollections.observableArrayList(cart.getItemsInCart().keySet().stream().map(Item::getName).collect(Collectors.toList())));
        items.add("Item 1");
        items.add("Item 2");
        // Total $ set Box Horizontal
        HBox totalAmount = new HBox();
        totalAmount.setAlignment(Pos.CENTER);

        // Label for Total Amount
        int total = 10;
        Label totalAmountLabel = new Label("   Total: " + total + "$   ");
        totalAmountLabel.setId("totalAmountLabel");
        totalAmount.getChildren().add(totalAmountLabel);


        // Add to GridPane
        CartView.add(totalAmount, 0, 1);




        //RIGHT VIEW -- RIGHT VIEW -- RIGHT VIEW -- RIGHT VIEW -- RIGHT VIEW -- RIGHT VIEW --

        GridPane ButtonView = new GridPane();
        ButtonView.setVgap(10);
        ButtonView.setHgap(10);
        ButtonView.setPadding(new Insets(10));
        ButtonView.setAlignment(Pos.BASELINE_RIGHT);
        ButtonCreation.initButtonView(ButtonView);

        // HBox for ButtonRow1
        HBox buttonRow1 = new HBox();
        buttonRow1.setAlignment(Pos.TOP_CENTER);
        buttonRow1.setSpacing(10);

        // Buttons for Row 1
        Button rechnungen = new Button("Rechnungen");
        rechnungen.setId("rechnungen");
        buttonRow1.getChildren().add(rechnungen);
        rechnungen.setMinWidth(100);
        rechnungen.setMinHeight(50);

        Button warenkorbLeeren = new Button("Warenkorb leeren");
        warenkorbLeeren.setId("warenkorb-leeren");
        buttonRow1.getChildren().add(warenkorbLeeren);
        warenkorbLeeren.setMinWidth(100);
        warenkorbLeeren.setMinHeight(50);

        Button sofortStorno = new Button("SofortStorno");
        sofortStorno.setId("sofort-storno");
        buttonRow1.getChildren().add(sofortStorno);
        sofortStorno.setMinWidth(100);
        sofortStorno.setMinHeight(50);

        Button checkout = new Button("Checkout");
        checkout.setId("checkout");
        buttonRow1.getChildren().add(checkout);
        checkout.setMinWidth(100);
        checkout.setMinHeight(50);

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
        scene.getStylesheets().add
                (getClass().getResource("/styles.css").toExternalForm());
        primaryStage.show();
    }
}
