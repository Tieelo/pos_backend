package view;

import model.GroupObject;
import model.ItemsObject;

import java.util.List;

public class ItemView {
    public void printGroups(List<GroupObject> groups){
        System.out.println("Welche Gruppe? <ID>");
        System.out.println("ID  Gruppennamen \n");
        for (GroupObject groupObject : groups) {
            System.out.printf("%-3d %s \n", groupObject.getId(), groupObject.getName());
        }
    }
public void displayItems(List<ItemsObject> items) {
        for (ItemsObject item : items) {
            System.out.println(item.toString());
        }
    }

/*    public void printReceipt(List<ItemManager.Item> receiptItems, double totalPrice, int countOfItems) {
        System.out.println("\n\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("           		   Super Bar\n");
        System.out.println("                   Quittung");
        System.out.println("                  ~~~~~~~~~\n");
        System.out.println(
                "Artikel        " + "Preis pro Einheit       " + "Menge"
        );
        System.out.println("______________________________________________\n");
        for (ItemManager.Item item : receiptItems) {
            System.out.println(item);
        }
        System.out.println("\nMenge der Artikel insgesamt : " + countOfItems);
        System.out.printf("\nGesamtbetrag: %.2f€ \n", totalPrice);
        System.out.println("\n               Ende der Quittung");
        System.out.println("\nVielen Dank für Ihren Besuch");
        System.out.println("             Kommen Sie gerne wieder");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }*/
}
