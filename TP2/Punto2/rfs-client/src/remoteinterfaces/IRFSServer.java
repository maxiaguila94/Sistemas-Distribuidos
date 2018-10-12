package remoteinterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRFSServer extends Remote{

	IRemoteAuth getAuthService() throws RemoteException;
	
}
