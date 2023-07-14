package main;

import controller.MenuController;
import view.MenuView;

public class main {
    private static final MenuController menuController = new MenuController();
    public static void main(String[] args) {
        menuController.menuOptions();
    }
}
