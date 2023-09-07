package controller;

import model.*;
import view.TerminalView;
import java.util.List;
import java.util.Scanner;

public class TerminalController {
    private Scanner scanner = ScannerSingleton.getInstance().getScanner();
    private Inventory inventory;
    private TerminalView terminalView;
    private Cart cart;
    private Receipt receipt;
    private static TerminalController instance = null;

    private TerminalController() {
        inventory = Inventory.getInstance();
        terminalView = new TerminalView();
        cart = Cart.getInstance();
        receipt = new Receipt();
    }

    public static TerminalController getInstance() {
        if (instance == null) {
            instance = new TerminalController();
        }
        return instance;
    }

    //Methods handling Input
    public int[] handleInput(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("Ungueltige Eingabe (Eingabe ist leer)");
            return null;
        } else {
            int[] idAndAmount = separateString(input);
            if (idAndAmount.length != 2) {
                System.out.println("Ungueltige Eingabe (Falsche Menge an Integer)");
                return null;
            }
            return idAndAmount;
        }
    }

    public int[] separateString(String string) {
        if (string == null || string.isEmpty()) {
            System.out.println("Ungueltige Eingabe (Eingabe ist leer)");
            return new int[]{0, 0};
        }
        String[] tokens = string.split(" ");
        int[] idAndAmount = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            try {
                idAndAmount[i] = Integer.parseInt(tokens[i]);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return idAndAmount;
    }

    public char stringToChar() {
        String charInput = scanner.next();
        charInput = charInput.toLowerCase();
        return charInput.charAt(0);
    }

    public void swallowLeftOverNewline() {
        scanner.nextLine();
    }

    //Methods fetching and printing items
    public void fetchAndPrintGroups() {
        List<Groups> groups = inventory.getGroups();
        terminalView.printGroups(groups);
    }

    public void fetchAndPrintItemsByGroup() {
        fetchAndPrintGroups();
        int group = scanner.nextInt();
        swallowLeftOverNewline();
        List<Item> items = inventory.getItems(group);
        terminalView.printItems(items);
    }

    public void fetchAndPrintAllItems() {
        List<Item> items = inventory.getItems(null);
        terminalView.printItems(items);
    }

    public void fetchAndPrintSellingItems() {
        fetchAndPrintGroups();
        int group = scanner.nextInt();
        swallowLeftOverNewline();
        terminalView.chooseItems();
        List<Item> items = inventory.getItems(group);
        terminalView.printItems(items);
    }

    public void menuOptions() {
        boolean exit = false;

        mainloop:
        while (!exit) {
            terminalView.displayMenu();

            System.out.print("Bitte wählen Sie eine Option: ");
            int option = scanner.nextInt();
            swallowLeftOverNewline();

            switch (option) {
                case 1: // ItemsDisplay
                    terminalView.displayItems();
                    int option1 = scanner.nextInt();
                    swallowLeftOverNewline();
                    switch (option1) {
                        case 1: //AlleItems
                            fetchAndPrintAllItems();
                            terminalView.pressEnterToContinue();
                            break;
                        case 2: //Gruppen
                            fetchAndPrintItemsByGroup();
                            terminalView.pressEnterToContinue();
                            break;
                    }
                    break;
                case 2:
                    startSale();// Aufruf des Verkaufscontrollers um den Verkaufsprozess zu starten.
                    break;
                case 3:
                    terminalView.viewInvoices();
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

    public void startSale() {
        fetchAndPrintSellingItems();

        sellingLoop:
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("fertig")) {
                break sellingLoop;
            } else if (input.equals("0") || input.equals("menu")) {
                boolean doneShopping = this.sellingMenuOptions();
                if (doneShopping) {
                    break sellingLoop;
                }
            } else {
                int[] idAndAmount = handleInput(input);
                if (idAndAmount != null) {
                    cart.fillCart(idAndAmount);
                }
            }
        }
    }

    public boolean sellingMenuOptions() {
        boolean exit = false;

        sellingMenuLoop:
        while (!exit) {
            System.out.println("Bitte wählen sie eine Option: ");
            terminalView.displaySellingMenu();

            int option = scanner.nextInt();
            swallowLeftOverNewline();
            switch (option) {
                case 1://Warenkorb anzeigen
                    cart.printCart();
                    break;
                case 2://neue Gruppe
                    fetchAndPrintSellingItems();
                    exit = true;
                    break;
                case 3://Sofort-Storno
                    cart.printCart();
                    System.out.println("Welches Item in welcher Menge zurücklegen? <ID Menge>");
                    String input = scanner.nextLine();
                    int[] idAndAmount = handleInput(input);
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
                    System.out.println("Bestätigung mit j:");
                    if (stringToChar() == 'j') {
                        terminalView.printReceipt();
                        cart.sellCart();
                        exit = true;
                        return true;
                    }
                    break;
                case 6: //weiter einkaufen
                    fetchAndPrintItemsByGroup();
                    exit = true;
                    break;
            }
        }
        return exit;
    }
}