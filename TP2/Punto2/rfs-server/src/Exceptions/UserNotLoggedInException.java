package Exceptions;

import java.rmi.RemoteException;

public class UserNotLoggedInException extends RemoteException {

	public UserNotLoggedInException(String msg) {
		super(msg);
	}
}
