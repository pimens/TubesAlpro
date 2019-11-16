
package controller;

import com.mycompany.tubes.MenuAdmin;
import com.mycompany.tubes.MenuPassengersManage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ControllerAdmin {
    public void index() throws FileNotFoundException{
        MenuAdmin m = new MenuAdmin();
        m.index();
    }
    public void managePassenger() throws FileNotFoundException, IOException{
        MenuPassengersManage m = new MenuPassengersManage();
        m.index();
    }
}
