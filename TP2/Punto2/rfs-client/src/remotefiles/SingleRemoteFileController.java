package remotefiles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import client.RFSClient;
import remoteinterfaces.FileMetadata;

public class SingleRemoteFileController implements ActionListener {
	private RFSClient model;
	private SingleRemoteFilePanel view;
	
	public SingleRemoteFileController (RFSClient model) {
		this.model = model;
	}

	public void setView(SingleRemoteFilePanel view) {
		this.view = view;
	}
	
	public RFSClient getModel() {
		return this.model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String methodName = e.getActionCommand();
				
		if (methodName.equals("rmtRead")) {
					
			FileMetadata remote_file = this.view.getRemoteFileMetadata();
			this.rmtRead(remote_file);
					
		} else if (methodName.equals("lclWrite")) {

			FileMetadata local_file = this.view.getLocalFile(); 
			this.lclWrite(local_file);
		}
	}

	
	public FileMetadata lookUpLocalCopy(String file_name) throws Exception {
		
		System.out.println(file_name);
		System.out.println("NOMBRE DE ARCHIVO");
		System.out.println("Archivo"+file_name);
		System.out.println(file_name);
		String[] f = file_name.split("/");
		System.out.println("nombre de archivo");
		System.out.println(f.length);
		System.out.println(f[2]);
		
		try {
			FileMetadata file = this.model.lookUpLocalCopy(f[2]);
			if (file == null)
				return null;
			return file;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
		
	}
	

	public void rmtRead(FileMetadata remote_file) {
		this.getModel().readFileFromServer(remote_file);
		this.view.closez();
		
	}

	public void lclWrite(FileMetadata local_file) {
		File file = new File(local_file.getFileName());
		this.model.writeFileToServer(file);
		this.view.closez();
	}

}