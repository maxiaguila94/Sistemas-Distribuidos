package remoteobjects;

import java.io.Serializable;
import java.io.File;

public class ResponseRead extends RFSCommand {
    
    private static final long serialVersionUID = 1L;
    public int count;
    public byte[] data;

    public ResponseRead(int count){
        super();
        this.count = count;
        this.data = new byte[1024];
    }

}
