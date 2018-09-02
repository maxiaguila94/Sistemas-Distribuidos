import java.io.BufferedReader;
import java.io.FileReader;

public class ServerRFS {

	
	public ServerRFS() {
		
	}
	
	public int open(String file_name){
		return 0;
	}
		
    public String read( String ruta ){
        FileReader fr = null;
        BufferedReader br = null;
        //Cadena de texto donde se guardara el contenido del archivo
        String contenido = "";
        try
        {
            //ruta puede ser de tipo String o tipo File
            fr = new FileReader( ruta );
            br = new BufferedReader( fr );
 
            String linea;
            //Obtenemos el contenido del archivo linea por linea
            while( ( linea = br.readLine() ) != null ){ 
                contenido += linea + "\n";
            }
 
        }catch( Exception e ){ 
        	e.printStackTrace();
        }
        finally
        {
            try{
                br.close();
            }catch( Exception ex ){}
        }
        return contenido;
    }
	
	
	public void write (String file_name, String data) {
		
	}
	
	public void close(String file_name) {
		
	}
	
}
