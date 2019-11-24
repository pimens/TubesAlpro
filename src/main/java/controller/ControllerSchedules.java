package controller;

import com.mycompany.tubes.MenuGenerateScheduleShow;
import com.mycompany.tubes.MenuGenerateSchedules;
import com.mycompany.tubes.MenuGenerateSchedulesMain;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import model.ModelSchedules;

/**
 *
 * @author pmen
 */
public class ControllerSchedules {

    ModelSchedules model;

    public ControllerSchedules() {
        model = new ModelSchedules();
    }

    public void index() throws IOException, FileNotFoundException, ParseException {
        MenuGenerateSchedulesMain m = new MenuGenerateSchedulesMain();
        m.index();
    }

    //======================generate sch
    public void menuGenerateSchedules() throws IOException, FileNotFoundException, ParseException {
        MenuGenerateSchedules m = new MenuGenerateSchedules();
        m.index();
    }

    public void generateSchedules() throws ParseException, IOException {
        model.genSch();
    }

    //========================end generate sch
    public void showJadwal() throws FileNotFoundException, IOException {
        MenuGenerateScheduleShow m = new MenuGenerateScheduleShow();
        m.index();
    }

    public ArrayList<Schedule> getAllSchedules() throws FileNotFoundException, IOException {
        return model.readSearch();
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        ControllerSchedules c = new ControllerSchedules();
        c.index();
    }
}
