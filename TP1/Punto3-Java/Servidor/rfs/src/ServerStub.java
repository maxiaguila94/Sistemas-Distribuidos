import objetosremotos.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sql.rowset.RowSetFactory;

public class ServerStub {

	public static void main(String[] args) throws ClassNotFoundException {
		int port = 7896;
		ServerSocket escuchandoSocket = null;
		Socket socketCliente;
		//InputStream entrada;
		//OutputStream salida;
		ServerRFS server;
		//ObjectOutputStream salidaObj;
		//ObjectInputStream entradaObj;
		
		server = new ServerRFS();

		try {
			escuchandoSocket = new ServerSocket(port);
			System.out.println("Escuchando Puerto: "+port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (true) { 
			
			try {
				System.out.println("Esperando Conexion ...");
				socketCliente = escuchandoSocket.accept();
				System.out.println("Conexion establecida !");
				
				ConexionEntrante c = new ConexionEntrante(socketCliente, server);
				
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	
		}
	}
}


