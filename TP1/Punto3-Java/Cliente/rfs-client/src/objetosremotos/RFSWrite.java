package objetosremotos;
import java.io.File;

public class RFSWrite {

    public File archivo;
    public int count;
    public byte[] data;

    public RFSWrite(File archivo, int count, byte[] data){
        this.archivo = archivo;
        this.count = count; 
        this.data = data;
    }
}
