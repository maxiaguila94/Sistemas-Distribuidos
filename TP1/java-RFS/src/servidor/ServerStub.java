package servidor;

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
	private static int port = 7896;
	private static ServerSocket escuchandoSocket;
	private static Socket socketCliente;
	public static InputStream entrada;
	public static OutputStream salida;
	public static ServerRFS server;
	public static ObjectOutputStream salidaObj;
	public static ObjectInputStream entradaObj;

	public static void main(String args) {
		
		server = new ServerRFS();

		try {
			escuchandoSocket = new ServerSocket(port);
			System.out.print("Escuchando Puerto: "+port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (true) { 
			
			try {
				System.out.print("Esperando Conexion ...");
				socketCliente = escuchandoSocket.accept();
				System.out.print("Conexion establecida :D");
				
				entrada = socketCliente.getInputStream();
				salida = socketCliente.getOutputStream();
				
				entradaObj = new ObjectInputStream(entrada);
				salidaObj = new ObjectOutputStream(salida);
				System.out.print("Esperando Mensajes ...");

				// Aca los instanceof de los objetos entrantes
				Object obj = entradaObj.readObject();
				
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
				
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	
		}
	}
}

