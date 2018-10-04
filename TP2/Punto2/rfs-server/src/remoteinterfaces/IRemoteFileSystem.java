package remoteinterfaces;

import java.awt.List;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IRemoteFileSystem extends Remote{

	public FileProxy open(String file_name, String user_token) throws RemoteException;
	public int read(FileProxy file, byte[] buffer, long offset) throws RemoteException;
	public int write(FileProxy remoteFile, byte[] buffer, int count) throws RemoteException;
	public boolean close(FileProxy file) throws RemoteException;
	
	public List getAvailableRemoteFiles(String user_token) throws RemoteException;
	
}

