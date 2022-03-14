package com.example;

import java.util.HashMap;
import java.util.Map;

public class CartSystem extends TheSystem {
    CartSystem() {
    }

    @Override
    public void display() {
        HashMap<String, Item> items = getItemCollection();
        double runningTotal = 0;
        
        System.out.println("Cart:");
        System.out.printf("%-20s %-20s %-10s %-10s %-10s\n", "Name", "Description", "Price", "Quantity", "Sub Total");
        for(Map.Entry<String, Item> entry : items.entrySet()) {
            double total = (entry.getValue().getItemPrice() * entry.getValue().getQuantity());
            runningTotal += total;
            System.out.printf("%-20s %-20s %-10.2f %-10d %-10.2f\n",
                entry.getValue().getItemName(),
                entry.getValue().getItemDesc(),
                entry.getValue().getItemPrice(),
                entry.getValue().getQuantity(),
                total);
        }
        System.out.printf("%-20s %-20.2f\n", "Pre-tax Total", runningTotal);
        double tax = runningTotal * .05;
        System.out.printf("%-20s %-20.2f\n", "Tax", tax);
        System.out.printf("%-20s %-20.2f\n", "Total",runningTotal + tax);
    }
}
