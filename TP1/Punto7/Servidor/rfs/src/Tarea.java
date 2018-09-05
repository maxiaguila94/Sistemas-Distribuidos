import java.net.Socket;
import objetosremotos.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


class Tarea implements Runnable{
    Socket socketCliente;
	ServerRFS servidor;
	ObjectInputStream entrada;
	ObjectOutputStream salida;
  
    public Tarea (Socket socket, ServerRFS server){
		socketCliente = socket;
		servidor = server;
		try {
		 
			salida = new ObjectOutputStream(socketCliente.getOutputStream());
            entrada = new ObjectInputStream(socketCliente.getInputStream());
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void run(){
		
		System.out.println("Esperando Mensajes ...");
		
		try {
			Thread.sleep(20000);	
		} catch (Exception e) {
			//TODO: handle exception
			e.printStackTrace();
		}
		
		Object obj;

		try {

			while((obj = entrada.readObject()) != null){

				// Aca los Instanceof
				if (obj instanceof RFSOpen){

					RFSOpen archivo = (RFSOpen) obj;

					System.out.println(
						String.format("RFSOpen %s", archivo.file_name)
					);

				} else if (obj instanceof RFSRead) {

					RFSRead archivo = (RFSRead) obj;

					System.out.println(
						String.format("RFSRead %s", archivo.data)
					);

				} else if (obj instanceof RFSWrite){

					RFSWrite archivo = (RFSWrite) obj;
					
					System.out.println(
						String.format("RFSWrite %s", archivo.data)
					);

				} else {

					RFSClose archivo = (RFSClose) obj;

					System.out.println(
						String.format("RFSClose %s", archivo.archivo.getAbsolutePath())
					);

				}

			} 

		} catch (Exception e) {
			//TODO: handle exception
			e.printStackTrace();
		}

	}


}