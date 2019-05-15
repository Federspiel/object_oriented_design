package Main;

import java.io.IOException;

import se.kth.pointofsale.logs.ConsoleLogger;
import se.kth.pointofsale.logs.FileLogger;
import se.kth.pointofsale.logs.PosLogger;
import se.kth.pointofsale.view.View;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		View view = null;
		try{
            PosLogger[] loggers = {new ConsoleLogger(), new FileLogger()};
            view = new View(loggers);
        }catch (IOException e) {
            System.out.println("Logger could not be initialized, terminating...");
            System.exit(0);
        }
		
		while(true){
			view.POSloop();
		}
	}

}
