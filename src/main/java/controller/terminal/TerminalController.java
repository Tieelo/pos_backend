package controller.terminal;

import model.*;
import view.terminal.TerminalView;

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

    //Other methods can be merged within this class
}
