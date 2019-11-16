package controller;

import com.mycompany.tubes.MenuUser;
import com.mycompany.tubes.MenuUserEdit;
import java.io.IOException;
import model.ModelUser;
import org.json.JSONObject;

public class User {
    public static JSONObject session;
    public void index() throws IOException {
        MenuUser m = new MenuUser();
        m.index();
    }    
    public void kelolaUser() throws IOException{
        MenuUserEdit v = new MenuUserEdit();
        v.index();
    }
    public void editUser(String id,String ktp, String nama, String handphone, String email, String pass) throws IOException{
        ModelUser d = new ModelUser();                
        d.editUser(id,ktp, nama, handphone, email, pass);        
    }
}
