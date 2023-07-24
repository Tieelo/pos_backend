package controller;

import model.Cart;
import model.Inventory;
import view.terminal.SellingView;

import java.util.Scanner;

public class SellingController {
    private static final Scanner scanner = new Scanner(System.in);
    private PrintItemController printItemController;
    private SellingView sellingView;
    private Cart cart;
    private Inventory inventory;
    public SellingController(){
        printItemController = new PrintItemController();
        cart = Cart.getInstance();
        inventory = Inventory.getInstance();
    }
    public void startSale(){
        printItemController.fetchAndPrintSellingItems();
    }
    public void processInput(){
        while (scanner.hasNextLine()){
            String input = scanner.nextLine();
            if (input.equals("fertig")){
                break;
            } else if (input.equals("gruppen")) {
                printItemController.fetchAndPrintItemsByGroup();
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
