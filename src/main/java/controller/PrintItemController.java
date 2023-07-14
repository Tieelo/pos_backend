package controller;

import model.GroupObject;
import model.ItemsManager;
import model.ItemsObject;
import view.terminal.ItemView;

import java.util.List;
import java.util.Scanner;

public class PrintItemController {
    private Scanner scan = new Scanner(System.in);
    private final ItemsManager itemsManager;
    private final ItemView itemView;

    public PrintItemController(){
        itemsManager = new ItemsManager();
        itemView = new ItemView();
    }
    public void fetchAndPrintGroups(){
        List<GroupObject> groups = itemsManager.getGroups();
        itemView.printGroups(groups);
    }
    public void fetchAndPrintItemsByGroup(){
        fetchAndPrintGroups();
        int group = scan.nextInt();
        List<ItemsObject> items = itemsManager.getItems(group);
        itemView.printItems(items);
    }
    public void fetchAndPrintAllItems(){
        List<ItemsObject> items = itemsManager.getItems(null);
        itemView.printItems(items);
    }
}
