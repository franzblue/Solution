package com.example;

import java.util.HashMap;
import java.util.Map;

public class AppSystem extends TheSystem {
    AppSystem() {
    }

    @Override
    public void display() {
        HashMap<String, Item> items = getItemCollection();
        System.out.println("AppSystem Inventory:");
        System.out.printf("%-20s %-20s %-10s %-10s\n", "Name", "Description", "Price", "Available Quantity");
        for (Map.Entry<String, Item> entry : items.entrySet())
            System.out.printf("%-20s %-20s %-10.2f %-10d\n",
                entry.getValue().getItemName(),
                entry.getValue().getItemDesc(),
                entry.getValue().getItemPrice(),
                entry.getValue().getAvailableQuantity());
    }

    @Override      // this overwrites the parents class add method 
    public Boolean add(Item item) {
        if(item == null) {
            return false;
        } else if(itemCollection.containsKey(item.getItemName())) {
            System.out.printf("%s is already in the App System\n", item.getItemName());
            return false;
        } else if(!itemCollection.containsKey(item.getItemName())) {
            itemCollection.put(item.getItemName(), item);
            return true;
        }
        return null;
    }

    public Item reduceAvailableQuantity(String item_name) {
        if(itemCollection.containsKey(item_name)) {
            itemCollection.get(item_name).setAvailableQuantity
            (itemCollection.get(item_name).getAvailableQuantity() - 1);
            if(itemCollection.get(item_name).getAvailableQuantity() == 0) {
                itemCollection.remove(item_name);
            }
            return itemCollection.get(item_name);
        } else return null;
    }
}
