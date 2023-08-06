package controller.terminal;

import model.Groups;
import model.Inventory;
import model.Item;
import model.ScannerSingleton;
import view.terminal.ItemView;
import view.terminal.MenuView;

import java.util.List;
import java.util.Scanner;

public class PrintItemController {
    private Scanner scanner = ScannerSingleton.getInstance().getScanner();
    private final ItemView itemView;
    private Inventory inventory;
    private MenuView menuView;
    private InputController inputController;

    public PrintItemController(){
        itemView = new ItemView();
        inventory = Inventory.getInstance();
        menuView = new MenuView();
        inputController = InputController.getInstance();
    }
    public void fetchAndPrintGroups(){
        List<Groups> groups = inventory.getGroups();
        itemView.printGroups(groups);
    }
    public void fetchAndPrintItemsByGroup(){
        fetchAndPrintGroups();
        int group = scanner.nextInt();
        inputController.swallowLeftOverNewline();
        List<Item> items = inventory.getItems(group);
        itemView.printItems(items);
    }
    public void fetchAndPrintAllItems(){
        List<Item> items = inventory.getItems(null);
        itemView.printItems(items);
    }

    public void fetchAndPrintSellingItems(){
        fetchAndPrintGroups();
        int group = scanner.nextInt();
        inputController.swallowLeftOverNewline();
        menuView.chooseItems();
        List<Item> items = inventory.getItems(group);
        itemView.printItems(items);
    }
}
