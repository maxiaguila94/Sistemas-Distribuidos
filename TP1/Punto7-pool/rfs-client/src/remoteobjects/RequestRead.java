package remoteobjects;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.System;


public class RequestRead extends RFSCommand{
    private static final long serialVersionUID = 1L;
    private String file_id;
    private String file_name;
    private String method_name;
    private String method_name_for_searching_a_opened_file;

    public RequestRead(String file_id, String file_name, String method_name, String method_name_for_searching_a_opened_file){
        super();
        this.file_id = file_id;
        this.file_name = file_name;
        this.method_name = method_name;
        this.method_name_for_searching_a_opened_file = method_name_for_searching_a_opened_file;
    }

    public RequestRead(String file_id, String file_name){
        this (file_id, file_name, "read", "getOpenedFile");        
    }


    @Override
    public ResponseRead exec(Object server) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{

        Method methodForSearchingOpenedFile = server.getClass().getMethod(this.getMethodNameForSearchingAOpenedFile(), String.class, String.class);

        FileProxy file = (FileProxy) methodForSearchingOpenedFile.invoke(server, this.getFileID(), this.getFileName());

        Method method = server.getClass().getMethod(this.getMethodName(), FileProxy.class);
        method.invoke(server, file);

        ResponseRead response = new ResponseRead(file.getFileLength());

        System.arraycopy(file.file_buffer, 0, response.data, 0, file.getFileLength());

        return response; 
    }


    public String getFileID(){
        return this.file_id;
    }

    public String getFileName(){
        return this.file_name;
    }

    public String getMethodName(){
        return this.method_name;
    }

    public String getMethodNameForSearchingAOpenedFile(){
        return this.method_name_for_searching_a_opened_file;
    }
}
