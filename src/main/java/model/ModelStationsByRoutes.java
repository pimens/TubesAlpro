package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class ModelStationsByRoutes extends ModelJSON {

    //cek route exist
    public boolean cekRoute(String kode) throws FileNotFoundException {
        JSONArray routes = readJson("DataJson/routes.json");
        boolean cek = false;
        for (int i = 0; i < routes.length(); i++) {
            JSONObject a = new JSONObject(routes.get(i).toString());
            if (kode.equals(a.get("kodeRute"))) {
                cek = true;
                break;
            }
        }
        return cek;
    }

    //cek city exist
    public boolean cekCity(String kota) throws FileNotFoundException {
        JSONArray cities = readJson("DataJson/cities.json");
        boolean cek = false;
        for (int i = 0; i < cities.length(); i++) {
            JSONObject a = new JSONObject(cities.get(i).toString());
            if (kota.equals(a.get("nama"))) {
                cek = true;
                break;
            }
        }
        return cek;
    }

    //cek station exist in routes
    public boolean cekStation(String station, String kodeRute) throws FileNotFoundException {
        JSONArray stations = getStationsByRoutes(kodeRute);
        boolean cek = false;
        //not check last data, cz last dest will be src in the next route
        for (int i = 0; i < stations.length() - 1; i++) {
            JSONObject a = new JSONObject(stations.get(i).toString());
            if (station.equals(a.get("src")) || station.equals(a.get("dst"))) {
                cek = true;
                break;
            }
        }
        return cek;
    }

    //get code city, add route need code city
    public String getCityByName(String kota) throws FileNotFoundException {
        JSONArray cities = readJson("DataJson/cities.json");
        String kode = "";
        for (int i = 0; i < cities.length(); i++) {
            JSONObject a = new JSONObject(cities.get(i).toString());
            if (kota.equals(a.get("nama"))) {
                kode = a.get("kodeKota").toString();
                break;
            }
        }
        return kode;
    }

    //getstation by route spesifik untuk rute 
    public JSONArray getStationsByRoutes(String kode) throws FileNotFoundException {
        JSONArray routes = readJson("DataJson/StationsByRoute.json");
        JSONArray spesifik = null;
        for (int i = 0; i < routes.length(); i++) {
            JSONObject a = new JSONObject(routes.get(i).toString());
            if (kode.equals(a.get("kodeRute"))) {
                spesifik = a.getJSONArray("routes");
                break;
            }
        }
        return spesifik;
    }

    //get all data Station by kode rute
    public JSONObject getDataRoutes(String kode) throws FileNotFoundException {
        JSONArray routes = readJson("DataJson/StationsByRoute.json");
        JSONObject spesifik = null;
        for (int i = 0; i < routes.length(); i++) {
            JSONObject a = new JSONObject(routes.get(i).toString());
            if (kode.equals(a.get("kodeRute"))) {
                spesifik = a;
                break;
            }
        }
        return spesifik;
    }

    public String getCodeofRoute(String kode) throws FileNotFoundException {
        JSONArray routes = readJson("DataJson/StationsByRoute.json");
        String kodeJalur = "";
        for (int i = 0; i < routes.length(); i++) {
            JSONObject a = new JSONObject(routes.get(i).toString());
            if (kode.equals(a.get("kodeRute"))) {
                kodeJalur = a.get("kodeJalur").toString();
                break;
            }
        }
        return kodeJalur;

    }

    public void addData(JSONObject r, String kodeRute) throws FileNotFoundException, IOException {
        JSONArray routes = readJson("DataJson/StationsByRoute.json");
        JSONArray newRoutes = new JSONArray();
        for (int i = 0; i < routes.length(); i++) {
            JSONObject a = new JSONObject(routes.get(i).toString());
            if (!kodeRute.equals(a.get("kodeRute"))) {
                newRoutes.put(a);
            }
        }        
        newRoutes.put(r);
        this.writeToJson(newRoutes.toString(2),
                "DataJson/StationsByRoute.json");
    }

    public void deleteStation(String kode) throws FileNotFoundException, IOException {
        //data lama
        JSONObject routes = getDataRoutes(kode);
        JSONArray route = routes.getJSONArray("routes");
        String kr = routes.getString("kodeRute");
        String kj = routes.getString("kodeJalur");
        //data baru
        JSONArray newRoute = new JSONArray();
        JSONObject newRoutes = new JSONObject();
        for (int i = 0; i < (route.length() - 1); i++) {
            newRoute.put(route.get(i));
        }
        newRoutes.put("kodeRute", kode);
        newRoutes.put("kodeJalur", kj);
        newRoutes.put("routes", newRoute);
        addData(newRoutes, kode);
    }

}
