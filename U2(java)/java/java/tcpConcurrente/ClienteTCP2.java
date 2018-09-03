
/*
 * Cliente TCP2.
 */
import java.net.*;
import java.io.*;


public class ClienteTCP2 {
    public static void main(String args[]) {
        // args proporciona el mensaje y el Nombre de Server Destino
	try {
            int puertoServicio = 7896;
	    int vez = 0;
            byte[] ipDestino = { (byte) 127, (byte) 0, (byte) 0, (byte) 1};
            //Se adapta la sig linea para tomar una dir IP sin DNS           
            InetAddress unHost = InetAddress.getByAddress(ipDestino);

            Socket s = new Socket(unHost, puertoServicio);
            DataInputStream entrada = new DataInputStream(s.getInputStream());
            DataOutputStream salida = new DataOutputStream(s.getOutputStream());
            
	    while (true){
	      salida.writeUTF(args[vez++]); //UTF es una codific. de Strings
	      String datos = entrada.readUTF();
	      System.out.println("Recibido: " + datos + vez);
	      if (vez == 4) vez = 0;
	      for(int i = 0; i < 10000000; i++)
		;
	    }
            //s.close();
            
        }
            catch( Exception e) {
                e.printStackTrace();
            }
    }
    
}

