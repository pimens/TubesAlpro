package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import com.mycompany.tubes.MenuTrainsByRoute;
import model.ModelTrainsByRoute;

public class ControllerTrainsByRoute {
    ModelTrainsByRoute m;

    public ControllerTrainsByRoute() throws FileNotFoundException, IOException{
        m = new ModelTrainsByRoute();
    }

    public void index(int act) throws FileNotFoundException, IOException {
        MenuTrainsByRoute mu = new MenuTrainsByRoute();
        mu.index(act);
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
}