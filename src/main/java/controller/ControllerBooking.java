package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.mycompany.tubes.MenuBooking;

import org.json.JSONException;

import model.ModelTrains;

public class ControllerBooking {
    ModelTrains m;

    public ControllerBooking() throws FileNotFoundException {
        m = new ModelTrains();
    }

    public void index() throws FileNotFoundException, IOException {
        MenuBooking b = new MenuBooking();

        b.searchSchedule();
    }

    public void booking(String tgl, String kode, ArrayList<String> kursi) throws JSONException, IOException {
        m.booking(tgl, kode, kursi);

        m.write();
    }

    public ArrayList<Schedule> getSchedule(String origin, String destination, String tgl) throws FileNotFoundException, IOException {
        String rute = m.convertRute(origin, destination);
        
        return m.readSearch(rute, tgl);
    }

    public Train getTrain(String jadwal, String tgl) throws FileNotFoundException {
        return m.getTrain(jadwal, tgl);
    }

    public void subMenu(int act, String date) throws JSONException, IOException {
        MenuBooking b = new MenuBooking();
        if (act == 1) {
            b.bookTicket(date);
        } else if (act == 2) {

        }
    }
}