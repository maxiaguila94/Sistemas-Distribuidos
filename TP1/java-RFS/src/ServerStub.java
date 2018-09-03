import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.omg.CORBA.Object;

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
				Object obj;

				switch (key) {
					case value:
						
						break;
				
					default:
						break;
				}			
				
				/*String request[] = entrada.readUTF().split(" ");
				String response = " ";


				
				if (request[0].toLowerCase() == "read") {
					response = server.read(request[1]);
				}

				salida.writeUTF(response);
			*/	
				
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	
		}
	}
}

