package controller;

import view.MenuView;

import java.util.Scanner;

public class MenuController {
    private Scanner scan = new Scanner(System.in);   // zum Einlesen der Benutzereinzugaben
    private PrintItemController printItemController;
    public MenuController() {
        printItemController = new PrintItemController();
    }
    public void menuOptions() {
        MenuView view = new MenuView();
        boolean exit = false;

        while(!exit){
            view.displayMenu();

            System.out.println("Bitte wählen Sie eine Option");
            int option = scan.nextInt();

            switch(option) {
                case 1: // ItemsDisplay
                    view.displayItems();
                    int option1 = scan.nextInt();
                    switch (option1){
                        case 1: //AlleItems
                            printItemController.fetchAndPrintAllItems();
                            view.pressEnterToContinue();
                            break;
                        case 2: //Gruppen
                            printItemController.fetchAndPrintGroups();
                            view.pressEnterToContinue();
                            break;
                    }
                    break;
                case 2:
                    // Aufruf des Verkaufscontrollers um den Verkaufsprozess zu starten.
                    break;
                case 3:
                    // Aufruf der Methode, welche die letzten Rechnungen zeigt.
                    break;
                case 4:
                    // Aufruf der Methode, welche eine Storno-Operation durchführt.
                    break;
                case 5:
                    // Programm beenden.
                    exit = true;
                    break;
                default:
                    System.out.println("Ungültige Option, bitte wählen Sie eine gültige Option");
                    break;
            }
        }
    }
}
