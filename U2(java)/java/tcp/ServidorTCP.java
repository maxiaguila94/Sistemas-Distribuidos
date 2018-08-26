import java.net.*;
import java.io.*; 
public class ServidorTCP {
	public static void main(String args[])  {
    	       try {
      		int puertoServicio = 7896;
      		ServerSocket escuchandoSocket = new ServerSocket(puertoServicio);
      		while (true) { 
        		     /* Espera conexion de un cliente */
        		    Socket socketCliente = escuchandoSocket.accept();
		    /* Ya hay una conexion con un cliente, streams de I/O */
        		    DataInputStream entrada;
        		    DataOutputStream salida;
        		    entrada = new DataInputStream(socketCliente.getInputStream());
        		    salida = new DataOutputStream(socketCliente.getOutputStream());
		    /* Provee servicio e imprime */
		    String datos = entrada.readUTF();
        		    salida.writeUTF(datos);
        		    System.out.println("Emitido: " + datos);
		    /* Fin de un servicio */
        		    socketCliente.close();
      	   	}
    	      }
    	     catch(Exception e) {
      		e.printStackTrace();
    	     }
  	}
}
