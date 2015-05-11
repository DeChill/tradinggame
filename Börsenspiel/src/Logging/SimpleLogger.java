package Logging;
import java.io.IOException;
import java.util.logging.*;

public class SimpleLogger{
	
	private static Logger fLogger =  Logger.getLogger("test");
	static {

		try {
	        FileHandler handler = new FileHandler("myapp-log.%u.%g.xml", true);
	        fLogger.addHandler(handler);
	    } catch (IOException e) {
	        throw new IllegalStateException("Could not add file handler.", e);
	    }
	    fLogger.setLevel(Level.FINE);
		
	}
	
	

	private void doSomething() {
//		fLogger.finest("this is finest");
//	    fLogger.finer("this is finer");
//	    fLogger.fine("this is fine");
//	    fLogger.config("this is config");
//	    fLogger.info("this is info");
//	    fLogger.warning("this is a warning");
//	    fLogger.severe("this is severe");

		
	}
}