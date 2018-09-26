import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteClock extends UnicastRemoteObject implements IRemoteClock{
    private static final long serialVersionUID = 8064792432550336467L;

	protected RemoteClock() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public long getHora() throws RemoteException {
		return System.currentTimeMillis(); // hora en milisegundos
	}
}

	