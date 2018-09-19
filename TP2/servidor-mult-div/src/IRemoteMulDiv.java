import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IRemoteMulDiv extends Remote {

	
	public int multiplica (int a, int b) throws RemoteException;
	public float divide (int a, int b) throws RemoteException;
}
