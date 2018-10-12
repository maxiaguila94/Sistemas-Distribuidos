package Exceptions;

import java.rmi.RemoteException;

public class UserNotFountException extends RemoteException{
	
	
	public UserNotFountException (String msg) {
		super(msg);
	}
}
