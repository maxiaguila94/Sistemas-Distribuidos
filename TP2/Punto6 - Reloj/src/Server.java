import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server{

    public Server(String host) {
        try {
            IRemoteClock objetoRemoto = new RemoteClock();
            // Liga el stub del objeto remoto en el registry
            String rname = "//" + host + ":" + Registry.REGISTRY_PORT  + "/RemoteClock";
            Naming.rebind (rname, objetoRemoto);
            System.err.println("Servidor listo");

        } catch (RemoteException e) {
            System.out.println("Error remoto");

        } catch (Exception e) {
            System.err.println("Excepcion en el servidor: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server(args[0]);
    }   
}