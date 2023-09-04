package controller.terminal;

import model.Groups;
import model.Inventory;
import model.Item;
import model.ScannerSingleton;
import view.terminal.TerminalView;

import java.util.List;
import java.util.Scanner;

public class PrintItemController {
    private Scanner scanner = ScannerSingleton.getInstance().getScanner();
    private Inventory inventory;
    private TerminalView terminalView;
    private InputController inputController;

    public PrintItemController(){
        inventory = Inventory.getInstance();
        terminalView = new TerminalView();
        inputController = InputController.getInstance();
    }
    public void fetchAndPrintGroups(){
        List<Groups> groups = inventory.getGroups();
        terminalView.printGroups(groups);
    }
    public void fetchAndPrintItemsByGroup(){
        fetchAndPrintGroups();
        int group = scanner.nextInt();
        inputController.swallowLeftOverNewline();
        List<Item> items = inventory.getItems(group);
        terminalView.printItems(items);
    }
    public void fetchAndPrintAllItems(){
        List<Item> items = inventory.getItems(null);
        terminalView.printItems(items);
    }

    public void fetchAndPrintSellingItems(){
        fetchAndPrintGroups();
        int group = scanner.nextInt();
        inputController.swallowLeftOverNewline();
        terminalView.chooseItems();
        List<Item> items = inventory.getItems(group);
        terminalView.printItems(items);
    }
}
