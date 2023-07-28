package controller.terminal;

import model.Groups;
import model.ItemsManager;
import model.Item;
import view.terminal.ItemView;
import view.terminal.SellingView;

import java.util.List;
import java.util.Scanner;

public class PrintItemController {
    private Scanner scan = new Scanner(System.in);
    private final ItemsManager itemsManager;
    private final ItemView itemView;
    private SellingView sellingView;

    public PrintItemController(){
        itemsManager = new ItemsManager();
        itemView = new ItemView();
    }
    public void fetchAndPrintGroups(){
        List<Groups> groups = itemsManager.getGroups();
        itemView.printGroups(groups);
    }
    public void fetchAndPrintItemsByGroup(){
        fetchAndPrintGroups();
        int group = scan.nextInt();
        List<Item> items = itemsManager.getItems(group);
        itemView.printItems(items);
    }
    public void fetchAndPrintAllItems(){
        List<Item> items = itemsManager.getItems(null);
        itemView.printItems(items);
    }

    public void fetchAndPrintSellingItems(){
        fetchAndPrintGroups();
        int group = scan.nextInt();
        sellingView.chooseItems();
        List<Item> items = itemsManager.getItems(group);
        itemView.printItems(items);
    }
}
