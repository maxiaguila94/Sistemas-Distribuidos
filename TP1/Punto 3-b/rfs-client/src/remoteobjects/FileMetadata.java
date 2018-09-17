package remoteobjects;
import java.io.File;
import java.io.Serializable;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class FileMetadata implements Serializable {
	
	public static final int CLOSED = 0;
	public static final int OPENED = 1;
	
	private String fileName;
    private String creationTime; 
    private String lastAccessTime; 
    private String lastModifiedTime; 
    private long size;
    private int status;
    
    public FileMetadata(File f, String file_name) {
        Path path = f.toPath();
        
        this.fileName = file_name;
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
    	this.fileName = "src/controllers/"+filename;
    	File f = new File(this.fileName);
    	Path path = f.toPath();
    	
    	if (f.exists()) {    		
    		try {
    			BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
    			this.creationTime = this._getAttrToString(attr.creationTime());
    			this.lastAccessTime = this._getAttrToString(attr.lastAccessTime());
    			this.lastModifiedTime = this._getAttrToString(attr.lastModifiedTime());
    			this.size = attr.size();
    		} catch (Exception e) {
    			// TODO: handle exception
    		}
    	} else {
    		this.fileName = null;
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
        	System.out.println("Entre a formatear..");
        	long milis;
            milis = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(attr).getTime();
            FileTime fileTime = FileTime.fromMillis(milis);
            System.out.println("Time: " + fileTime.toString());
            System.out.println("Fin de formateo !");
            return fileTime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    private String _getAttrToString(FileTime attr) {
    	return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format((attr.toMillis()));
    }
    
    public String parsedFileTimeToString (FileTime ft ) {
    	DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    	String dateCreated = df.format(ft);
    	return dateCreated;
    }
}
