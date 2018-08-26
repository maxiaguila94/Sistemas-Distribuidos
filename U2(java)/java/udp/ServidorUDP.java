/*
 * ServidorUDP.java
 *
 * Created on 21 de octubre de 2005, 10:24
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
public class ServidorUDP {
    public static void main(String args[]) {
        try {
            DatagramSocket unSocket = new DatagramSocket(6788);
            byte[] bufer = new byte[1000];
            while (true){
                DatagramPacket peticion = new DatagramPacket(bufer,bufer.length);
                unSocket.receive(peticion);
                DatagramPacket respuesta = new DatagramPacket(peticion.getData(),peticion.getLength(),peticion.getAddress(),peticion.getPort());
                unSocket.send(respuesta);
            }
        }
        catch( Exception e){
            e.printStackTrace();

        }
    } 
}
