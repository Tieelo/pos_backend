package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private static Cart singleInstance = null;
    private HashMap<Items, Integer> items;

    private Cart() {
        items = new HashMap<>();
    }

    public static Cart getInstance() {
        if (singleInstance == null) {
            singleInstance = new Cart();
        }
        return singleInstance;
    }
    public void addItem(Items item, int amount) {
        items.put(item, amount);
    }
    public void removeItem(Items item, int amount) {
        Integer currentAmount = items.get(item);
        if (currentAmount != null) {
            if (amount < currentAmount) {
                items.put(item, currentAmount - amount);
            } else {
                // Wenn der zu entfernende Betrag größer oder gleich der aktuellen Menge ist, entfernen Sie das Element ganz
                items.remove(item);
            }
        } else {
            System.out.printf("Artikel %s nicht in Warenkorb gefunden%n", item);
        }
    }
    public double getTotalCost() {
        double totalCost = 0.0;
        for (Items item : items.keySet()) {
            totalCost += item.getPrice() * items.get(item);
        }
        return totalCost;
    }
    public int getItemCount() {
        return items.values().stream()
                .reduce(0, Integer::sum);
    }
    public void clear() {
        items.clear();
    }
    public boolean contains(Items item) {
        return items.containsKey(item);
    }
    public void printCart() {
        for (Items item : items.keySet()) {
            System.out.printf("%5d %-15s %.2f€ ", item.getId(), item.getName(), item.getPrice() * items.get(item));
        }
        // Add more methods as per your requirements
    }
}
