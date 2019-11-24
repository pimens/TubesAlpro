package controller;

import com.mycompany.tubes.MainMenu;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import model.ModelUser;
import org.json.JSONObject;

public class Home {
    
    //hal menu utama
    public void index() throws FileNotFoundException, IOException {
        MainMenu m = new MainMenu();
        m.index();
    }

    //hal login
    public void login() throws FileNotFoundException, IOException, ParseException {
        MainMenu m = new MainMenu();
        m.login();
    }

    //hal register
    public void register() throws FileNotFoundException, IOException {
        MainMenu m = new MainMenu();
        m.registerUser();
    }

    public void addUser(String ktp, String nama, String handphone, String email, String pass) throws FileNotFoundException, IOException {
        ModelUser d = new ModelUser(ktp, handphone, nama, email, pass);
        d.addUser();
        this.index();
    }

    public JSONObject doLogin(String email, String pass) throws FileNotFoundException, IOException {
        ModelUser d = new ModelUser();
        JSONObject object = d.doLogin(email, pass);
        return object;
    }

    public boolean isNumber(String str,int l) {
        boolean cek=false;
        if((str != null) && (!str.equals(""))
                && (str.matches("^[0-9]*$")) && str.length()==l){
            cek=true;
        }       
        return cek;
    }
    public boolean isNumber(String str,int l,int l2) {
        boolean cek=false;
        if((str != null) && (!str.equals(""))
                && (str.matches("^0[0-9]*$")) && (str.length()==l || str.length()==l2)){
            cek=true;
        }       
        return cek;
    }
    public boolean isString(String str) {
        return ((str != null)
                && (!str.equals(""))
                && (str.matches("^[a-zA-Z\\s]*$")));
    }
    public boolean isEqual(String str,String str2) {
        return str.equals(str2);
    }
    

}
