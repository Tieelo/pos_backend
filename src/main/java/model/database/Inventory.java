package model.database;

import model.objects.Groups;
import model.objects.Item;

import java.util.Comparator;
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
            return items.stream()
                    .sorted(Comparator.comparing(Item::getId)) // Hier sortieren wir die items nach Item::getId
                    .collect(Collectors.toList());
        }else {
            return items.stream()
                    .filter(item -> item.getGroupId() == group_id)
                    .collect(Collectors.toList());
        }
    }
    private void fetchInventory() {
        groups = itemsManager.getGroups();
        items = itemsManager.getItems(null);
    }
    private Item getItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null; // return null, wenn kein Artikel mit der gegebenen ID gefunden wurde
    }
    public Item fillCartFromInventory(int[] idAndAmount){
	    Item item = getItemById(idAndAmount[0]);
        if (item != null){
            item.decreaseStock(idAndAmount[1]);
        }
        return item;
    }
    public void putItemBackInInventory(int[] idAndAmount){
        Item item = getItemById(idAndAmount[0]);
        if (item != null){
            item.increaseStock(idAndAmount[1]);
        }
    }
    public void writeInventoryToDatabase() {
        for(Item item : items) {
            itemsManager.updateDatabaseFromInventory(item);
        }
    }
}