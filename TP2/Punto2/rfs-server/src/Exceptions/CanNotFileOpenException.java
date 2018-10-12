package Exceptions;

import java.rmi.RemoteException;

public class CanNotFileOpenException extends RemoteException {

	public CanNotFileOpenException(String msg) {
		super(msg);
	}
}
