package model;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Item> getItems(Integer group_id) {
        if (group_id == null){
            return items;
        }else {
            // Filter items by group ID using Java 8 Stream API
            return items.stream()
                    .filter(item -> item.getGroupId() == group_id)
                    .collect(Collectors.toList());
        }
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
    public Item fillCartfromInventory(int[] idAndAmount){
        Item item = getItemById(idAndAmount[0]);
        // todo: reduce Inventory by amount
        return item;
    }
}