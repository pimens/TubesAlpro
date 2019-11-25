package controller;

import com.mycompany.tubes.MenuTimes;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.ModelTimes;
import org.json.JSONArray;

public class ControllerTime {

    MenuTimes m;
    ModelTimes mt;

    public ControllerTime() throws FileNotFoundException {
        m = new MenuTimes();
        mt = new ModelTimes();
    }

    public void index() throws FileNotFoundException, IOException {
        m.index();
    }

    public void menuGenerateWaktu() throws IOException {
        m.generateWaktuMenu();
    }

    public void menuShowWaktu() throws IOException {
        m.showTimeMenu();
    }

    public void generateWaktu() throws IOException {
        mt.generateWaktu();
    }

    public void showTimes() {

    }

    public JSONArray getTime() {
        return mt.getTimes();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ControllerTime c = new ControllerTime();
        c.index();

    }
}
