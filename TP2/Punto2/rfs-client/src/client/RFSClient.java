package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.xml.transform.stax.StAXSource;

import remoteinterfaces.FileMetadata;
import remoteinterfaces.FileProxy;
import remoteinterfaces.IRFSServer;
import remoteinterfaces.IRemoteAuth;
import remoteinterfaces.IRemoteFileSystem;

public class RFSClient {

	private String user_token = null;
	private boolean connected = false;
//	public ArrayList<FileProxy> remote_files_opened; 
	private List<FileMetadata> availableFiles;
	private IRFSServer server;
	private IRemoteAuth authService;
	private IRemoteFileSystem remoteFileSystem;
	
	public RFSClient() throws UnknownHostException, IOException{

		this.availableFiles = new ArrayList<FileMetadata>();
		
	}
	

	// CONNECT	
	public void connect(String hostname, String port) throws NumberFormatException, UnknownHostException, IOException, Exception {
		
		this.server = (IRFSServer)Naming.lookup("//"
				+ hostname + ":"
				+ Registry.REGISTRY_PORT + "/rfsserver");
		
		this.authService = (IRemoteAuth)this.server.getAuthService();
		
	}	
	
	// LOGIN	
	public void login(String username, String password) throws Exception{
		
		
		String user_token = this.authService.login(username, password);
		if(user_token == null) 
			throw new Exception("No se ha podido iniciar sesión");

		
		this.setUserToken(user_token);			
		this.remoteFileSystem = this.authService.getFileSystemService(this.getUserToken());		
		this.setAvailableFiles((List<FileMetadata>)this.remoteFileSystem.getAvailableRemoteFiles(user_token));

	}
	
	// SIGNUP
	public void signUp(String username, String password) throws Exception {
		
		String user_token = this.authService.signup(username, password);
		if (user_token == null) {
			throw new Exception("No pudo crearse la cuenta");
		}
		this.setUserToken(user_token);		
		this.remoteFileSystem = this.authService.getFileSystemService(this.getUserToken());		
		this.setAvailableFiles((List<FileMetadata>)this.remoteFileSystem.getAvailableRemoteFiles(user_token));

	}
	
	
	
	
	// WRITE FILE TO SERVER 
	public void writeFileToServer(File file) {
		
		
		try {
			FileProxy remote_file = this.open(file.getName(), this.getUserToken());
			
			if( remote_file != null) {
				
				this.write(file, remote_file);
				this.close(remote_file);
			} else {
				System.out.println("no se pudo abrir el archivo");
			}
					
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// READ FILE FROM SERVER
	public void readFileFromServer(FileMetadata file) {
		try {
			FileProxy remote_file = this.open(file.getFileName(), this.getUserToken());
			
			if( remote_file != null) {
				
				this.read(remote_file);
				this.close(remote_file);
			} else {
				System.out.println("no se pudo abrir el archivo");
			}
					
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public FileProxy open(String file_name, String user_token) throws ClassNotFoundException, IOException, Exception {
		
		FileProxy file = this.remoteFileSystem.open(file_name, user_token);
		if (file == null) 
			return null;
		return file;
	}
	
	public void read(FileProxy file) throws ClassNotFoundException, Exception {
		byte[] buffer = new byte[1024];
		
		String[] file_name = file.getFileName().split("/");
		
		String dir = new Config().getProperties().getProperty("home_path");
		String path = dir+file_name[2]; 
		File f = new File(path);
		
		if (!f.exists())
			f.createNewFile();
		
		FileOutputStream out = new FileOutputStream(f, true);
		
		int count = 0;
		long offset = 0;
		while ((count = this.remoteFileSystem.read(file, buffer, offset)) !=-1) {
			offset = offset + count;
			out.write(buffer, 0, count);	
		}
		out.close();
			
	}
	
	public void write(File file, FileProxy remoteFile) throws ClassNotFoundException, IOException {
		
		if (file.exists() && !file.isDirectory()){

			byte[] buffer = new byte[1024];
			
			FileInputStream fi = new FileInputStream(file);
			int count = 0;
			while ((count = fi.read(buffer)) != -1)
				this.remoteFileSystem.write(remoteFile, buffer, count);
			
		}		
	}
	
	public void close(FileProxy remote_file) throws Exception {
		
		if (!this.remoteFileSystem.close(remote_file)) {
			throw new Exception("no pudo cerrarse el archivo remoto");
		}		
		
		
	}
	
	public List<FileMetadata> getAvailableFiles(){
		return this.availableFiles;
	}
	
	public String getUserToken() {
		return this.user_token;
	}
	
	public void setUserToken(String token) {
		this.user_token = token;
	}
	
	public void setAvailableFiles(List<FileMetadata> files) {
		if (this.availableFiles.isEmpty()) {
			availableFiles = new ArrayList<FileMetadata>(files);
		}
	}
	
	public FileMetadata lookUpLocalCopy(String file_name) throws Exception {
				
		String dir = new Config().getProperties().getProperty("home_path");
		System.out.println(dir);
		System.out.println(dir+file_name);
		
		File f = new File(dir+file_name);
		System.out.println("ARCHIVO AHORA SI");
		System.out.println(f.getName());
		System.out.println(f.getAbsolutePath());
		if (!f.exists()) {
			System.out.println("Archivo no existe");
			return null;
			
		}
		return new FileMetadata(f);
		
	}
	
	
	public boolean getStatus() {
		return this.connected;
	}

	
}

