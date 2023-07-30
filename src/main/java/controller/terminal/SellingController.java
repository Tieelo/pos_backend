package controller.terminal;

import model.Cart;
import model.Inventory;
import view.terminal.MenuView;

import java.util.Scanner;

public class SellingController {
    private static final Scanner scanner = new Scanner(System.in);
    private PrintItemController printItemController;
    private Cart cart;
    private Inventory inventory;
    private MenuView menuView;

    public SellingController() {
        printItemController = new PrintItemController();
        cart = Cart.getInstance();
        inventory = Inventory.getInstance();
        menuView = new MenuView();
    }

    public void startSale() {
        printItemController.fetchAndPrintSellingItems();
        processInput();
    }

    public void processInput() {
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("fertig")) {
                break;
            } else if (input.equals("0")) {
                menuView.displaySellingMenu();
            } else if(input == null || input.isEmpty()) {
                System.out.println("Ungueltige Eingabe (Eingabe ist leer)");
            } else {
                int[] idAndAmount = seperateString(input);
                if (idAndAmount.length != 2 ){
                    System.out.println("Ungueltige Eingabe (Falsche Menge an Integer)");
                    continue;
                }
                cart.fillCart(idAndAmount);
                cart.printCart();
            }
        }
    }
    public int[] seperateString(String string) {
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
}
