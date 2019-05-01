package se.kth.pointofsale.integration;

public class ItemDTO {
	private double price;
	private String name;  
	private double vatTaxRate;
	private int itemIdentifier;
	
	public ItemDTO(double price, String name , double tax, int itemIdentifier){
		this.price = price;
		this.name = name;
		this.vatTaxRate= tax;
		this.itemIdentifier = itemIdentifier;
	}
	/*
	 * 
	 * Compares to ItemDTO's to see if they are equal.
	 * 
	 */
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        ItemDTO itemDTO = (ItemDTO) o;
	        if (itemIdentifier != itemDTO.itemIdentifier) return false;
	        if (Double.compare(itemDTO.price, price) != 0) return false;
	        return name != null ? name.equals(itemDTO.name) : itemDTO.name == null;

	    }
	
	/**
     * Returns a string representing the item
     * The price is dependant on the quantity of the items
     * 
     * @param quantity int
     * @return String
     */
    public String toDisplay(int quantity) {
        return name + "\nPrice: " + (quantity*this.price);
    }
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getVatTaxRate() {
		return vatTaxRate;
	}

	public void setVatTaxRate(double vatTaxRate) {
		this.vatTaxRate = vatTaxRate;
	}

	public int getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(int itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}
}
