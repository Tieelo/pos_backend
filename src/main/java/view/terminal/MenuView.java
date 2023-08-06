package view.terminal;

import model.ScannerSingleton;

import java.util.Scanner;

public class MenuView {
    Scanner scanner = ScannerSingleton.getInstance().getScanner();
    public void displayMenu() {
        System.out.println("1. Anzeige Items");
        System.out.println("2. Verkaufen");
        System.out.println("3. Letzte Rechnungen anzeigen");
        System.out.println("4. Storno");
        System.out.println("5. Beenden");
    }

    public void displayItems() {
        System.out.println("1. Alle Items");
        System.out.println("2. Nach Gruppe");
    }

    public void pressEnterToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void displaySellingMenu() {
        System.out.println("1. Anzeige Warenkorb");
        System.out.println("2. Andere Gruppe waehlen");
        System.out.println("3. Item aus Warenkorb entfernen");
        System.out.println("4. Einkauf verwerfen und neu starten");
        System.out.println("5. Einkauf bezahlen");
        System.out.println("6. weiter einkaufen");
    }

    public void chooseItems() {
        System.out.print(
                """
                        Gib '<GetränkeID> <Menge>' ein, die in den Warenkorb gelegt werden sollen gefolgt von 'Enter'
                        Gib '0' ein um ins Menu zu gelangen\s
                        """
        );
    }
}
