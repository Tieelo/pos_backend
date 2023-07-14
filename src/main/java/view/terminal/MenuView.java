package view.terminal;

import java.util.Scanner;

public class MenuView {
public void displayMenu(){
    System.out.println("1. Anzeige Items");
    System.out.println("2. Verkaufen");
    System.out.println("3. Letzte Rechnungen anzeigen");
    System.out.println("4. Storno");
    System.out.println("5. Beenden");
    }

    public void displayItems(){
        System.out.println("1. Alle Items");
        System.out.println("2. Nach Gruppe");
    }
    public void pressEnterToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
