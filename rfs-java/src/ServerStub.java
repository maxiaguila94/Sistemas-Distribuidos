import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStub {
	private static int port = 7896;
	private static ServerSocket escuchandoSocket;
	private static Socket socketCliente;
	public static DataInputStream entrada;
	public static DataOutputStream salida;
	public static ServerRFS server; 
	
	
	public static void main(String args) {
		
		try {
			escuchandoSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (true) { 
			
			try {
				socketCliente = escuchandoSocket.accept();
				entrada = new DataInputStream(socketCliente.getInputStream());
				salida = new DataOutputStream(socketCliente.getOutputStream());
				String request[] = entrada.readUTF().split(" ");
				String response = " ";
				
				if (request[0].toLowerCase() == "read") {
					response = server.read(request[1]);
				}

				salida.writeUTF(response);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
		}
	}
}

