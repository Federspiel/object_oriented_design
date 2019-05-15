package se.kth.pointofsale.exceptions;

/**
 * Created by Josef on 2019-05-15.
 */
public class DatabaseFailureException extends RuntimeException {
    /**
     * Thrown when a database failure is caught while performing
     * an operation in the <code>InventoryHandler</code>.
     */
    public DatabaseFailureException() {
        super("Database is no longer responding.");
    }
}