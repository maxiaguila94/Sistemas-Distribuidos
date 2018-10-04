package remoteinterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteAuth extends Remote{

	public String login(String username, String password) throws RemoteException;
	public boolean logout(String user_token) throws RemoteException;
	public String signup(String username, String password) throws RemoteException;
	
}
