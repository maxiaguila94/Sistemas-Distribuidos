import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientStub {
	private Socket s;
	ObjectInputStream entrada; 
	ObjectOutputStream salida;
	
	public String archivo_remoto;
	
	public ClientStub(String hostname, int port) {
		try {			
			s = new Socket(hostname, port);
			entrada = new ObjectInputStream(s.getInputStream());
			salida = new ObjectOutputStream(s.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
   public int rfs_open() {

	   return 0;
	   
   }
   
   public int rfs_read(String file_name) {
		
	   String comando = "read "+file_name;
	   
	   try {
			salida.writeUTF(comando);
			this.archivo_remoto = entrada.readUTF();
			
			return 0;
		
	   } catch (IOException e) {
			e.printStackTrace();
			return -1;
	   }
   }
   
   public int rfs_wrtite() {
	   return 0;
   }
   
   public int rfs_close() {
	   return 0;
   }
}
