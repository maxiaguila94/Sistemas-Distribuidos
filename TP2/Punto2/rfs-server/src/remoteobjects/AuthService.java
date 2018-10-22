package remoteobjects;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import models.AuthModel;
import models.UserModel;
import remoteinterfaces.IRemoteAuth;
import remoteinterfaces.IRemoteFileSystem;

public class AuthService extends UnicastRemoteObject implements IRemoteAuth{
	private static final long serialVersionUID = 1L;
	private AuthModel _authModel;
	private List<UserModel> logged_users;
	private static FileSystemService _fileSystemService;
	
	
	public AuthService(AuthModel authModel) throws RemoteException {
		super();
		this._authModel = authModel;
		this.logged_users = new ArrayList<UserModel>();

	}
	
	
	@Override
	public String login(String username, String password) throws RemoteException {

    	UserModel user =  this._authModel.login(username, password);
    	if (user == null)
    		return null;
    	
    	this.logged_users.add(user);
    	
    	return user.getUID();
		
	}

	@Override
	public boolean logout(String user_token) throws RemoteException {
		// TODO Auto-generated method stub
		return this.logged_users.remove(this._getLoggedUser(user_token));
		
	}

	@Override
	public String signup(String username, String password) throws RemoteException {
		UserModel user;
		try {
			user = this._authModel.signup(username, password);
			if (user == null)
				return null;
			
			this.logged_users.add(user);
			
			return user.getUID();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public IRemoteFileSystem getFileSystemService(String user_token) throws RemoteException {
		
		if (this.isLoggedIn(user_token)) {
			if(this._fileSystemService == null)
				this._fileSystemService = new FileSystemService(this);
			
			return this._fileSystemService;
		}
		
		return null;
	}
	
	public boolean isLoggedIn(String user_token) {
		
		return this._getLoggedUser(user_token) != null;
		
	}
	
	private UserModel _getLoggedUser(String user_token) {
		
		UserModel user = (UserModel) this.logged_users
				.stream()
				.filter(u -> u.getUID().equals(user_token))
				.findFirst()
				.get();
		
		return user;
		
	}

}
