package controller;

import com.mycompany.tubes.MenuKota;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.ModelCities;
import org.json.JSONArray;

public class ControllerCities {

    MenuKota m;
    ModelCities modelCity;

    //===================================Akses menu via controller=============================
    public void index() throws IOException {
        m = new MenuKota();
        m.index();
    }

    public void showCity() throws IOException {
        m = new MenuKota();
        m.showCity();
    }

    public void menuAddCity() throws IOException {
        m = new MenuKota();
        m.menuAddCity();
    }

    public void editCity() throws IOException {
        m = new MenuKota();
        m.editCityMenu();
    }

    public void deleteCity() throws IOException {
        m = new MenuKota();
        m.deleteCityMenu();
    }
    //===================================END Akses menu via controller=============================

    //===================================komunikasi JSON via Model=================================
    //lempar input user dari view ke model city baru di model inputan ambil
    public void addCity(String kode, String nama) throws IOException {
        modelCity = new ModelCities(kode, nama);
        modelCity.addCity();

    }

    //delete berdasarkan kode city, lempar kode ke JSON data,baru dijson data dicari kodenya itu, dihapus
    public void deleteCity(String kode) throws IOException {
        modelCity = new ModelCities();
        modelCity.deleteCity(kode);

    }

    //ambil smua data kota dari model city, return ke view
    public JSONArray getDataKota() throws IOException {
        modelCity = new ModelCities();
        return modelCity.getDataKota();
    }
    
    public String getKodeCity(String kota) throws IOException {
        modelCity = new ModelCities();
        return modelCity.getKodeCity(kota);
    }

    //edit city ambil input dari user lempar lagi ke JSON
    public void editCity(String kodeLama,String kodeBaru,String kotaBaru) throws IOException {
        modelCity = new ModelCities();
        modelCity.editCity(kodeLama,kodeBaru, kotaBaru);

    }
    //===================================END komunikasi JSON via Model=================================
    
    
    //ini cek apakah kota ada gak didatabase json
    public boolean cityExist(String kode) throws FileNotFoundException{
        modelCity = new ModelCities();
        return modelCity.cityExist(kode);
    }
    
    //buat main sndiri utk ngecek
    public static void main(String[] args) throws IOException {
        ControllerCities c = new ControllerCities();
        c.index();
    }
}
