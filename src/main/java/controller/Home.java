package controller;

import com.mycompany.tubes.MainMenu;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.DataUser;
import org.json.JSONObject;

public class Home {
    //hal menu utama
    public void index() throws FileNotFoundException, IOException {
        MainMenu m = new MainMenu();
        m.index();
    }
    //hal login
    public void login() throws FileNotFoundException, IOException {
        MainMenu m = new MainMenu();
        m.login();
    }
    //hal register
    public void register() throws FileNotFoundException, IOException {
        MainMenu m = new MainMenu();
        m.registerUser();
    }
    public void addUser(String ktp, String nama, String handphone, String email, String pass) throws FileNotFoundException, IOException {
        //cek logic input
        DataUser d = new DataUser();
        d.addUser(ktp, nama, handphone, email, pass);
        this.index();
    }
    public JSONObject doLogin(String email, String pass) throws FileNotFoundException, IOException {
        //cek logic input
        DataUser d = new DataUser();
        JSONObject object = d.getDataById(email, pass);
        return object;
    }

}
