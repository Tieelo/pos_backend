package controller.terminal;

import model.Cart;
import model.Receipt;
import model.ScannerSingleton;
import view.terminal.MenuView;
import java.util.Scanner;
public class MenuController {
    private static final Scanner scanner = ScannerSingleton.getInstance().getScanner();   // zum Einlesen der Benutzereinzugaben
    private final PrintItemController printItemController;
    private static MenuController instance = null;
    private final InputController inputController;
    private Cart cart;
    private Receipt receipt;
    public MenuController() {
        printItemController = new PrintItemController();
        receipt = new Receipt();
        inputController = InputController.getInstance();
        cart = Cart.getInstance();
    }
    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }
        return instance;
    }

    public void menuOptions() {
        MenuView view = new MenuView();
        boolean exit = false;

        mainloop:
        while(!exit){
            view.displayMenu();

            System.out.print("Bitte wählen Sie eine Option: ");
            int option = scanner.nextInt();
            inputController.swallowLeftOverNewline();

            switch(option) {
                case 1: // ItemsDisplay
                    view.displayItems();
                    int option1 = scanner.nextInt();
                    inputController.swallowLeftOverNewline();
                    switch (option1){
                        case 1: //AlleItems
                            printItemController.fetchAndPrintAllItems();
                            view.pressEnterToContinue();
                            break;
                        case 2: //Gruppen
                            printItemController.fetchAndPrintItemsByGroup();
                            view.pressEnterToContinue();
                            break;
                    }
                    break;
                case 2:
                    startSale();// Aufruf des Verkaufscontrollers um den Verkaufsprozess zu starten.
                    break;
                case 3:
                    // Aufruf der Methode, welche die letzten Rechnungen zeigt.
                    break;
                case 4:
                    // Aufruf der Methode, welche eine Storno-Operation durchführt.
                    break;
                case 5:
                    // Programm beenden.
                    exit = true;
                    break;
                default:
                    System.out.println("Ungültige Option, bitte wählen Sie eine gültige Option");
                    break;
            }
        }
    }
    public boolean sellingMenuOptions(){
        boolean exit = false;
        MenuView menuView = new MenuView();

        sellingMenuLoop:
        while (!exit){
            System.out.println("Bitte wählen sie eine Option: ");
            menuView.displaySellingMenu();

            int option = scanner.nextInt();
            inputController.swallowLeftOverNewline();
            switch (option){
                case 1://Warenkorb anzeigen
                    cart.printCart();
                    break;
                case 2://neue Gruppe
                    printItemController.fetchAndPrintSellingItems();
                    exit = true;
                    break;
                case 3://Sofort-Storno
                    cart.printCart();
                    System.out.println("Welches Item in welcher Menge zurücklegen? <ID Menge>");
                    String input = scanner.nextLine();
                    //inputController.swallowLeftOverInput();
                    int[] idAndAmount = inputController.handleInput(input);
                    if (idAndAmount != null) {
                        cart.removeItemById(idAndAmount);
                    } else {
                        System.out.println("Invalid Input. Please enter Item ID and Amount correctly");
                    }
                    break;
                case 4://leert den Warenkorb
                    cart.putCartBackToInventory();
                    exit = true;
                    break;
                case 5://Einkauf bezahlen
                    System.out.println("Bestätigung:");
                    if (inputController.stringToChar() == 'j'){
                        menuView.printReceipt();
                        cart.sellCart();
                        exit = true;
                        return true;
                    }
                        //todo: Invoice and Receipt
                    break;
                case 6: //weiter einkaufen
                    printItemController.fetchAndPrintItemsByGroup();
                    exit = true;
                    break;
            }
        }
        return exit;
    }
    public void startSale() {
        printItemController.fetchAndPrintSellingItems();

        sellingLoop:
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();
            //inputController.swallowLeftOverInput();
            if (input.equals("fertig")){
                break;
            } else if (input.equals("0")||input.equals("menu")) {
                boolean doneShopping = this.sellingMenuOptions();
                if (doneShopping){
                    break sellingLoop;
                }
            } else {
                int[] idAndAmount = inputController.handleInput(input);
                if (idAndAmount != null){
                    cart.fillCart(idAndAmount);
                }
            }
        }
    }
}
