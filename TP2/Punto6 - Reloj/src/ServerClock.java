import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerClock implements Clock {

    public ServerClock() {
    }

    public long getHora() {
        return System.currentTimeMillis(); // hora en milisegundos
    }

    public static void main(String args[]) {

        try {
            ServerClock serverClock = new ServerClock();
            Clock clock = (Clock) UnicastRemoteObject.exportObject(serverClock, 0);

            // Liga el stub del objeto remoto en el registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Clock", clock);

            System.err.println("Servidor listo");
        } catch (RemoteException e) {
            System.out.println("Error remotox");
        } catch (Exception e) {
            System.err.println("Excepcion en el servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}