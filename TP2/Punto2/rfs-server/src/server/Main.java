package server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class Main {

	public static void main(String[] args) {
		try {
			new RFSServer();
		} catch (RemoteException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
