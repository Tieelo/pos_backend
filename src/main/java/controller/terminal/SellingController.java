/*
package controller.terminal;

import model.Cart;
import model.Inventory;
import view.terminal.MenuView;

import java.util.Scanner;

public class SellingController {
    private static final Scanner scanner = new Scanner(System.in);
    private PrintItemController printItemController;
    private MenuController menuController;
    private InputController inputController;
    private Cart cart;
    private Inventory inventory;
    private MenuView menuView;

    public SellingController() {
        printItemController = new PrintItemController();
        menuController = new MenuController();
        inputController = new InputController();
        cart = Cart.getInstance();
        inventory = Inventory.getInstance();
        menuView = new MenuView();
    }

    public void startSale() {
        printItemController.fetchAndPrintSellingItems();
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();
            if (input.equals("fertig")){
                break;
            } else if (input.equals("0")||input.equals("menu")) {
                menuController.sellingMenuOptions();
            } else {
                int[] idAndAmount = inputController.handleInput(input);
                if (idAndAmount != null){
                    cart.fillCart(idAndAmount);
                }
            }
        }
    }
}
*/
