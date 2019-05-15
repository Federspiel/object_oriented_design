package se.kth.pointofsale.controller;

import java.util.ArrayList;
import java.util.List;

import se.kth.pointofsale.exceptions.ActionFailedException;
import se.kth.pointofsale.exceptions.DatabaseFailureException;
import se.kth.pointofsale.exceptions.ItemNotFoundException;
import se.kth.pointofsale.integration.InventoryHandler;
import se.kth.pointofsale.integration.ItemDTO;
import se.kth.pointofsale.model.Sale;
import se.kth.pointofsale.model.SaleObserver;

public class Controller {
	private Sale currentSale = null;
	private InventoryHandler inventory;
	private List<SaleObserver> saleObservers = new ArrayList<>();

	
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
        this.currentSale.addSaleObservers(saleObservers);
    }
    
    /**
     * Adds an item to the current sale
     *
     * @param itemId int
     * @param quantity int
     * @return ItemDTO
     */
    public ItemDTO enterItem(int itemId, int quantity)  throws ItemNotFoundException, ActionFailedException{
        try{
        	ItemDTO item = inventory.getItemInfo(itemId);
        	this.currentSale.addToSale(item, quantity);
        	return item;
        }catch(DatabaseFailureException e){
        	throw new ActionFailedException("Could not get the Item",e);
        }
    	
        
        
    }
    
    public String currentBasket(){
    	return this.currentSale.currentBasket();
    }
    
    public double getCurrentTotal(){
    	return currentSale.getCurrentTotal();
    }
    
    public double completeSale(){
        return currentSale.getCurrentTotal();
    }
    
    public double completePayment(double payment){
        return currentSale.completeSale(payment);
    }
    
    public void addSaleObserver(SaleObserver obs){
        saleObservers.add(obs);
    }

}
