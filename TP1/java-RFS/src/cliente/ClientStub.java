package cliente;

import objetosremotos.*;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientStub {
	private Socket s;
	ObjectInputStream entrada;
	ObjectOutputStream salida;

	public String archivo_remoto;

	public ClientStub(String hostname, int port) {
		try {
			s = new Socket(hostname, port);
			entrada = new ObjectInputStream(s.getInputStream());
			salida = new ObjectOutputStream(s.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void rfs_open(String file_name) {

		RFSOpen archivo_remoto = new RFSOpen(file_name);
		try {
			salida.writeObject(archivo_remoto);
			File archivo_abierto = (File) entrada.readObject();
			// return archivo_abierto;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return null;

	}

	public void rfs_read(File archivo_remoto_abierto, byte[] buffer) {

		int count = 0;
		RFSRead obj_remoto = new RFSRead(archivo_remoto_abierto, count, buffer);
		salida.writeObject(obj_remoto);
		RFSRead lector = (RFSRead) entrada.readObject();

		// return lector;
	}

	public int rfs_wrtite(String file_name, byte[] buffer) {

		return 0;
	}

	public void rfs_close(File archivo_remoto) {

		RFSClose obj_remoto = new RFSClose(archivo_remoto);

		salida.writeObject(obj_remoto);

		RFSClose archivo = (RFSClose) entrada.readObject();

		// return archivo.cerrado;
	}
}
