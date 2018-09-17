package remoteobjects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestLogin extends RFSCommand{

	String username;
	String password; 
	
	public RequestLogin(String username, String password) {
		this.username = username;
		this.password = password;	
	}
	
	@Override
	public RFSCommand exec(Object server) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		
		System.out.println("llegó peticion login...");
		
		Method method = server.getClass().getMethod("login", String.class, String.class);
        ResponseLogin response = (ResponseLogin) method.invoke(server, this.username, this.password);
        
        if (response == null) {        	
        	RFSCommand error = new RFSCommand();
        	error.setError(true);
        	error.setErrorMessage("No ha podido loguearse");
    		System.out.println("llogin failed...");
        	return error;
        }
		System.out.println("login success...");
		return response;
	}
}
