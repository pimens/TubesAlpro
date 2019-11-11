package controller;

import com.mycompany.tubes.MenuUser;
import org.json.JSONObject;

public class User {
    public static JSONObject session;
    public void index() {
        MenuUser m = new MenuUser();
        m.index();
    }
}
