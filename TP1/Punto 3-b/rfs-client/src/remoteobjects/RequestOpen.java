package remoteobjects;

import remoteobjects.IRFSConstants;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class RequestOpen extends RFSCommand{
    private static final long serialVersionUID = 1L;
	public String file_name;
    public int mode; 
    public String method_name;

    public RequestOpen (String file_name, int mode, String method_name){
        super();
        this.file_name = file_name;        
        this.mode = mode;
        this.method_name = method_name;
    }

    public RequestOpen(String file_name, String method_name){
        this (file_name, IRFSConstants.OPEN_O_CREAT, method_name);
    }

    public RequestOpen(String file_name, int mode){
        this (file_name, mode, "open");
    }

    public RequestOpen(String file_name) {
        this (file_name, IRFSConstants.OPEN_O_CREAT, "open");
    }

    public RFSCommand exec(Object server) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
    
        Method method = server.getClass().getMethod(this.getMethodName(), String.class, int.class, String.class);
        FileProxy file = (FileProxy) method.invoke(server, this.file_name, this.mode, this.getUserToken());

        if (file == null) {
        	RFSCommand error = new RFSCommand();
        	error.setError(true);
        	error.setErrorMessage("no se pudo abrir el archivo");
        	return error;
        }
        
        ResponseOpen response = new ResponseOpen(file);
        return response;
    }

    public String getFileName(){
        return this.file_name;
    }
    public String getMethodName(){
        return this.method_name;
    }
    public int getMode(){
        return this.mode;
    }
}
