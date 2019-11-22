package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.mycompany.tubes.MenuTrainsByRoute;
import com.mycompany.tubes.MenuTrainsByRouteAdd;
import com.mycompany.tubes.MenuTrainsByRouteShow;
import com.mycompany.tubes.MenuTrainsByRouteDelete;

import model.ModelTrainsByRoute;

public class ControllerTrainsByRoute {
    ModelTrainsByRoute m;

    public ControllerTrainsByRoute() throws FileNotFoundException, IOException{
        m = new ModelTrainsByRoute();
    }

    public void index() throws FileNotFoundException, IOException {
        MenuTrainsByRoute mu = new MenuTrainsByRoute();
        mu.index();
    }

    public void subMenu(int act) throws FileNotFoundException, IOException {
        if (act == 1) {
            MenuTrainsByRouteAdd mu = new MenuTrainsByRouteAdd();
            mu.index();
        } else if (act == 2) {
            MenuTrainsByRouteShow mu = new MenuTrainsByRouteShow();
            mu.index();
        } else if (act == 3) {
            MenuTrainsByRouteDelete mu = new MenuTrainsByRouteDelete();
            mu.index();
        }
    }

    public ArrayList<String> showData(String rute) {
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> codes = m.getTrainCodes();
        ArrayList<String> routes = m.getRoutes();
        for (int i=0; i<codes.size(); i++) {
            if (rute.equals(routes.get(i))) {
                temp.add(codes.get(i));
            }
        }

        return temp;
    }

    public void addData(String rute, ArrayList<String> data) throws FileNotFoundException, IOException {
        for (String d : data) {
            m.addRoutes(rute);
            m.addTrainCodes(d);
        }
        m.write();
    }

    public boolean deleteData(String rute, String kode) throws FileNotFoundException, IOException {
        if (m.del(kode,rute)) {
            m.write();
        }
        return (m.del(kode,rute));
    }
}