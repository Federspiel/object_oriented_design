package se.kth.pointofsale.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import se.kth.pointofsale.integration.InventoryHandler;
import se.kth.pointofsale.integration.ItemDTO;
import se.kth.pointofsale.integration.Printer;
	
public class Sale {
	private SaleInfo saleInfo = null;
	private InventoryHandler inventory = null;
	private List<SaleObserver> saleObservers = new ArrayList<>();
	/**
     * Creates the instance of the object and creates an instance specific SaleInfo object
     */
    public Sale(InventoryHandler inv){
        this.saleInfo = new SaleInfo();
        this.inventory = inv;
    }
    
    /**
     * Completes the sale by adding the payment to the 
     * saleInfo and removing the items from the stock.
     * prints the receipt and returns the change. 
     * @param payment
     * @return change
     */
    public double completeSale(double payment){
    	saleInfo.setAmountPaid(payment);
    	notifyObservers();
    	double change = saleInfo.getChange();
        Map<ItemDTO, Integer> items = saleInfo.getItems();
        for (Map.Entry<ItemDTO, Integer> entry : items.entrySet()){
            //Doesn't handle case where cashier tries to remove more items than are in stock
            inventory.removeFromStock(entry.getKey().getItemIdentifier(), entry.getValue());
        }
        System.out.println(Printer.printReceipt(saleInfo));
        return change;
    }
    
    /*
     * adds an item to the sale info
     */
	public void addToSale(ItemDTO item, int quantity){
        this.saleInfo.addItem(item, quantity);
    }

	public String currentBasket(){
		return this.saleInfo.toString();
	}
	public double getCurrentTotal(){
		return this.saleInfo.getTotal();
	}
	
    private void notifyObservers(){
    	for (SaleObserver obs : saleObservers){
    		System.out.println("********************");
            obs.newTotal(saleInfo);
        }
    }

    public void addSaleObserver(SaleObserver obs){
        saleObservers.add(obs);
    }

    public void addSaleObservers(List<SaleObserver> obsList){
        saleObservers.addAll(obsList);
    }
	
}
