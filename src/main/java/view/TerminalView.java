package view;

import model.database.Invoice;
import model.objects.*;

import java.util.List;
import java.util.Scanner;

public class TerminalView {
    Scanner scanner = ScannerSingleton.getInstance().getScanner();
    Receipt receipt = new Receipt();
    Cart cart = Cart.getInstance();
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
    public void printReceipt (){
        List<String> receiptLines = receipt.getReceiptLines();
        System.out.println("\n\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("                       Super Bar");
        System.out.println("                       Quittung");
        System.out.println("                      ~~~~~~~~~");
        System.out.println("Artikel            Preis pro Einheit              Menge");
        System.out.println("________________________________________________");
        for (String line : receiptLines) {System.out.println(line);}
        System.out.println("\nMenge der Artikel insgesamt : " + receipt.getTotalAmount());
        System.out.printf("\nGesamtbetrag: %.2f€ \n", receipt.getTotalCost());
        System.out.println("\n               Ende der Quittung");
        System.out.println("\nVielen Dank für Ihren Besuch");
        System.out.println("             Kommen Sie gerne wieder");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }
    public void printGroups(List<Groups> groups){
        System.out.println("Welche Gruppe? <ID>");
        System.out.println("ID  Gruppennamen \n");
        for (Groups groupObject : groups) {
            System.out.printf("%-3d %s \n", groupObject.getId(), groupObject.getName());
        }
    }
    public void printItems(List<Item> items) {
        for (Item item : items) {
            System.out.println(item.toString());
        }
    }
    public void viewInvoices() {
        Invoice invoice = new Invoice();
        List<String> invoices = invoice.getInvoices();
        for (String i : invoices) {
            System.out.println(i);
        }
    }
}
