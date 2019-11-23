package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.mycompany.tubes.MenuBooking;
import model.ModelTrains;

public class ControllerBooking {
    ModelTrains m;

    public ControllerBooking() {
        m = new ModelTrains();
    }

    public void index() throws FileNotFoundException, IOException {
        MenuBooking b = new MenuBooking();

        b.searchSchedule();
    }

    public ArrayList<Schedule> getSchedule(String origin, String destination, String tgl) throws FileNotFoundException, IOException {
        String rute = m.convertRute(origin, destination);
        
        return m.readSearch(rute, tgl);
    }

    public void subMenu(int act) {
        MenuBooking b = new MenuBooking();
        if (act == 1) {
            b.bookTicket();
        } else if (act == 2) {

        }
    }
}