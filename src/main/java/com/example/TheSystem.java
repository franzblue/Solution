package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public abstract class TheSystem {

    // fields
    HashMap<String, Item> itemCollection;
    private static final String path = "/resources";
    private static final String filename = "/sample.txt";
   
    // constructors
    TheSystem() {
        this.itemCollection = new HashMap<String, Item>();
        if(this.getClass().getSimpleName().equals("AppSystem")) {
            String pwd = System.getProperty("user.dir");
            File myObj = new File(pwd + path + filename);
            try(Scanner myReader = new Scanner(myObj);){
                while(myReader.hasNextLine()){
                    String line = myReader.nextLine();
                    String[] itemInfo = line.split("\\s+ ");
                    Item addItem = new Item(
                        String.format(itemInfo[0]), 
                        String.format(itemInfo[1]), 
                        Double.parseDouble(itemInfo[2]), 
                        Integer.parseInt(itemInfo[3]));
                    this.itemCollection.put(itemInfo[0], addItem);
                }
            } catch (FileNotFoundException e){
                System.out.println("An error occurred");
                e.printStackTrace();
            }
        }
    }
    
    // getters/setters
    public HashMap<String, Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(HashMap<String, Item> itemCollection) {
        this.itemCollection = itemCollection;
    }
    
    // methods
    public Boolean checkAvailability(Item item) {
        if(item == null) {
            return null;
        }
        if(item.getQuantity() >= item.getAvailableQuantity()) {
            System.out.printf("System is unable to add %s to the card. System only has %d %ss.", 
                item.getItemName(), 
                item.getAvailableQuantity(), 
                item.getItemName());
            return false;
        } else return true;
    }
    
    public Boolean add(Item item) {
        System.out.println(item);
        if(item == null) {
            return false;
        } else if(itemCollection.containsKey(item.getItemName()) && checkAvailability(item)){
            itemCollection.get(item.getItemName()).setQuantity(itemCollection.get(item.getItemName()).getQuantity() + 1);
            return true;
        } else if(!itemCollection.containsKey(item.getItemName())) {
            itemCollection.put(item.getItemName(), item);
            itemCollection.get(item.getItemName()).setQuantity(1);
            return true;
        } else return false;
    }

    public Item remove(String itemName) {
        if(itemCollection.containsKey(itemName)) {
            Item itemToReturn = itemCollection.get(itemName);
            itemCollection.remove(itemName);
            return itemToReturn;
        } else return null;
    }

    public abstract void display();
}
