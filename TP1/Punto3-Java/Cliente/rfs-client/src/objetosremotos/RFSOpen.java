package objetosremotos;

public class RFSOpen {

    public String file_name;
    public boolean arbierto;

    public RFSOpen(String file_name) {
        this.file_name = file_name;
    }

    public void setStatus (boolean status){
        this.arbierto = status;
    }

}
