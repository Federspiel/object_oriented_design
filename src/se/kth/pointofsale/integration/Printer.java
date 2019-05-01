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
				"\nTotal Price: " + saleInfo.getTotal() +
				"\nTotalVat" +saleInfo.getTotalVat() + "%"+
				"\nAmount Paid: " + saleInfo.getAmountPaid() +
				"\nChange: " + saleInfo.getChange(); 
	}
		
}
