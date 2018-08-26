/*
 * ClienteUDP.java
 *
 * Created on 19 de octubre de 2005, 14:09
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
import java.net.*;
import java.io.*;

/**
 *
 * @author rlopez
 */
public class ClienteUDP {
    public static void main(String args[]) {
        // args proporciona el mensaje y el nro de server
        try {
            int puertoServidor = 6788;
            DatagramSocket unSocket = new DatagramSocket();
            byte[] m = args[0].getBytes();
            byte[] ipDestino = { (byte) 127, (byte) 0, (byte) 0, (byte) 1 };
            
            //Se adapta la sig linea para tomar una dir IP sin DNS           
            InetAddress unHost = InetAddress.getByAddress(ipDestino);
            DatagramPacket peticion = new DatagramPacket(m,args[0].length(),unHost,puertoServidor);
            unSocket.send(peticion);
            byte [] bufer = new byte[1000];
            DatagramPacket respuesta = new DatagramPacket(bufer,bufer.length);
            unSocket.receive(respuesta);
            System.out.println("Respuesta: " + new String(respuesta.getData()));
            unSocket.close();
        }
            catch( Exception e) {
                e.printStackTrace();
            }
    }
    
}
