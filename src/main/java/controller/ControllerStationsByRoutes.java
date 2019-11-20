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
        ModelStationsByRoutes m = new ModelStationsByRoutes();
        return m.cekRoute(kode);
    }

    //cek city in json cities
    public boolean cekCity(String city) throws FileNotFoundException {
        ModelStationsByRoutes m = new ModelStationsByRoutes();
        return m.cekCity(city);
    }

    //cek city in json StationsByRoute
    public boolean cekStation(String city, String kodeRute) throws FileNotFoundException {
        ModelStationsByRoutes m = new ModelStationsByRoutes();
        return m.cekStation(city, kodeRute);
    }

    public JSONArray getStationsByRoutes(String kode) throws FileNotFoundException {
        ModelStationsByRoutes m = new ModelStationsByRoutes();
        JSONArray stations = m.getStationsByRoutes(kode);
        return stations;
    }

    public void addRoute(String kodeRute, String src, String dst, String time, String id) throws FileNotFoundException, IOException {
        ModelStationsByRoutes m = new ModelStationsByRoutes();
        JSONArray routes = getStationsByRoutes(kodeRute);
        String kodeSrc = m.getCityByName(src);
        String kodeDst = m.getCityByName(dst);
        JSONObject route = new JSONObject();
        JSONObject outerObject = new JSONObject();
        //make array routes update with new data
        route.put("id", id);
        route.put("src", src);
        route.put("dst", dst);
        route.put("kodeSrc", kodeSrc);
        route.put("kodeDst", kodeDst);
        route.put("time", time);
        routes.put(route);
        //add data 
        outerObject.put("kodeRute", kodeRute);
        outerObject.put("routes", routes);
        outerObject.put("kodeJalur", m.getCodeofRoute(kodeRute));
        m.addData(outerObject, kodeRute);
    }

    public void initialStation(String kodeRute) throws IOException {
        ModelStationsByRoutes m = new ModelStationsByRoutes();
        JSONArray o = new JSONArray();
        JSONObject route = new JSONObject();
        route.put("kodeRute", kodeRute);
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
        ModelStationsByRoutes m = new ModelStationsByRoutes();
        return m.getDataRoutes(kode);
    }

    //---------------------------------------menu delete
    public void indexDelete() throws IOException {
        MenuStationByRouteDelete m = new MenuStationByRouteDelete();
        m.index();
    }

    public void deleteStation(String kodeRute) throws IOException {
        ModelStationsByRoutes m = new ModelStationsByRoutes();
        m.deleteStation(kodeRute);
    }
}
