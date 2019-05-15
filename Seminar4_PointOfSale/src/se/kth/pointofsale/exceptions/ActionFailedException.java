package se.kth.pointofsale.exceptions;

/**
 * Created by Josef on 2019-05-15.
 */
public class ActionFailedException extends Exception {

    /**
     * Creates a new instance representing the condition
     * described in the specified message.
     * @param message A message that describes what went wrong.
     * @param cause The Exception that caused this exception to be thrown
     */
    public ActionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
