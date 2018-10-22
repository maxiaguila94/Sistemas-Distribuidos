package models;

import java.util.List;
import java.util.UUID;

import remoteinterfaces.FileMetadata;
import server.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class FileModel {
	String csvFile;	
	
	public List<FileMetadata> filterByOwner(String owner) throws Exception {
        
        String line = "";
        String cvsSplitBy = ",";
        String dir = Config.getProperties().getProperty("home_path");
        
        List<FileMetadata> files = new ArrayList<>();
        try {
        	this.csvFile = Config.getProperties().getProperty("files_file");
        	BufferedReader br = new BufferedReader(new FileReader(this.csvFile));
            while ((line = br.readLine()) != null) {
                   
            	String[] file = line.split(cvsSplitBy);
            	
	                if (file[1].equals(owner)){
                	
	                	File f = new File(dir+file[0]);
	                	
	                	BasicFileAttributes attr = Files.readAttributes(f.toPath(), BasicFileAttributes.class);         	
	                	files.add(new FileMetadata(f));
	                	
                }
            }
            if(files.isEmpty()) {
            	return null;
            }
            
            return files;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null; 
	}

	public boolean create(String file_name, String user_token) {
    	try {
    		final String NEXT_LINE = "\n" ;
    		this.csvFile = Config.getProperties().getProperty("files_file");
    		FileWriter w = new FileWriter(this.csvFile, true);
            StringBuilder sb = new StringBuilder();
            sb.append(file_name
            ).append(",").append(user_token
            ).append(NEXT_LINE); 
            
            w.append(sb);
            w.close();
            
            return true;
    		
    	} catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
		
	}
	
}
