package controller.terminal;

import model.Cart;
import view.terminal.MenuView;
import java.util.Scanner;
public class MenuController {
    private static final Scanner scanner = new Scanner(System.in);   // zum Einlesen der Benutzereinzugaben
    private final PrintItemController printItemController;
    private final SellingController sellingController;
    public MenuController() {
        printItemController = new PrintItemController();
        sellingController = new SellingController();
    }
    public void menuOptions() {
        MenuView view = new MenuView();
        boolean exit = false;

        while(!exit){
            view.displayMenu();

            System.out.print("Bitte wählen Sie eine Option: ");
            int option = scanner.nextInt();

            switch(option) {
                case 1: // ItemsDisplay
                    view.displayItems();
                    int option1 = scanner.nextInt();
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
                    sellingController.startSale();// Aufruf des Verkaufscontrollers um den Verkaufsprozess zu starten.
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
    public void sellingMenuOptions(){
        boolean exit = false;
        MenuView view = new MenuView();
        InputController inputController = new InputController();
        Cart cart = Cart.getInstance();

        while (!exit){
            view.displaySellingMenu();
            System.out.print("Bitte wählen sie eine Option: ");

            int option = scanner.nextInt();
            switch (option){
                case 1:
                    cart.printCart();
                    break;
                case 2:
                    printItemController.fetchAndPrintSellingItems();
                    break;
                case 3:
                    cart.printCart();
                    System.out.println("Welches Item in welcher Menge zurücklegen? <ID Menge>");
                    String input = scanner.nextLine();
                    int[] idAndAmount = inputController.handleInput(input);
                    cart.removeItemById(idAndAmount);
                    break;
            }
        }
    }
}
