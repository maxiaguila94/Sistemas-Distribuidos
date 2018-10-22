package models;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.UUID;

import server.Config;

public class UserModel {

    private String csvFile;
    private String username;
    private String password;
    private String uid;

    public UserModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    public UserModel create () throws IOException{
    	final String NEXT_LINE = "\n" ;
    	if (!this.exists()){
        	try {
        		this.csvFile = Config.getProperties().getProperty("users_file");
        		FileWriter w = new FileWriter(this.csvFile, true);
                StringBuilder sb = new StringBuilder();
                this.setUID(UUID.randomUUID().toString());
                sb.append(this.getUsername()
                ).append(",").append(this.getPass()
                ).append(",").append(this.getUID()
                ).append(NEXT_LINE); 
                
                w.append(sb);
                w.close();
                
                return this;
        		
        	} catch (Exception e) {
                e.printStackTrace();
                return null;
            } 
        } else {
            return null;
        }

    }

    public boolean exists(){

        String line = "";
        String cvsSplitBy = ",";
    
        try {
        	this.csvFile = Config.getProperties().getProperty("users_file");
        	BufferedReader br = new BufferedReader(new FileReader(this.csvFile));
            while ((line = br.readLine()) != null) {
                   
                String[] users = line.split(cvsSplitBy);

                if (users[0].equals(this.getUsername()) && 
                    users[1].equals(this.getPass())){
                    
                        this.setUID(users[2]);;
                        return true;
                }
            }
            return false;
    
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }

    public String getUsername(){
        return this.username;
    }

    public String getPass(){
        return this.password;
    }

    public String getUID(){
        return this.uid;
    }

    public void setUID(String uid){
        this.uid = uid;
    }
}
