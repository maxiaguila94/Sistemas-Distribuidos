import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Clock {
        
    public Server() {}

    public long getHora() {
        return System.currentTimeMillis(); //hora en milisegundos
    }
        
    public static void main(String args[]) {
        
        try {
            Server obj = new Server();
            Clock clock = (Clock) UnicastRemoteObject.exportObject(obj, 0);
            
            // Liga el stub del objeto remoto en el registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Clock", clock);
            System.err.println("Servidor listo");
        } catch (RemoteException e){
            System.out.println("Error remotox");
        } catch (Exception e){
            System.err.println("Excepcion en el servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}