package remoteobjects;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestSignUp extends RFSCommand{
	
	String username;
	String password; 
	
	public RequestSignUp(String username, String password) {
		this.username = username;
		this.password = password;	
	}
	
	public RFSCommand exec(Object server) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {					
	   			
		Method method = server.getClass().getMethod("signup", String.class, String.class);
	    String token =  (String) method.invoke(server, this.username, this.password);
	    
        if (token != null) {
        	RFSCommand response =  new RFSCommand();
        	response.setUserToken(token);
        	return response;
        }
        
        RFSCommand error = new RFSCommand();
        error.setError(true);
        error.setErrorMessage("No ha podido loguearse");
		return error;
	}
	
}
