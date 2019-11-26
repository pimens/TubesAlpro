package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.mycompany.tubes.MenuKota;
import com.mycompany.tubes.MenuRute;
import com.mycompany.tubes.MenuRute;

import model.ModelCities;
import model.ModelJSON;
import model.ModelRute;

public class ControllerRute extends ModelJSON{
    MenuRute m;
    ModelRute modelRute;

    //===================================Akses menu via controller=============================
    public void index() throws IOException, ParseException {
        m = new MenuRute();
        m.index();
    }

    public void showRute() throws IOException, ParseException {
        m = new MenuRute();
        m.showRuteMenu();
    }

    public void menuAddRute() throws IOException, ParseException {
        m = new MenuRute();
        m.addRuteMenu();
    }

    public void editRute() throws IOException, ParseException {
        m = new MenuRute();
        m.editRuteMenu();
    }

    public void deleteRute() throws IOException, ParseException {
        m = new MenuRute();
        m.deleteRuteMenu();
    }
    //===================================END Akses menu via controller=============================

    //===================================komunikasi JSON via Model=================================
    //lempar input user dari view ke model train baru di model inputan ambil
    public void addRute(String kode_rute, String keberangkatan, String tujuan, String business, String premium) throws IOException {
        modelRute = new ModelRute(kode_rute,keberangkatan,tujuan,business,premium);
        modelRute.addRute();

    }

    //delete berdasarkan kode train, lempar kode ke JSON data,baru dijson data dicari kodenya itu, dihapus
    public void deleteRute(String kode) throws IOException {
        modelRute = new ModelRute();
        modelRute.deleteRute(kode);

    }

    //ambil smua data kota dari model train, return ke view
    public JSONArray getDataRute() throws IOException {
        modelRute = new ModelRute();
        return modelRute.getDataRute();
    }

    //edit train ambil input dari user lempar lagi ke JSON
    public void editRute(String kodelama, String kodeBaru, String keberangkatan,String tujuan,String business,String premium) throws IOException {
        modelRute = new ModelRute();
        modelRute.editRute(kodelama, kodeBaru, keberangkatan, tujuan, business, premium);

    }
    //===================================END komunikasi JSON via Model=================================
    
    
    //ini cek apakah kota ada gak didatabase json
    public boolean RuteExist(String kode) throws FileNotFoundException{
        modelRute = new ModelRute();
        return modelRute.RuteExist(kode);
    }
    
    //buat main sndiri utk ngecek
    public static void main(String[] args) throws IOException, ParseException {
        ControllerRute c = new ControllerRute();
        c.index();
    }
  




}
