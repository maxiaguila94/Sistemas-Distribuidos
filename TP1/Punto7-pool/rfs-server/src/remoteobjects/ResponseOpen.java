package remoteobjects;

import java.lang.reflect.Method;

public class ResponseOpen extends RFSCommand{

    private static final long serialVersionUID = 1L;

    private FileProxy file;
    
    public ResponseOpen(FileProxy file){
        this.file = file;
    }

    public FileProxy getFile() {
    	return this.file;
    }
    
    public void setMetadata(FileMetadata metadata){
        this.file.setMetadata(metadata);
    }

    public FileMetadata getMetadata(){
    	return this.file.getMetadata();
     }

    public String getFileName(){
        return this.file.getFileName();
    }

    public String getFileId(){
        return this.file.getFileId();
    }

}
