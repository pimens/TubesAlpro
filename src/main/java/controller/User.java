package controller;

import com.mycompany.tubes.MenuUser;
import com.mycompany.tubes.ViewManageUser;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import model.DataUser;
import org.json.JSONObject;

public class User {
    public static JSONObject session;
    public void index() throws IOException {
        MenuUser m = new MenuUser();
        m.index();
    }    
    public void kelolaUser() throws IOException{
        ViewManageUser v = new ViewManageUser();
        v.index();
    }
    public void editUser(String ktp, String nama, String handphone, String email, String pass) throws IOException{
        DataUser d = new DataUser();                
        d.editUser(ktp, nama, handphone, email, pass);        
    }
}
