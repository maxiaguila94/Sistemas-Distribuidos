import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteSumRes extends UnicastRemoteObject implements IRemoteSumRes {

	protected RemoteSumRes() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8064792432550336467L;

	@Override
	public int suma(int a, int b) throws RemoteException {
		System.out.println(String.format("Sumando %d + %d", a, b));
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a + b;
	}

	@Override
	public int resta(int a, int b) throws RemoteException {
		System.out.println(String.format("Restando %d - %d", a, b));
		return a - b;
	}

}
