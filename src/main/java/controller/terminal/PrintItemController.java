package controller.terminal;

import model.Groups;
import model.Inventory;
import model.Item;
import view.terminal.ItemView;
import view.terminal.MenuView;

import java.util.List;
import java.util.Scanner;

public class PrintItemController {
    private Scanner scan = new Scanner(System.in);
    private final ItemView itemView;
    private Inventory inventory;
    private MenuView menuView;

    public PrintItemController(){
        itemView = new ItemView();
        inventory = Inventory.getInstance();
        menuView = new MenuView();
    }
    public void fetchAndPrintGroups(){
        List<Groups> groups = inventory.getGroups();
        itemView.printGroups(groups);
    }
    public void fetchAndPrintItemsByGroup(){
        fetchAndPrintGroups();
        int group = scan.nextInt();
        List<Item> items = inventory.getItems(group);
        itemView.printItems(items);
    }
    public void fetchAndPrintAllItems(){
        List<Item> items = inventory.getItems(null);
        itemView.printItems(items);
    }

    public void fetchAndPrintSellingItems(){
        fetchAndPrintGroups();
        int group = scan.nextInt();
        menuView.chooseItems();
        List<Item> items = inventory.getItems(group);
        itemView.printItems(items);
    }
}
