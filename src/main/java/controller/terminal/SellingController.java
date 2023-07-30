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
    public SellingController(){
        printItemController = new PrintItemController();
        cart = Cart.getInstance();
        inventory = Inventory.getInstance();
        menuView = new MenuView();
    }
    public void startSale(){
        printItemController.fetchAndPrintSellingItems();
        processInput();
    }
    public void processInput(){
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();
            if (input.equals("fertig")){
                break;
            } else if (input.equals("0")) {
                menuView.displaySellingMenu();
            } else {
                int[] idAndAmount = seperateString(input);
                fillCart(idAndAmount);
            }
        }
    }
    public void fillCart(int[] idAndAmount){
        cart.addItem(inventory.getItemById(idAndAmount[0]),idAndAmount[1]);
    }
    public int[] seperateString(String string){
        String[] tokens = string.split(" ");
        return null;
    }
}
