package se.kth.pointofsale.integration;

import java.util.HashMap;
import java.util.Map;

import se.kth.pointofsale.exceptions.DatabaseFailureException;
import se.kth.pointofsale.exceptions.ItemNotFoundException;

public class InventoryHandler {
	private Map<Integer,InventorySystem<ItemDTO,Integer>> items = new HashMap<Integer,InventorySystem<ItemDTO,Integer>>();
		
	/**
     * Creates instance of object and initiates a "Database" of items, 
     * Each contains N amount of products
     */
	public InventoryHandler(){
		items.put(1, new InventorySystem<>(new ItemDTO(14.99,"Potatos",12, 1), 10));
        items.put(2, new InventorySystem<>(new ItemDTO(29.99, "Milk-Carton",15,2), 10));
        items.put(3, new InventorySystem<>(new ItemDTO(9.99, "Cornflakes",25,3), 10));
        items.put(4, new InventorySystem<>(new ItemDTO(49.99, "Cheese",12,4), 10));
        items.put(5, new InventorySystem<>(new ItemDTO(4.99, "Juice",12,5), 10));
        items.put(6, new InventorySystem<>(new ItemDTO(9.99, "Beans",8,6), 10));
	}
	
    /**
     * Returns the ItemDTO of the item asked for
     *
     * @param itemId int
     * @return ItemDTO
     * @throws ItemNotFoundException if the specified item does not exist
     * @throws DatabaseFailureException if the database fails during runtime
     */
    public ItemDTO getItemInfo(int itemId) throws ItemNotFoundException, DatabaseFailureException{
        if (itemId == 7){
            throw new DatabaseFailureException();
        }
        if (!items.containsKey(itemId)){
            throw new ItemNotFoundException(itemId);
        }
        return items.get(itemId).getItem();
    }
    
    /**
     * Removes the amount specified in "quantity" from the active stock of the item
     *
     * @param itemId int
     * @param quantity int
     * @return boolean
     */
    
    public boolean removeFromStock(int itemId, int quantity){
    	InventorySystem<ItemDTO, Integer> item = items.get(itemId);
        if(item.getStock() >= quantity){
            item.setStock(item.getStock() - quantity);
            return true;
        } else{
            return false;
        }
    }
    /**
     * @author Josef
     * Database that keeps track of the stock.
     * @param <Item>
     * @param <AvailStock>
     */
	
	private class InventorySystem<Item,AvailStock> {
		private Item item;
		private AvailStock stock;
		InventorySystem(Item item, AvailStock stock){
            this.item = item;
            this.stock = stock;
        }

        Item getItem(){ return item; }

        AvailStock getStock(){ return stock; }

        void setStock(AvailStock stock){
            this.stock = stock;
        }
	}
}
