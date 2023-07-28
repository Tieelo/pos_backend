package main;

import controller.terminal.MenuController;
import model.Inventory;

public class main {
    private static final MenuController menuController = new MenuController();
    Inventory inventory = Inventory.getInstance();
    public static void main(String[] args) {
        menuController.menuOptions();
    }
}
