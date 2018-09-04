package objetosremotos;

import java.io.File;

public class RFSRead {

    public File archivo;
    public int count;
    public byte[] data;

    public RFSRead(File archivo, int count, byte[] data){
        this.archivo = archivo;
        this.count = count; 
        this.data = data;
    }
}
