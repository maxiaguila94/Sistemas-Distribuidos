package remoteobjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.io.BufferedReader;
import java.io.FileReader;

import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.charset.*;
import java.nio.ByteBuffer;
import java.nio.file.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileProxy implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File file;

//	private FileOutputStream fileOutputStream;
//	private FileInputStream fileInputStream;
	
	private String file_name;
    private String owner;
    private String file_id;
    private int file_length;
    public byte[] file_buffer;
    private FileMetadata metadata;
    public static final String csvFile = "src/models/fileowners.csv";

    public FileProxy(String file_name) throws IOException {
        this.file_name = file_name;
        this.file = new File(this.file_name);
//        this.fileInputStream =  this.getFileInputStream();
//        this.fileOutputStream = this.getFileOutputStream();
//        if (this.exists()) {
//            this.loadOwner();
//            this.metadata = new FileMetadata(this.file, this.file_name);
//        }else {
//        	this.file.createNewFile();
//        }
    }

    
//    public FileOutputStream getFileOutputStream() throws FileNotFoundException {
//    	if (this.fileOutputStream == null) {
//    		this.fileOutputStream = new FileOutputStream(this.getFile(), true);
//    	}
//    	return this.getFileOutputStream();
//    }
    
//    public FileInputStream getFileInputStream() throws FileNotFoundException {
//    	if(this.fileInputStream == null) {
//    		this.fileInputStream = new FileInputStream(this.getFile()); 
//    	}
//    	return this.getFileInputStream();
//    }
    
    public void fileBufferInitialize(){
        if (this.file_buffer == null)
            this.file_buffer = new byte[1024];
    }

    public boolean exists(){
        return this.file.exists() && !this.file.isDirectory(); 
    }

    public boolean isNewerThan(FileTime date) throws IOException{
        // archivo local es mas nuevo que el remoto
        return this.getLastModified().compareTo(date) > 0; 
    }

    public boolean isOwner(String userID){
        return this.owner.equals(userID);
    }

    
    
    public void loadOwner (){
        String csvFile = this.csvFile;
        String line = "";
        String cvsSplitBy = ",";
    
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                   
                String[] files = line.split(cvsSplitBy);

                if (files[0].equals(this.getFileName())){
                    this.setOwner(files[1]);
                }
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    
    
    public FileTime getLastModified() throws IOException{
        return this.getMetadata().getLastModifiedTime();
    }

    public String getHash(String hashAlgorithm) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        try {
            MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
            FileInputStream fis = new FileInputStream(this.getFile());
            byte[] dataBytes = new byte[this.file_length];
            
            int nread = 0; 
            
            while ((nread = fis.read(dataBytes)) != -1) {
              md.update(dataBytes, 0, nread);
            };
        
            byte[] mdbytes = md.digest();
           
            //convert the byte to hex format
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }     
            return sb.toString(); 

        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("No se encontró el algoritmo para el hash");
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("Error creando stream para archivo");
        } catch (IOException e) {
            throw new IOException("Error intentando leer archivo");
        }
    }

    public String getFileName(){
        return this.file_name;
    }

    public File getFile(){
        return this.file;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }
    
    public String getFileId(){
        return this.file_id;
    }

    public void setFileId(String id){
        this.file_id = id;   
    }
    public FileMetadata getMetadata(){
        return this.metadata;
    }

    public void setMetadata (FileMetadata fileMetadata){
        this.metadata = fileMetadata;
    }

    public int getFileLength(){
        return this.file_length;
    }

    public void setFileLength(int length){
        this.file_length = length;
    }
}
