package models;
import java.io.IOError;
import java.io.IOException;

public class AuthModel {

    public UserModel user;

    public UserModel login(String username, String password){

        this.user = new UserModel(username, password);
        if(this.user.exists()){
            return user;
        }
        return null;
    }

    public void logout(){
        this.user = null;
    }

    public boolean isLoggedIn (){
        return this.user == null;
    }

    public UserModel signup(String username, String password) throws IOException{
        this.user = new UserModel(username, password);
        return this.user.create();       
    }
}
