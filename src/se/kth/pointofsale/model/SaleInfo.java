package se.kth.pointofsale.model;

import java.util.HashMap;
import java.util.Map;
import se.kth.pointofsale.integration.ItemDTO;

public class SaleInfo{

	private double total = 0;
	private double amountPaid = 0;
    private Map<ItemDTO, Integer> items;
    
    public SaleInfo(){
    	this.items = new HashMap<>();
    }
    
    /**
     * Adds and item to the basket
     * if the item is already in the basket 
     * the quantity of the item is increased.
     *  
     * @param item
     * @param quantity
     */
    
    void addItem(ItemDTO item, int quantity){
        if(!items.containsKey(item)) {
            this.items.put(item, quantity);
        } else{
            this.items.replace(item, this.items.get(item) + quantity);
        }
        this.total += (item.getPrice() * quantity);
    }
    
    /**
     * 
     * Itterates over the map and extracts the 
     * average vat of all the purchased items
     * 
     **/
    public double getTotalVat(){
    	double totalTaxed = 0; 
    	for (Map.Entry<ItemDTO, Integer> item : items.entrySet()) {
    		totalTaxed += (item.getKey().getPrice() * (item.getKey().getVatTaxRate()/100) * item.getValue());
    	}
    	return totalTaxed/this.total;
    }
    
    public double getChange(){
    	return this.amountPaid - this.total;
    }
    public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Map<ItemDTO, Integer> getItems() {
		return items;
	}
	
	public String toString(){
		String basket = "";
		for (Map.Entry<ItemDTO, Integer> item : items.entrySet()) {
    		basket += "Product: " + item.getKey().getName() + " Quantity: " + item.getValue() +
    				   " Cost: " + item.getKey().getPrice() * item.getValue() + "\n";
    	}
		return basket;
	}
}
