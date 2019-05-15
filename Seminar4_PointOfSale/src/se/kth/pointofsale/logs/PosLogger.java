package se.kth.pointofsale.logs;
/**
 * Created by Josef on 2019-05-15.
 */
public interface PosLogger {
    public void logErrorMessage(String msg, Exception e);
}