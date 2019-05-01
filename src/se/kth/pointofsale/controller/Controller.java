package se.kth.pointofsale.controller;

import se.kth.pointofsale.integration.InventoryHandler;
import se.kth.pointofsale.integration.ItemDTO;
import se.kth.pointofsale.model.Sale;
import se.kth.pointofsale.model.SaleInfo;

public class Controller {
	private Sale currentSale = null;
	private InventoryHandler inventory;
	
	/**
     * @param inv InventoryHandler
     */
    public Controller(){
        this.inventory = new InventoryHandler();
    }
    /**
     * Starts a sale
     */
    public void startSale(){
        this.currentSale = new Sale(this.inventory);
    }
    
    /**
     * Adds an item to the current sale
     *
     * @param itemId int
     * @param quantity int
     * @return ItemDTO
     */
    public ItemDTO enterItem(int itemId, int quantity){
        ItemDTO item = inventory.getItemInfo(itemId);
        this.currentSale.addToSale(item, quantity);
        return item;
    }
    
    public String currentBasket(){
    	return this.currentSale.currentBasket();
    }
    
    public double getCurrentTotal(){
    	return currentSale.getCurrentTotal();
    }
    
    /**
     * Returns amount of change to give to customer
     * Removes the purchased items from the inventory and consumes any potential discounts
     *
     * @param payment double
     * @return double
     */

    public double completeSale(){
        return currentSale.getCurrentTotal();
    }
    
    public double completePayment(double payment){
        return currentSale.completeSale(payment);
    }

}
