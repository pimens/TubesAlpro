package controller;

import com.mycompany.tubes.MenuStationByRouteDelete;
import com.mycompany.tubes.MenuStationViewByRoute;
import com.mycompany.tubes.MenuStationByRouteAdd;
import com.mycompany.tubes.MenuStationByRouteMain;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.ModelStationsByRoutes;
import org.json.JSONArray;
import org.json.JSONObject;
// mvn compile exec:java -Dexec.mainClass=controller.Main

public class ControllerStationsByRoutes {
    ModelStationsByRoutes m;
    public ControllerStationsByRoutes() {
        m=new ModelStationsByRoutes();
    }
    
   //----------------------------------main menu
    public void index(String msg) throws IOException {
        MenuStationByRouteMain m = new MenuStationByRouteMain();
        m.index(msg);
    }

    //--------------------addd Menu Station ByRoute-------------------------
    public void indexAdd() throws FileNotFoundException, IOException {
        MenuStationByRouteAdd m = new MenuStationByRouteAdd();
        m.index();
    }

    //cek routes in json routes
    public boolean cekRoute(String kode) throws FileNotFoundException {        
        return m.cekRoute(kode);
    }

    //cek city in json cities
    public boolean cekCity(String city) throws FileNotFoundException {       
        return m.cekCity(city);
    }

    //cek city in json StationsByRoute
    public boolean cekStation(String city, String kodeRute) throws FileNotFoundException {       
        String idKota = m.getIdByCity(city);
        String idRute = m.getIdByKodeRute(kodeRute);
        return m.cekStation(idKota, idRute);
    }

    //get id kota dari nama
    public String getIdByCity(String city) throws FileNotFoundException {       
        String id = m.getIdByCity(city);
        return id;
    }

    //get nama dari kota by id
    public String getNameCityById(String id) throws FileNotFoundException {       
        String nama = m.getCityByName(id);
        return nama;
    }

    public JSONArray getStationsByRoutes(String kode) throws FileNotFoundException {       
        String id = m.getIdByKodeRute(kode);
        JSONArray stations = m.getStationsByRoutes(id);
        return stations;
    }

    public void addRoute(String kodeRute, String src, String dst, String time, String id) throws FileNotFoundException, IOException {
        JSONArray routes = getStationsByRoutes(kodeRute);
        JSONObject route = new JSONObject();
        JSONObject outerObject = new JSONObject();
        //make array routes update with new data
        route.put("id", id);
        route.put("src", src);
        route.put("dst", dst);
        route.put("time", time);
        routes.put(route);
        //add data 
        outerObject.put("id", m.getIdByKodeRute(kodeRute));
        outerObject.put("routes", routes);
        outerObject.put("kodeJalur", m.getCodeofRoute(m.getIdByKodeRute(kodeRute)));
        m.addData(outerObject, m.getIdByKodeRute(kodeRute));
    }

    public void initialStation(String kodeRute) throws IOException {
        String id = m.getIdByKodeRute(kodeRute);
        JSONArray o = new JSONArray();
        JSONObject route = new JSONObject();
        route.put("id", id);
        route.put("kodeJalur", kodeRute + String.valueOf(1));
        route.put("routes", o);
        m.addData(route, kodeRute);
    }

    //-----------------------------------------menu lihat jalur 
    public void MenuSeeStation() throws IOException {
        MenuStationViewByRoute m = new MenuStationViewByRoute();
        m.index();
    }

    public JSONObject getDataByRoute(String kode) throws FileNotFoundException {
        String id = m.getIdByKodeRute(kode);
        return m.getDataRoutes(id);
    }

    public String getCityById(String idkota) throws FileNotFoundException {
        String kode = m.getCityById(idkota);
        return kode;
    }
    //-----------------------------------------menu lihat jalur 
    
    //---------------------------------------menu delete
    public void indexDelete() throws IOException {
        MenuStationByRouteDelete m = new MenuStationByRouteDelete();
        m.index();
    }

    public void deleteStation(String kodeRute) throws IOException {
        String id = m.getIdByKodeRute(kodeRute);
        m.deleteStation(id);
    }
}
