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

	
	@Override
	public FileProxy open(String file_name, String user_token) throws RemoteException {
		
		if (!this._authService.isLoggedIn(user_token))
			throw new UserNotLoggedInException("El usuario no se encuentra registrado");

		FileProxy file;
		try {
			
			file = new FileProxy(file_name);
			file.setFileId(UUID.randomUUID().toString());
			this.remote_files_opened.add(file);
			return file;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			throw new CanNotFileOpenException("No fue posible abrir el archivo "+file_name);
		
		}
	}

	@Override
	public int read(FileProxy file, byte[] buffer, long offset) throws RemoteException {

		file.fileBufferInitialize();
		FileInputStream is;
		try {
			is = new FileInputStream(file.getFile());
			is.skip(offset);        
			int count = is.read(file.file_buffer);
			return count;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RemoteException("Error leyendo el archivo "+file.getFileName());
		}
	}

	@Override
	public void write(FileProxy remoteFile, byte[] buffer, int count) throws RemoteException {
		FileOutputStream out;
		try {
			out = new FileOutputStream(remoteFile.getFile(), true);
			out.write(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RemoteException("Error al escribir el archivo"+remoteFile.getFileName());
		}
	}

	@Override
	public boolean close(FileProxy file) throws RemoteException {
		
		FileProxy file_to_remove = this.remote_files_opened
			.stream()
			.filter(f -> f.getFileId().equals(file.getFileId()))
			.findFirst()
			.get();

		return this.remote_files_opened.remove(file_to_remove);
	}

	
	
	@Override
	public List<FileMetadata> getAvailableRemoteFiles(String user_token) throws RemoteException {
		
		if (!this._authService.isLoggedIn(user_token))
			throw new UserNotLoggedInException("El usuario no se encuentra registrado");
		
		try {
			List<FileMetadata> files =  (List<FileMetadata>) this.fileModel.filterByOwner(user_token);
			for (FileMetadata f : files) {
				System.out.println("ARCHIVO:");
				System.out.println(f.getFileName());
				System.out.println(f.getCreationDate());
			}
			
			return files;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RemoteException("No pudieron cargarse los archivos disponibles");
		}
	}
	
}
