package controller;

import com.mycompany.tubes.MenuTimes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import model.ModelTimes;
import org.json.JSONArray;

public class ControllerTime {

    MenuTimes m;
    ModelTimes mt;

    public ControllerTime() throws FileNotFoundException {
        m = new MenuTimes();
        mt = new ModelTimes();
    }

    public void index() throws FileNotFoundException, IOException, ParseException {
        m.index();
    }

    public void menuGenerateWaktu() throws IOException, FileNotFoundException, ParseException {
        m.generateWaktuMenu();
    }

    public void menuShowWaktu() throws IOException, FileNotFoundException, ParseException {
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

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        ControllerTime c = new ControllerTime();
        c.index();

    }
}
