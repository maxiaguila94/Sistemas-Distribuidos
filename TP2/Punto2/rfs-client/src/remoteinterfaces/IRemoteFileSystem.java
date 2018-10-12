package remoteinterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface IRemoteFileSystem extends Remote{

	public FileProxy open(String file_name, String user_token) throws RemoteException;
	public int read(FileProxy file, byte[] buffer, long offset) throws RemoteException;
	public void write(FileProxy remoteFile, byte[] buffer, int count) throws RemoteException;
	public boolean close(FileProxy file) throws RemoteException;
	
	public List<FileMetadata> getAvailableRemoteFiles(String user_token) throws RemoteException;
	
}

