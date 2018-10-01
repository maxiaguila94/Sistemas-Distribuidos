import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Clock extends Remote {
    long getHora() throws RemoteException;
}