
import java.rmi.Naming; /* lookup         */
import java.rmi.registry.Registry; /* REGISTRY_PORT  */
import java.rmi.server.RMISocketFactory;
import java.io.IOException;
import java.net.MalformedURLException; /* Exceptions...  */
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;

public class Cliente {

    public Cliente(String host) {
        try {
            // Se crea el socket factory, dandole un tiempo de 10 segundos
            try {
                RMISocketFactory.setSocketFactory(new MiSocketFactory(10));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Se crea la conexion
            String rname = "//" + host + ":" + Registry.REGISTRY_PORT + "/RemoteSumRes";
            IRemoteSumRes objetoRemoto = (IRemoteSumRes) Naming.lookup(rname);
            try {
                System.out.println(objetoRemoto.suma(2, 10));
            } catch (UnmarshalException e) {
                System.out.println("Timeout...");
            }
            try {
                System.out.println(objetoRemoto.resta(10, 5));
            } catch (UnmarshalException e) {
                System.out.println("Timeout...");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Cliente(args[0]);
    }

}
