package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.mycompany.tubes.MenuBooking;
import model.ModelStationsByRoutes;

import org.json.JSONException;

import model.ModelTrains;

public class ControllerBooking {

    ModelTrains m;
    ModelStationsByRoutes s;

    public ControllerBooking() throws FileNotFoundException {
        m = new ModelTrains();
        s = new ModelStationsByRoutes();
    }

    public void index() throws FileNotFoundException, IOException {
        MenuBooking b = new MenuBooking();

        b.searchSchedule();
    }

    public void booking(String tgl, String kode, ArrayList<String> kursi) throws JSONException, IOException {
        m.booking(tgl, kode, kursi);

        m.write();
    }

    public String addBooking(String kode, String tgl, ArrayList<String> penumpang, ArrayList<String> kursi,long jumlah) throws JSONException, IOException {
        return (m.addBooking(kode,tgl,penumpang, kursi,jumlah));
    }

    public int checkPayment(String rek, String jumlah) throws FileNotFoundException {
        return (m.checkPayment(rek, jumlah, User.session.getString("KTP")));
    }

    public ArrayList<Schedule> getSchedule(String origin, String destination, String tgl) throws FileNotFoundException, IOException {
        
        //cari id orig & dest di json kota, cari di route yang id src dan dstnya ini, kode rutenya apa
        String idSrc = s.getIdByCity(origin);
        String idDest = s.getIdByCity(destination);
        String rute = m.convertRute(idSrc, idDest);
        return m.readSearch(rute, tgl);
    }

    public Train getTrain(String jadwal, String tgl) throws FileNotFoundException {
        return m.getTrain(jadwal, tgl);
    }

    public double getHarga(String tgl, String rute, String jenis) throws FileNotFoundException {
        return m.getHarga(tgl, rute, jenis);
    }

    public ArrayList<String> payment(String kode) throws JSONException, IOException {
        return m.payment(kode);
    }

    public void subMenu(int act, String date) throws JSONException, IOException {
        MenuBooking b = new MenuBooking();
        if (act == 1) {
            b.bookTicket(date);
        } else if (act == 2) {
            b.payment();
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ControllerBooking c = new ControllerBooking();
        c.index();
    }
}
