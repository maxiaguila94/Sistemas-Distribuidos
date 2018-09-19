
import java.rmi.Naming;                    /* lookup         */
import java.rmi.registry.Registry;         /* REGISTRY_PORT  */

import java.net.MalformedURLException;     /* Exceptions...  */
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {
    
    public Cliente(String host) 
    {
        try
        {
	    String rname = "//" + host + ":" + Registry.REGISTRY_PORT + "/RemoteMulDiv";
           IRemoteMulDiv objetoRemoto = 
                (IRemoteMulDiv)Naming.lookup (rname);

   	    String rname2 = "//" + "172.17.0.2" + ":" + "1099" + "/RemoteSumRes";
        IRemoteSumRes objetoRemoto2 = 
             (IRemoteSumRes)Naming.lookup (rname2);
           
            System.out.println (objetoRemoto.multiplica(2,3));
            System.out.println(objetoRemoto.divide(10, 2));
            System.out.println(objetoRemoto2.suma(2, 10));
            System.out.println(objetoRemoto2.resta(10, 5));
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
