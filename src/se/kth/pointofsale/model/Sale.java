package se.kth.pointofsale.model;


import java.util.Map;

import se.kth.pointofsale.integration.InventoryHandler;
import se.kth.pointofsale.integration.ItemDTO;
import se.kth.pointofsale.integration.Printer;
	
public class Sale {
	private SaleInfo saleInfo = null;
	private InventoryHandler inventory = null;
	/**
     * Creates the instance of the object and creates an instance specific SaleInfo object
     */
    public Sale(InventoryHandler inv){
        this.saleInfo = new SaleInfo();
        this.inventory = inv;
    }
    
    public double completeSale(double payment){
    	saleInfo.setAmountPaid(payment);
    	double change = saleInfo.getChange();
        Map<ItemDTO, Integer> items = saleInfo.getItems();
        for (Map.Entry<ItemDTO, Integer> entry : items.entrySet()){
            //Doesn't handle case where cashier tries to remove more items than are in stock
            inventory.removeFromStock(entry.getKey().getItemIdentifier(), entry.getValue());
        }
        System.out.println(Printer.printReceipt(saleInfo));
        return change;
    }
	public void addToSale(ItemDTO item, int quantity){
        this.saleInfo.addItem(item, quantity);
    }
	
	public void payment(double pay){
		this.saleInfo.setAmountPaid(pay);
	}
	
	public String currentBasket(){
		return this.saleInfo.toString();
	}
	public double getCurrentTotal(){
		return this.saleInfo.getTotal();
	}
	
	
}
