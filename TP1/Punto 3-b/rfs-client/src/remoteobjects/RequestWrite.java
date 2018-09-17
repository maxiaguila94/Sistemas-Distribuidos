package remoteobjects;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RequestWrite extends RFSCommand{

    private static final long serialVersionUID = 1L;
    private int count;
    public byte[] file_content;
    private FileProxy file;
    
    public RequestWrite(FileProxy file, int count){
        
        this.file = file;
        this.count = count;

    }

    @Override
    public ResponseWrite exec(Object server) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
    	
        Method method = server.getClass().getMethod("write", FileProxy.class, int.class, byte[].class);
        
        method.invoke(server, this.file, this.getCount(), this.file_content);
		return new ResponseWrite();

    }

    public void setContent(byte[]content, int count) {
    	this.count = count;
    	System.arraycopy(content, 0, this.file_content, 0, count);
    }
    
	public int getCount(){
        return this.count;
    }

	public void setCount(int count) {
		this.count = count;
	}
    
//    public String getHash (){
//        return this.file_hash;
//    }
//
//    public void setHash(String hash){
//        this.file_hash = hash;
//    }
}