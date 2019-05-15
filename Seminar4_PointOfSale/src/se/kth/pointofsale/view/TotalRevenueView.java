package se.kth.pointofsale.view;

import se.kth.pointofsale.model.SaleObserver;

import java.util.HashMap;
import java.util.Map;

import se.kth.pointofsale.model.*;

class TotalRevenueView implements SaleObserver{
    private Map<SaleInfo, Double> saleTotals = new HashMap<>();

    @Override
    public void newTotal(SaleInfo saleInfo) {
        addToSale(saleInfo);
        printCurrentState();
    }

    private void addToSale(SaleInfo saleInfo){
        saleTotals.put(saleInfo, saleInfo.getTotal());
    }

    private void printCurrentState(){
        int index = saleTotals.size();
        System.out.println("*** Total Sales For Today ***");
        for (Map.Entry<SaleInfo, Double> saleTotal : saleTotals.entrySet()){
            System.out.println("Sale nr " + index + " total: " + saleTotal.getValue());
            index--;
        }
        System.out.println("********************");
        System.out.println("\n\n\n\n");
        System.out.println("********************");
    }
}