package se.kth.pointofsale.exceptions;

/**
 * Created by Josef on 2019-05-15.
 */
public class ItemNotFoundException extends Exception {

    /**
     * Thrown when an invalid identifier is used to try fetch an item
     * in the <code>InventoryHandler</code>
     *
     * @param id - The ID that was used when no item was found
     */
    public ItemNotFoundException(int id) {
        super("Item with identifier : " + id + " could not be found in registry.");
    }
}
