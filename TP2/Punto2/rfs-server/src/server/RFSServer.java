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
	private static AuthService _authService;
	private static AuthModel _authModel;
	
	protected RFSServer() throws RemoteException, MalformedURLException {
		super();
		LocateRegistry.createRegistry(1099);
		Naming.rebind("rfsserver", this);
	}

	@Override
	public IRemoteAuth getAuthService() throws RemoteException {
		if (this._authService == null)
			this._authService = new AuthService(this.getAuthModel());
		return this._authService;
	}	
	
	private AuthModel getAuthModel() {
		if(this._authModel == null)
			this._authModel = new AuthModel();
		
		return this._authModel;
}
}

    
    
