package remoteobjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Exceptions.CanNotFileOpenException;
import Exceptions.UserNotLoggedInException;
import models.FileModel;
import remoteinterfaces.FileMetadata;
import remoteinterfaces.FileProxy;
import remoteinterfaces.IRFSConstants;
import remoteinterfaces.IRemoteFileSystem;
import server.Config;

public class FileSystemService extends UnicastRemoteObject implements IRemoteFileSystem{
	
	private static final long serialVersionUID = 1L;

	AuthService _authService;
	public List<FileProxy> remote_files_opened;
	private FileModel fileModel;
	
	
	public FileSystemService(AuthService _authService) throws RemoteException {
		super();
		this._authService = _authService;
		this.fileModel = new FileModel();
		this.remote_files_opened = new ArrayList<FileProxy>();
	}

    public FileProxy getOpenedFile(String file_id){
		FileProxy result = null;

		result = (FileProxy)this.remote_files_opened.stream()
		 	.filter(f -> f.getFileID().equals(file_id))
		 	.findFirst()
		 	.get();
        
        return result;        
    }
    
    
	public String open(String file_name, int mode, String user_token) throws RemoteException {
		
		if (!this._authService.isLoggedIn(user_token))
			throw new UserNotLoggedInException("El usuario no se encuentra registrado");

		FileProxy file;
		try {
			
			String dir = Config.getProperties().getProperty("home_path");
			file = new FileProxy(dir+file_name);
			if (!file.exist() && mode != IRFSConstants.OPEN_O_CREAT) {
				return null;
			}
			
			if (!file.exist())
				if (!this.fileModel.create(file_name, user_token))
					throw new RemoteException("Error al abrir el archivo");
				
			this.remote_files_opened.add(file);
			return file.getFileID();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CanNotFileOpenException("No fue posible abrir el archivo "+file_name);
		}
	}

	@Override
	public byte[] read(String file_id, int buffer_size) throws FileNotFoundException, IOException {
		
		byte[] buffer = new byte[buffer_size];
		FileProxy openedFile = this.getOpenedFile(file_id);
        int count = openedFile.getFileInputS().read(buffer);
        if (count == -1)
        	return null;
        return buffer;
	}

	@Override
	public void write(String file_id, int count, byte[] data) throws FileNotFoundException, IOException {
		
		FileProxy openedFile = this.getOpenedFile(file_id);		
		openedFile.getFileOutputS().write(data);
	
	}

	@Override
	public boolean close(String file_id) throws RemoteException {
		
		FileProxy file = this.getOpenedFile(file_id);
		return this.remote_files_opened.remove(file);
	}

	
	
	@Override
	public List<FileMetadata> getAvailableRemoteFiles(String user_token) throws RemoteException {
		
		if (!this._authService.isLoggedIn(user_token))
			throw new UserNotLoggedInException("El usuario no se encuentra registrado");
		
		try {
			List<FileMetadata> files =  (List<FileMetadata>) this.fileModel.filterByOwner(user_token);
			if (files == null)
				System.out.println("no encontro archivos del propietario");
			return files;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RemoteException("No pudieron cargarse los archivos disponibles");
		}
	}
	
}
