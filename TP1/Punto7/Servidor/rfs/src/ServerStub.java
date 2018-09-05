import objetosremotos.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sql.rowset.RowSetFactory;

public class ServerStub {
	public static int maxTareas = 3;

	public static void main(String[] args) throws ClassNotFoundException {
		int port = 7896;
		ServerSocket escuchandoSocket = null;
		Socket socketCliente;
		ServerRFS server;
		server = new ServerRFS();

		try {
			escuchandoSocket = new ServerSocket(port);
			System.out.println("Escuchando Puerto: "+port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Se crea el Pool
		ExecutorService pool = Executors.newFixedThreadPool(maxTareas);
		
		while (true) { 
			
			try {
				System.out.println("Esperando Conexion ...");
				socketCliente = escuchandoSocket.accept();
				System.out.println("Conexion establecida !");
				
				// Agrega la conexion (Cliente) al pool
				Tarea task = new Tarea(socketCliente, server);
				pool.submit(task);

			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	
		}
	}
}


