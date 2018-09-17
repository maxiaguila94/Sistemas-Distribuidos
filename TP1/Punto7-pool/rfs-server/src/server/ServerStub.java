package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.RFSServer;

public class ServerStub {

	private static final int MAX_CONNECTIONS = 3;

	public static void main(String[] args) throws ClassNotFoundException {
		int port = 7896;
		ServerSocket escuchandoSocket = null;
		Socket socketCliente;
		RFSServer server;
		
		server = new RFSServer();

		try {
			escuchandoSocket = new ServerSocket(port);
			System.out.println("Escuchando Puerto: "+port);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//Se crea el Pool
		ExecutorService pool = Executors.newFixedThreadPool(MAX_CONNECTIONS);
		
		while (true) { 
			
			try {
				System.out.println("Esperando Conexion ...");
				socketCliente = escuchandoSocket.accept();
				System.out.println("Conexion establecida !");
				
				Connection c = new Connection(socketCliente, server);
				pool.submit(c);
				
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	
		}
	}
}
