package objetosremotos;

import java.io.File;

public class RFSClose {

    public File archivo;
    public boolean cerrado;

    public RFSClose(File archivo) {
        this.archivo = archivo;
    }

    public void setStatus(boolean status){
        this.cerrado = status;
    }

}
