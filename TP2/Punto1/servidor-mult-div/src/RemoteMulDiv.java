import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class RemoteMulDiv extends UnicastRemoteObject implements IRemoteMulDiv{

	protected RemoteMulDiv() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5489371336839281323L;

	@Override
	public int multiplica(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(String.format("Multiplicando %d * %d", a, b));
		return a*b;
	}

	@Override
	public float divide(int a, int b) throws RemoteException {
		System.out.println(String.format("Dividiendo %d / %d", a, b));
		return a/b;
	}

}
