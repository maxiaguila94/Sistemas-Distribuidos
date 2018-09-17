package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import remoteobjects.FileProxy;
import remoteobjects.RFSCommand;
import remoteobjects.FileMetadata;
import remoteobjects.ResponseLogin;

public class RFSClient {

	private static ClientStub stub;
	private String user_token = null;
	private boolean connected = false;
	public ArrayList<FileProxy> remote_files_opened; 
	private List<FileMetadata> availableFiles;
	
	public RFSClient() throws UnknownHostException, IOException{
		this.remote_files_opened = new ArrayList<FileProxy>();
		this.availableFiles = new ArrayList<FileMetadata>();
	}
	
	
    public FileProxy getOpenedFile(String id, String file_name){
        FileProxy result = null;
        for (FileProxy f : this.remote_files_opened) {
            if (f.getFileId().equals(id) && f.getFileName().equals(file_name)){
                result = f;
                break;
            }
        }
        return result;        
    }

	// CONNECT	
	public void connect(String hostname, String port) throws NumberFormatException, UnknownHostException, IOException, Exception {
		stub = this.getStub(hostname, Integer.parseInt(port));
		this.connected = true;
	}	
	
	// LOGIN	
	public void login(String username, String password) throws ClassNotFoundException, IOException{
		
		ResponseLogin response = stub.login(username, password);
		this.setUserToken(response.getUserToken());
		List<FileMetadata> availableFiles = response.getAvailableFiles();
		if(!availableFiles.isEmpty())
			this.setAvailableFiles(response.getAvailableFiles());
	}
	
	// SIGNUP
	public void signUp(String username, String password) throws Exception {
		RFSCommand response = stub.signUp(username, password);
		if (response.error) {
			throw new Exception(response.getErrorMessaage());
		}
		this.setUserToken(response.getUserToken());
	}
	
	
	
	
	// WRITE FILE TO SERVER 
	public void writeFileToServer(File file) {
		
		
		try {
			FileProxy remote_file = this.open(file.getName(), this.getUserToken());
			
			if( remote_file != null) {
				
				this.write(file, remote_file);
				this.close();
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
				this.close();
			} else {
				System.out.println("no se pudo abrir el archivo");
			}
					
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	
	
	
	
	
	public FileProxy open(String file_name, String user_token) throws ClassNotFoundException, IOException, Exception {
		
		FileProxy file = stub.rfs_open(file_name, user_token);
		if (file == null) 
			return null;
		
		this.remote_files_opened.add(file);
		return file;
	}
	
	public void read(FileProxy file) throws ClassNotFoundException, Exception {
		byte[] buffer = new byte[1024];

		String path = "cliente-"+ file.getFileName(); 
		File f = new File(path);
		f.createNewFile();
		FileOutputStream out = new FileOutputStream(f, true);
		
		int count = 0;
		while ((count = stub.rfs_read(file, buffer)) !=-1) {
			stub.rfs_read(file, buffer);
			out.write(buffer);	
		}
			
	}
	
	public void write(File file, FileProxy remoteFile) throws ClassNotFoundException, IOException {
		
		if (file.exists() && !file.isDirectory()){

			System.out.println("existe y no es directorio");
			byte[] buffer = new byte[1024];
			
			FileInputStream fi = new FileInputStream(file);
			int count = 0;
			while ((count = fi.read(buffer)) != -1)
				stub.rfs_wrtite(remoteFile, buffer, count);
		}		
	}
	
	public void close() {
		System.out.println("Close remote file");
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
	
	public boolean getStatus() {
		return this.connected;
	}
	public ClientStub getStub(String hostname, int port) throws UnknownHostException, IOException {
		if(stub == null) {
			stub = new ClientStub(hostname, port);
		}
		return stub;
	}
	
}

