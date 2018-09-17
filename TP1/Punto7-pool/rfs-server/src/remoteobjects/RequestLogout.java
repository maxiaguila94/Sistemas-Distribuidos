package remoteobjects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestLogout extends RFSCommand {

	
	@Override
	public RFSCommand exec(Object server) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Method method = server.getClass().getMethod("logout");
        method.invoke(server);

		
		return null;
	}
	
}
