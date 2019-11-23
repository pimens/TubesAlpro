package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class ModelStationsByRoutes extends ModelJSON {

    //cek station exist in routes
    public boolean cekStation(String station, String idRute) throws FileNotFoundException {
        JSONArray stations = getStationsByRoutes(idRute);
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
    public String getCityByName(String id) throws FileNotFoundException {
        JSONArray cities = readJson("DataJson/cities.json");
        String nama = "";
        for (int i = 0; i < cities.length(); i++) {
            JSONObject a = new JSONObject(cities.get(i).toString());
            if (id.equals(a.get("id"))) {
                nama = a.get("nama").toString();
                break;
            }
        }
        return nama;
    }
    
    public String getIdByCity(String city) throws FileNotFoundException {
        JSONArray cities = readJson("DataJson/cities.json");
        String id = "";
        for (int i = 0; i < cities.length(); i++) {
            JSONObject a = new JSONObject(cities.get(i).toString());
            if (city.equals(a.get("nama"))) {
                id = a.get("id").toString();
                break;
            }
        }
        return id;
    }

    public String getCodeofRoute(String id) throws FileNotFoundException {
        JSONArray routes = readJson("DataJson/StationsByRoute.json");
        String kodeJalur = "";
        for (int i = 0; i < routes.length(); i++) {
            JSONObject a = new JSONObject(routes.get(i).toString());
            if (id.equals(a.get("id"))) {
                kodeJalur = a.get("kodeJalur").toString();
                break;
            }
        }
        return kodeJalur;

    }

    //getstation by route spesifik untuk rute 
    public JSONArray getStationsByRoutes(String id) throws FileNotFoundException {
        JSONArray routes = readJson("DataJson/StationsByRoute.json");
        JSONArray spesifik = null;
        for (int i = 0; i < routes.length(); i++) {
            JSONObject a = new JSONObject(routes.get(i).toString());
            if (id.equals(a.get("id"))) {
                spesifik = a.getJSONArray("routes");
                break;
            }
        }
        return spesifik;
    }

    public void addData(JSONObject r, String id) throws FileNotFoundException, IOException {
        JSONArray routes = readJson("DataJson/StationsByRoute.json");
        JSONArray newRoutes = new JSONArray();
        for (int i = 0; i < routes.length(); i++) {
            JSONObject a = new JSONObject(routes.get(i).toString());
            if (!id.equals(a.get("id"))) {
                newRoutes.put(a);
            }
        }
        newRoutes.put(r);
        this.writeToJson(newRoutes.toString(2),
                "DataJson/StationsByRoute.json");
    }

    public void deleteStation(String id) throws FileNotFoundException, IOException {
        //data lama
        JSONObject routes = getDataRoutes(id);
        JSONArray route = routes.getJSONArray("routes");
        String kj = routes.getString("kodeJalur");
        //data baru
        JSONArray newRoute = new JSONArray();
        JSONObject newRoutes = new JSONObject();
        for (int i = 0; i < (route.length() - 1); i++) {
            newRoute.put(route.get(i));
        }
        newRoutes.put("id", id);
        newRoutes.put("kodeJalur", kj);
        newRoutes.put("routes", newRoute);
        addData(newRoutes, id);
    }

    //=========================menu see station 
    //get all data Station by kode rute
    public JSONObject getDataRoutes(String id) throws FileNotFoundException {
        JSONArray routes = readJson("DataJson/StationsByRoute.json");
        JSONObject spesifik = new JSONObject();
        for (int i = 0; i < routes.length(); i++) {
            JSONObject a = new JSONObject(routes.get(i).toString());
            if (id.equals(a.get("id"))) {
                spesifik = a;
                break;
            }
        }
        return spesifik;
    }

    public String getCityById(String idKota) throws FileNotFoundException {
        String nama = "";
        JSONArray cities = readJson("DataJson/cities.json");
        for (int i = 0; i < cities.length(); i++) {
            JSONObject a = new JSONObject(cities.get(i).toString());
            if (idKota.equals(a.get("id"))) {
                nama = a.getString("kodeKota");
                break;
            }
        }
        return nama;
    }

    //=========================menu see station 
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

    //ambill id rute di rute json berdasarkan kode input user, cocokin ke stasiun byrute
    public String getIdByKodeRute(String kode) throws FileNotFoundException {
        String id = "";
        JSONArray routes = readJson("DataJson/routes.json");
        for (int i = 0; i < routes.length(); i++) {
            JSONObject a = new JSONObject(routes.get(i).toString());
            if (kode.equals(a.get("kodeRute"))) {
                id = a.getString("id");
                break;
            }
        }
        return id;
    }

}
