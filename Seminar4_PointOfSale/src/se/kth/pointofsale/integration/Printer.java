package se.kth.pointofsale.integration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import se.kth.pointofsale.model.SaleInfo;

public class Printer {
	
	/**
     * Representation of the printer in the store
     *
     * @param saleInfo SaleInfo
     * @return String
     */
	public static String printReceipt(SaleInfo saleInfo){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		return "Date: " + dtf.format(localDate) + "\nIV1350: Kista" + "\n"+ saleInfo.toString() + 
				"\nTotal Price: " + String.format("%.2f",saleInfo.getTotal()) +
				"\nTotalVat: " + String.format("%.2f", saleInfo.getTotalVat()) + "%"+
				"\nAmount Paid: " + String.format("%.2f",saleInfo.getAmountPaid()) +
				"\nChange: " + String.format("%.2f",saleInfo.getChange()); 
	}
		
}
