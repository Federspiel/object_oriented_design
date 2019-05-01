package se.kth.pointofsale.model;

import se.kth.pointofsale.integration.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SaleInfoTest {
    private InventoryHandler inventory;
    private SaleInfo testSaleInfo;

    @Before
    public void setUp() throws Exception {
        inventory = new InventoryHandler();
        testSaleInfo = new SaleInfo();
    }

    @After
    public void tearDown() throws Exception {
        inventory = null;
        testSaleInfo = null;
    }

    @Test
    public void testAddingItems() throws Exception {
        ItemDTO item1 = inventory.getItemInfo(1);
        ItemDTO item2 = inventory.getItemInfo(2);
        ItemDTO item3 = inventory.getItemInfo(3);

        testSaleInfo.addItem(item1, 1);
        assertEquals("Adding an item to SaleInfo doesn't add it to item Map", 1, testSaleInfo.getItems().size());

        testSaleInfo.addItem(item2, 1);
        assertEquals("Adding an additional item to SaleInfo doesn't add it to item Map", 2, testSaleInfo.getItems().size());

        testSaleInfo.addItem(item1, 5);
        assertEquals("Adding an already existing item does not handle the Map correctly", 2, testSaleInfo.getItems().size());

        testSaleInfo.addItem(item3, 3);
        assertEquals("Adding multiple of a new item doesn't work correctly", 3, testSaleInfo.getItems().size());
    }

    @Test
    public void testGetTotal() throws Exception {
        ItemDTO item1 = inventory.getItemInfo(1);
        ItemDTO item2 = inventory.getItemInfo(2);
        ItemDTO item3 = inventory.getItemInfo(3);

        double expectedCurrentTotal = 0;

        testSaleInfo.addItem(item1, 1);
        expectedCurrentTotal += item1.getPrice();
        assertEquals("Adding an item to SaleInfo doesn't add it to new total correctly", expectedCurrentTotal, testSaleInfo.getTotal(), 0.001);

        testSaleInfo.addItem(item2, 1);
        expectedCurrentTotal += item2.getPrice();
        assertEquals("Adding an additional item to SaleInfo doesn't update the total correctly", expectedCurrentTotal, testSaleInfo.getTotal(), 0.001);

        testSaleInfo.addItem(item1, 5);
        expectedCurrentTotal += item1.getPrice() * 5;
        assertEquals("Adding an already existing item does not update the total correctly", expectedCurrentTotal, testSaleInfo.getTotal(), 0.001);

        testSaleInfo.addItem(item3, 3);
        expectedCurrentTotal += item3.getPrice() * 3;
        assertEquals("Adding multiple of a new item doesn't update the total correctly", expectedCurrentTotal, testSaleInfo.getTotal(), 0.001);
    }
}