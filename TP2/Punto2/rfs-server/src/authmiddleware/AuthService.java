package authmiddleware;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import models.AuthModel;
import models.UserModel;
import remoteinterfaces.FileMetadata;
import remoteinterfaces.IRemoteAuth;
import remoteinterfaces.ResponseLogin;

public class AuthService extends UnicastRemoteObject implements IRemoteAuth{
	private static final long serialVersionUID = 1L;
	private AuthModel _authModel;
	
	protected AuthService() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String login(String username, String password) throws RemoteException {

    	UserModel user =  this._authModel.login(username, password);
    	if (user == null)
    		return null;
    	
    	return user.getUID();
		
	}

	@Override
	public boolean logout(String user_token) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String signup(String username, String password) throws RemoteException {
		UserModel user;
		try {
			user = this._authModel.signup(username, password);
			if (user == null)
				return null;
			
			return user.getUID();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
