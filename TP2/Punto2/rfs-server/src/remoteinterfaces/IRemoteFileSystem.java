package remoteinterfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface IRemoteFileSystem extends Remote{

	public String open(String file_name, int mode, String user_token) throws RemoteException;
	public byte[] read(String file_id, int buffer_size) throws RemoteException, FileNotFoundException, IOException;
	public void write(String file_id, int count, byte[] data) throws RemoteException, FileNotFoundException, IOException;
	public boolean close(String file_id) throws RemoteException;
	
	public List<FileMetadata> getAvailableRemoteFiles(String user_token) throws RemoteException;
	
}

