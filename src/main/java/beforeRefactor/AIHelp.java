package beforeRefactor;

import database.DatabaseConnection;
import model.ItemsObject;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AIHelp {
    private static DatabaseConnection dbConnection;
    private static final int maxDisplayItems = 16;
    private static final List<ItemsObject> ITEMS_LIST = new ArrayList<>();
    private static double totalPrice;
    private static Scanner scanner = new Scanner(System.in);
    private static int countOfItems;
    private static int totalSQLItems = 0; //Counter für die Anzahl der items im Array
    private static final ArrayList<ItemsObject> itemArrayList = new ArrayList<>(); //array für das Inventar

    public static void main(String[] args) {
        boolean nextPurchase = true;

        while (nextPurchase) {
            dbConnection = DatabaseConnection.getInstance();
            clearScreen();
            System.out.println("Enter the <ID> of the group");
            populateItemsList();
            displayPurchaseOptions();
            resetCounters();

            String userInput;
            List<String> receiptItems = new ArrayList<>();
            List<String> boughtItemsDetails = new ArrayList<>();

            while (scanner.hasNextLine()) {
                userInput = scanner.nextLine();
                handleUserInput(userInput, receiptItems, boughtItemsDetails);
            }

            processPurchase(receiptItems, boughtItemsDetails);
            System.out.println("Next purchase? (y/n)");
            nextPurchase = scanner.next().equals("y");
        }
    }

    private static void handleUserInput(String userInput, List<String> receiptItems, List<String> boughtItemsDetails) {
        switch (userInput) {
            case "done":
                break;
            case "groups":
                populateItemsList();
                displayPurchaseOptions();
                break;
            default:
                addItemToCart(userInput, receiptItems, boughtItemsDetails);
                break;
        }
    }

    private static void addItemToCart(String userInput, List<String> receiptItems, List<String> boughtItemsDetails) {
        String[] userInputParts = userInput.split(" ");

        if (userInputParts.length < 2) {
            return;
        }
        String itemName = userInputParts[0];
        double itemQuantity = Double.parseDouble(userInputParts[1].replaceAll(",", "."));
        updateCart(itemName, itemQuantity, receiptItems, boughtItemsDetails);
    }

    private static void updateCart(String itemName, double itemQuantity, List<String> receiptItems, List<String> boughtItemsDetails) {
        Optional<ItemsObject> optionalItem = ITEMS_LIST.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName))
                .findFirst();

        if (!optionalItem.isPresent()) {
            return;
        }

        ItemsObject item = optionalItem.get();
        double newStock = item.getStock() - itemQuantity;

        if (newStock >= 0) {
            receiptItems.add(formatReceiptItem(itemName, item.getPrice(), itemQuantity));
            boughtItemsDetails.add(formatBoughtItemsDetails(item.getId(), newStock, itemName, item.getPrice(), itemQuantity));
            item.setStock(newStock);
            totalPrice += itemQuantity * item.getPrice();
            countOfItems++;
        } else {
            System.out.println("Insufficient Stock!");
        }
    }

    private static String formatReceiptItem(String itemName, double itemPrice, double itemQuantity) {
        return String.format("%-19s €%.2f %-12s %.2f", itemName, itemPrice, "", itemQuantity);
    }

    private static String formatBoughtItemsDetails(int itemId, double newStock, String itemName, double itemPrice, double itemQuantity) {
        return String.format("%d %.2f %s %.2f %.2f", itemId, newStock, itemName, itemPrice, itemQuantity);
    }

    private static void processPurchase(List<String> receiptItems, List<String> boughtItemsDetails) {
        System.out.println("Proceed with purchase? (y/n)");

        if (scanner.next().equals("y")) {
            updateInventory(boughtItemsDetails);
            //exportInvoice
            System.out.println("\n Inventory updated");
            displayReceiptOption(receiptItems);
        } else {
            System.out.println("No charges on inventory");
        }
    }

    private static void displayReceiptOption(List<String> receiptItems) {
        System.out.println("\n Do you want a receipt? (y/n)");
        if (scanner.next().equals("y")) {
            printPurchaseReceipt(receiptItems);
        }
    }

    //Bunch of helper methods to refactor main.main functionality
    private static void populateItemsList() {
        printGroups();
        System.out.println("Which group? <ID>");
        int groupChoice = scanner.nextInt();
        getItemsFromDB(groupChoice);
    }

    private static void displayPurchaseOptions() {
        System.out.println(
                """
                        Enter '<Drink> <Quantity>' to be sold followed by 'Enter'
                        Reselect the article group with 'groups'
                        When you're done, write 'done' to end the purchase
                        """
        );
        printItems();
    }

    private static void resetCounters() {
        totalPrice = 0;
        countOfItems = 0;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printGroups() {
        try {
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT groups_id, group_names FROM groups order by groups_id;"
            );
            System.out.print("ID  Gruppennamen \n");
            while (rs.next()) {
                int groups_id = rs.getInt("groups_id");
                String group_names = rs.getString("group_names");
                System.out.printf("%-3d %s \n", groups_id, group_names);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void getItemsFromDB(int group_input) {
        try {
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT i.items_id, i.item_name, g.measurement, i.item_price, i.item_amount, g.group_names " +
                            "FROM items i " +
                            "JOIN groups g " +
                            "on i.groups_id = g.groups_id " +
                            "WHERE i.groups_id=" +
                            group_input +
                            ";"
            );
            totalSQLItems = 0;

            while (rs.next() && totalSQLItems <= maxDisplayItems) {
                int items_id = rs.getInt("items_id");
                String item_name = rs.getString("item_name");
                String measurement = rs.getString("measurement");
                double item_price = rs.getDouble("item_price");
                double stock = rs.getDouble("item_amount");
                String group_names = rs.getString("group_names");

                itemArrayList.add(
                        new ItemsObject(
                                items_id,
                                item_name,
                                measurement,
                                item_price,
                                stock,
                                group_names
                        ));
                totalSQLItems++;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    public static void updateInventory(List<String> Einkauf) {
        try {
            Statement stmt = dbConnection.getConnection().createStatement();
            String[] export;
            int itemsId;
            double stock;
            for (String i : Einkauf) {
                if (i == null) {
                    break;
                }
                export = i.split(" ");
                itemsId = Integer.parseInt(export[0]);
                export[1] = export[1].replaceAll(",", ".");
                stock = Double.parseDouble(export[1]);
                String query =
                        "UPDATE items set item_amount = " +
                                stock +
                                " where items_id =" +
                                itemsId +
                                ";";
                stmt.executeUpdate(query);
                dbConnection.getConnection().commit();
            }
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void printPurchaseReceipt(List<String> receiptItems) {
        System.out.println("\n\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("           		   Super Bar\n");
        System.out.println("                   Quittung");
        System.out.println("                  ~~~~~~~~~\n");
        System.out.println(
                "Artikel        " + "Preis pro Einheit       " + "Menge"
        );
        System.out.println("______________________________________________\n");
        for (int i = 0; i < countOfItems; i++) {
            System.out.println(receiptItems.get(i));
        }
        System.out.println("\nMenge der Artikel insgesamt : " + countOfItems);
        System.out.printf("\nGesamtbetrag: %.2f€ \n", totalPrice);
        System.out.println("\n               Ende der Quittung");
        System.out.println("\nVielen Dank für Ihren Besuch");
        System.out.println("             Kommen Sie gerne wieder");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    public static void printItems() {
        for (int i = 0; i < totalSQLItems; i++) {
            System.out.println(itemArrayList.get(i).toString());
        }
    }

}
