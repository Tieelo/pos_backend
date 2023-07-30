package main;

import controller.terminal.MenuController;
import model.Inventory;

public class main {
    private static final MenuController menuController = new MenuController();
    public static void main(String[] args) {
        menuController.menuOptions();
    }
}