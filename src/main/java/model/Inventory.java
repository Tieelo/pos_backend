package model;

import java.util.List;

public class Inventory {
    private static Inventory instance;
    private final ItemsManager itemsManager;
    private List<Groups> groups;
    private List<Item> items;
    private Inventory() {
        itemsManager = new ItemsManager();
        fetchInventory();
    }
    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }
    public List<Groups> getGroups() {
        return groups;
    }
    public List<Item> getItems() {
        return items;
    }
    private void fetchInventory() {
        groups = itemsManager.getGroups();
        items = itemsManager.getItems(null);
    }
    public Item getItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null; // return null, wenn kein Artikel mit der gegebenen ID gefunden wurde
    }
}