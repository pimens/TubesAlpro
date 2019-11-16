package controller;

import com.mycompany.tubes.MenuAdmin;
import com.mycompany.tubes.MenuPassengersManage;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.ModelPassengers;

public class ControllerAdmin {

    public void index() throws FileNotFoundException, IOException {
        MenuAdmin m = new MenuAdmin();
        m.index();
    }

    public void managePassenger() throws FileNotFoundException, IOException {
        MenuPassengersManage m = new MenuPassengersManage();
        m.index();
    }

    public ModelPassengers getDataByKTP(String ktp) throws FileNotFoundException {
        ModelPassengers p = new ModelPassengers();
        p.getDataByKTP(ktp);
        return p;
    }

    public ModelPassengers editPassenger(String nama, String hp, String email, String pass, String ktp, String id) throws FileNotFoundException, IOException {
        ModelPassengers p = new ModelPassengers();
        p.setEmail(email);
        p.setKtp(ktp);
        p.setId(id);
        p.setNama(nama);
        p.setNomorHandphone(hp);
        p.setPassword(pass);
        p.pushNewDataUser();
        return p;
    }
}
