package se.kth.pointofsale.logs;

public class ConsoleLogger implements PosLogger{
	@Override
	public void logErrorMessage(String msg, Exception e) {
		System.out.println(DateAndTimeUtility.getCurrentTime() + ": ERROR\n" + msg);
	}
}
