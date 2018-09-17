package models;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import remoteobjects.FileMetadata;

public class FileModel {
	String csvFile = "src/models/files.csv";
	
	public FileModel() {
		
	}
	
	
	public List<FileMetadata> filterByOwner(String owner) {
        String csvFile = this.csvFile;
        String line = "";
        String cvsSplitBy = ",";
        String dir = "src/server/";
        List<FileMetadata> files = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                   
            	String[] file = line.split(cvsSplitBy);
            	
	                if (file[1].equals(owner)){
                	
                	File f = new File(dir+file[0]);
	
	                	BasicFileAttributes attr = Files.readAttributes(f.toPath(), BasicFileAttributes.class);
                	
	                	System.out.println("FileName:"+file[0]);
	                	System.out.println("Last Modified:"+attr.lastModifiedTime());
	                	System.out.println("Creation time:"+attr.creationTime());
	                	System.out.println("Last Acces Time:"+attr.lastAccessTime());
	                	System.out.println("Tamaño"+attr.size());
	 
	                	
	                	files.add(new FileMetadata(dir+file[0]));
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
	
}
