package core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyInvocationHandler implements InvocationHandler {
	private AccountManager acc;
	private Logger logger = Logger.getLogger("test");

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		
		
		Object result = method.invoke(acc, args);
		
		
		log(args, method, result);
		
		return result;
	}
	
	private void log(Object[] args, Method method, Object result) {
		
		
		String s = args[0].toString() + " calls method " + method.getName();
		if (result != null){
			s = s + " ergebnis = " + result;
		}
		logger.log(Level.FINE, s, args);
	}

	MyInvocationHandler(AccountManager accountManager){
		this.acc = accountManager;
	}

}
