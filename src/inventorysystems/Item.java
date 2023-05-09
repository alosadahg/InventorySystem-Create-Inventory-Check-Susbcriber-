/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystems;

/**
 *
 * @author daugd
 */
public class Item {
    private int itemID;
    private String name;
    private String description;
    private int quantity;
    private int inventoryID;
    private boolean isVisible;

    public Item(int itemID, String name, String description, int quantity, int inventoryID, boolean isVisible) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.inventoryID = inventoryID;
        this.isVisible = isVisible;
    }
    public Item(int itemID, String name, String description, int quantity, boolean isVisible) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.isVisible = isVisible;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
}
