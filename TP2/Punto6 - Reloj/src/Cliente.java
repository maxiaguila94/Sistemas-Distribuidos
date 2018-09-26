import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente extends Thread {
    private RemoteClock objetoRemoto;
    private static int tolerancia = 300; // tolerancia de 5 minutos
    private static int granularidad = 500;
    private int delta;
    private boolean ajuste;

    // Constructor
    public Cliente(String host) {
        try {

            String rname = "//" + host + ":" + Registry.REGISTRY_PORT + "/RemoteClock";
           IRemoteClock objetoRemoto = (IRemoteClock)Naming.lookup (rname);

        } catch (MalformedURLException e) {
	    e.printStackTrace();
	    } catch (RemoteException e) {
	    e.printStackTrace();
	    } catch (NotBoundException e) {
	    e.printStackTrace();
	    }
        
        this.ajuste = false;
        this.start();
        

    }

    // Setters y Getters
    public void setDelta(int value) {
        this.delta = value;
    }

    public int getDelta() {
        return delta;
    }

    public int getTolerancia() {
        return tolerancia;
    }

    public int getGranularidad() {
        return granularidad;
    }

    public void setAjuste(boolean ajuste) {
        this.ajuste = ajuste;
    }

    public boolean getAjuste() {
        return this.ajuste;
    }

    @Override
    public void run() {

        if (this.getAjuste()) {
            while (true) {
                // La magia
            }
        }

    }

    public void algoritmoCristian(RemoteClock clock) {

    }

    public void ajustaClock(RemoteClock clock) {

    }

}