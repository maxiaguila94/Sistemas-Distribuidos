package remoteinterfaces;
import java.io.File;
import java.io.Serializable;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import java.text.SimpleDateFormat;


public class FileMetadata implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final int CLOSED = 0;
	public static final int OPENED = 1;
	
	private String fileName;
    private String creationTime; 
    private String lastAccessTime; 
    private String lastModifiedTime; 
    private long size;
    private int status;
    
    public FileMetadata(File f) {
        this.fileName = f.getName();
        Path path = f.toPath();
        try {            
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            this.creationTime = this._getAttrToString(attr.creationTime());
            this.lastAccessTime = this._getAttrToString(attr.lastAccessTime());
            this.lastModifiedTime = this._getAttrToString(attr.lastModifiedTime());
            this.size = attr.size();
            this.status = CLOSED;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public FileMetadata(String filename) {
    	this.fileName = filename;
    	File f = new File(this.fileName);
    	Path path = f.toPath();
    	try {
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            this.creationTime = this._getAttrToString(attr.creationTime());
            this.lastAccessTime = this._getAttrToString(attr.lastAccessTime());
            this.lastModifiedTime = this._getAttrToString(attr.lastModifiedTime());
            this.size = attr.size();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public String getFileName() {
    	return this.fileName;
    }

    public int getStatus(){
    	return this.status; 
    }
    
    public void setStatus(int status) {
    	this.status = status;
    }
    
    public long getSize(){
        return this.size;
    }

    public FileTime getCreationDate(){
        return this._getAtrrToFileTime(this.creationTime);
    }

    public FileTime getLastAccessTime(){
        return this._getAtrrToFileTime(this.lastAccessTime);
    }

    public FileTime getLastModifiedTime(){
        return this._getAtrrToFileTime(this.lastModifiedTime);
    }

    private FileTime _getAtrrToFileTime(String attr) {
        try {
        	long milis;
            milis = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(attr).getTime();
            FileTime fileTime = FileTime.fromMillis(milis);
            return fileTime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    private String _getAttrToString(FileTime attr) {
    	return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format((attr.toMillis()));
    }
    
}
