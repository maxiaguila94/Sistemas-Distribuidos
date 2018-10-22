package remoteinterfaces;

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

	private static final long serialVersionUID = 1L;

	private File file;
	private String file_name;
    private String file_id;
    public byte[] file_buffer;
    private FileMetadata metadata;
    private FileInputStream in;
    private FileOutputStream out;
    
    
    public FileProxy(String file_name) throws IOException {
        this.file_name = file_name;
        this.file = new File(this.file_name);
        if (this.exists()) {            
            this.metadata = new FileMetadata(this.file);
    	}
        this.in = null;
        this.out = null;
        this.file_id = UUID.randomUUID().toString();
        
    }

    public boolean exist() {
    	return this.file.exists(); 
    }
    
    //GetInput/Output stream
    public FileInputStream getFileInputS () throws FileNotFoundException {
         if (this.in == null){
             this.in = new FileInputStream(this.getFile());
         }
        return this.in;
    }

    public FileOutputStream getFileOutputS () throws FileNotFoundException {
         if (this.out == null){
             this.out = new FileOutputStream(this.getFile());
         }
        return this.out;
    }

    public void close() {
    	try {
    		this.getFileInputS().close();
    		this.getFileOutputS().close();
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public String getFileID(){
        return this.file_id;
    }

    
    public void fileBufferInitialize(){
        if (this.file_buffer == null)
            this.file_buffer = new byte[1024];
    }

    public boolean exists(){
        return this.file.exists() && !this.file.isDirectory(); 
    }

    public String getFileName(){
        return this.file_name;
    }

    public File getFile(){
        return this.file;
    }
    
    public void setFileID(String id){
        this.file_id = id;   
    }
    public FileMetadata getMetadata(){
        return this.metadata;
    }

    public void setMetadata (FileMetadata fileMetadata){
        this.metadata = fileMetadata;
    }
}
