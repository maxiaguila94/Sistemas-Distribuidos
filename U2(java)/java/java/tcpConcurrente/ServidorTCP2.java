/**
 *
 * ServidorTCP2 (adaptado a IP)
 */
import java.net.*;
import java.io.*; 
public class ServidorTCP2 {
    public static void main(String args[]) {
        try {
            int puertoServicio = 7896;
            ServerSocket escuchandoSocket = new ServerSocket(puertoServicio);
            while (true){
                Socket socketCliente = escuchandoSocket.accept();
                Conexion c = new Conexion(socketCliente);
            }
        }
        catch( Exception e){
            e.printStackTrace();
        }
    } 
} 

class Conexion extends Thread {
    DataInputStream entrada;
    DataOutputStream salida;
    Socket socketCliente;
    public Conexion (Socket unSocketCliente) {
        try {
           socketCliente = unSocketCliente;
           entrada = new DataInputStream(socketCliente.getInputStream());
           salida = new DataOutputStream(socketCliente.getOutputStream());
           this.start();
        }
        catch( Exception e){
            e.printStackTrace();
        }
    }
    
    public void run(){
        try {          //un servidor ECO
            
            while (true){
	      String datos = entrada.readUTF();
	      salida.writeUTF(datos);
	      System.out.println("Emitido: "+ datos);
	      //socketCliente.close();
	    }
        }
        catch( Exception e){
            e.printStackTrace();
        }
    }
}
