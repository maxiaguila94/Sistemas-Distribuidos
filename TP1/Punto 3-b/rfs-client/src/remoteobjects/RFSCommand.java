package remoteobjects;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class RFSCommand implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String user_token;
    public boolean error;
    private String error_message;

    public String getErrorMessaage(){
        return this.error_message;
    }

    public void setErrorMessage(String error_message){
        this.error_message = error_message;
    }

    public void setUserToken (String token){
        this.user_token = token;
    }

    public String getUserToken(){
        return this.user_token;
    }

    public void setError(boolean status) {
    	this.error = status;
    }
    public RFSCommand exec(Object app) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        return this;
    }
}
