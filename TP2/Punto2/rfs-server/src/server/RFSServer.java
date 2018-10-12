package server;

import java.net.MalformedURLException;
import java.rmi.Naming; 
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import remoteinterfaces.IRFSServer;
import remoteinterfaces.IRemoteAuth;
import remoteobjects.AuthService;
import models.AuthModel;

public class RFSServer extends UnicastRemoteObject implements IRFSServer{

	private static final long serialVersionUID = 1L;
	
	protected RFSServer() throws RemoteException, MalformedURLException {
		super();
		LocateRegistry.createRegistry(1099);
		Naming.rebind("rfsserver", this);
	}

	@Override
	public IRemoteAuth getAuthService() throws RemoteException {
		return new AuthService(new AuthModel());
	}	
}

    
    
