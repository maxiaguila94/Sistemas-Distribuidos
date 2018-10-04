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
    private String owner;
    private String file_id;
    private int file_length;
    public byte[] file_buffer;
    private FileMetadata metadata;
    public static final String csvFile = "src/models/fileowners.csv";

    public FileProxy(String file_name) throws IOException {
        this.file_name = file_name;
        this.file = new File(this.file_name);
        if (this.exists()) {            
            this.metadata = new FileMetadata(this.file, this.file_name);
    	}
    }
    
    public void fileBufferInitialize(){
        if (this.file_buffer == null)
            this.file_buffer = new byte[1024];
    }

    public boolean exists(){
        return this.file.exists() && !this.file.isDirectory(); 
    }


    public boolean isOwner(String userID){
        return this.owner.equals(userID);
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
