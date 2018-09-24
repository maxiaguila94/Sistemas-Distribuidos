import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/* sobreescribe la clase RMISocketFactory 
y define un socket al que se le puede asignar un tiempo de timeout al momento de crearlo. */

class MiSocketFactory extends RMISocketFactory {
    private int timeout;

    public MiSocketFactory(int time) {
        this.timeout = time;
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        return getDefaultSocketFactory().createServerSocket(port);
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        Socket timeout_socket = getDefaultSocketFactory().createSocket(host, port);
        timeout_socket.setSoTimeout(timeout * 1000);
        return timeout_socket;
    }

}