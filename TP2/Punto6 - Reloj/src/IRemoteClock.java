import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteClock extends Remote {
    long getHora() throws RemoteException;
}