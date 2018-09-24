
import java.rmi.Naming;                    /* lookup         */
import java.rmi.registry.Registry;         /* REGISTRY_PORT  */

public class Servidor 
{
    
    public Servidor(String host) {
        try 
        {

            IRemoteSumRes objetoRemoto = new RemoteSumRes();
	    String rname = "//" + host + ":" + Registry.REGISTRY_PORT  + "/RemoteSumRes";
            Naming.rebind (rname, objetoRemoto);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Servidor(args[0]);
    }
}
