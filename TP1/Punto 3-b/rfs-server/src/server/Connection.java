package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import remoteobjects.RFSCommand;

public class Connection {
	static final int BUFER_LENGTH = 1024;

    Socket socketCliente;
	RFSServer server;
	ObjectInputStream in;
	ObjectOutputStream out;

	public Connection (Socket socket, RFSServer server){
		this.server = server;
		try {
			
			socketCliente = socket;
			out = new ObjectOutputStream(socketCliente.getOutputStream());
			in = new ObjectInputStream(socketCliente.getInputStream());
	
			//this.start(); No utiliza Start porque el pool es el que inicia los hilos
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ConexionEntrante(){
		
		System.out.println("Esperando Mensajes ...");
		
		Object obj;
		
		try {
			while((obj = in.readObject()) != null){

				System.out.println("llego peticion...");
				RFSCommand request = (RFSCommand) obj;
				try {					
					RFSCommand response = request.exec(
						this.server
					);
					System.out.println("sale respuesta...");
					out.writeObject(response);

				} catch (Exception e) {
					e.printStackTrace();
					RFSCommand response = new RFSCommand();
					response.error = true;
					response.setErrorMessage(e.toString());
					out.writeObject(response);
				}

			}

			out.close();
			in.close();
			socketCliente.close();
		} catch (Exception e) {
			//TODO: handle exception
			e.printStackTrace();
		}

	}
}
