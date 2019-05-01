package se.kth.pointofsale.view;

import java.util.Scanner;

import se.kth.pointofsale.controller.Controller;
import se.kth.pointofsale.integration.ItemDTO;

public class View {
	
	private Controller controller;
	
	public View(){
        this.controller = new Controller();
    }
	
	  public String displayItemInfo(ItemDTO item, int quantity){
	        return "Item scanned: (x" + quantity + ")\n" + item.toDisplay(quantity) + "\nTotal: " + controller.getCurrentTotal() +
	        		"\nIn Basket" + "\n*********************\n" + this.controller.currentBasket();
	  }

	  public String displayTotal(double amountDue){
	        return "Amount to pay: " + amountDue + "kr";
	  }
	  
	  private void itemsLeftDialog(){
	        System.out.println("Please take an action:\n0: Complete sale\n1: Enter item\n2: Enter multiple items");
	    }

	    private void completedSaleDialog(){
	        System.out.println("Please Register payment");
	    }

	    private void takePaymentDialog(){
	        System.out.println("Please take an action:\n1: Register payment");
	    }
	    
	    private void baseDisplay(String currentDisplay){
	        System.out.println(
	                "\n\n\n\n\n\n\n" +
	                "CURRENT DISPLAY:\n" +
	                "*********************\n" +
	                currentDisplay +
	                "\n*********************"
	        );
	    }
	    private void finishSale(Scanner scan){
	        System.out.println("Please enter the amount payed:");
	        double payment = scan.nextDouble();
	        double change = controller.completePayment(payment);
	        baseDisplay("Purchase completed:\nAmount paid: " + String.format("%.2f", payment) + "\nChange: " + String.format("%.2f", change));
	    }
	    
	    public void POSloop(){
	        Scanner scan = new Scanner(System.in);
	        boolean itemsLeft = true;
	        ItemDTO lastScannedItem = null;
	        int lastQuantity = 0;
	        int identifier = -1;

	        controller.startSale();

	        while(itemsLeft){
	            if(lastScannedItem != null){
	                baseDisplay(displayItemInfo(lastScannedItem, lastQuantity));
	            } else{
	                welcomeScreen();
	            }
	            itemsLeftDialog();
	            int choice = scan.nextInt();
	            switch (choice){
	                case 0:
	                    itemsLeft = false;
	                    break;
	                case 1:
	                    System.out.println("Please enter the item identifier");
	                    identifier = scan.nextInt();
	                    lastScannedItem = controller.enterItem(identifier, 1);
	                    lastQuantity = 1;
	                    break;
	                case 2:
	                    System.out.println("Please enter the item identifier");
	                    identifier = scan.nextInt();
	                    System.out.println("Please enter the amount of items");
	                    lastQuantity = scan.nextInt();
	                    lastScannedItem = controller.enterItem(identifier, lastQuantity);
	                    break;
	            }

	        }

	        System.out.println("Completing sale...");

	        double totalWithTax = controller.completeSale();

	        System.out.println("Sale completed");
	        baseDisplay(displayTotal(totalWithTax));
	        completedSaleDialog();
	        finishSale(scan);
	 	    }

	    private void welcomeScreen(){
	        System.out.println("New Sale started!");
	    }

}
