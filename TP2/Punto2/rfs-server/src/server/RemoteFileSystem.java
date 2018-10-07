package server;

import java.awt.List;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import remoteinterfaces.FileProxy;
import remoteinterfaces.IRemoteFileSystem;

public class RemoteFileSystem extends UnicastRemoteObject implements IRemoteFileSystem{
	private static final long serialVersionUID = 1L;

	protected RemoteFileSystem() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public FileProxy open(String file_name, String user_token) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int read(FileProxy file, byte[] buffer, long offset) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int write(FileProxy remoteFile, byte[] buffer, int count) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean close(FileProxy file) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List getAvailableRemoteFiles(String user_token) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
