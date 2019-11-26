package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import controller.ControllerCities;

public class ModelCities extends ModelJSON {

    File file = new File("DataJson/cities" + ".json");
    BufferedReader br;
    static int indexUser = 0;

    private String namaKota;
    private String kodeKota;

    public ModelCities(String namaKota, String kodeKota) {
        super();
        this.namaKota = namaKota;
        this.kodeKota = kodeKota;
    }

    public ModelCities() {

    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    public String getKodeKota() {
        return kodeKota;
    }

    public void setKodeKota(String kodeKota) {
        this.kodeKota = kodeKota;
    }

    public int getMaxId() throws FileNotFoundException {
        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray currentKota = new JSONArray(tokener);
        int max = 0;
        if (currentKota.length() > 0) {
            for (int i = 0; i < currentKota.length(); i++) {
                JSONObject object = new JSONObject(currentKota.get(i).toString());
                if (Integer.valueOf(object.getString("id")) > max) {
                    max = Integer.valueOf(object.getString("id"));
                }
            }
        }

        return max;
    }

    public void addCity() throws FileNotFoundException, IOException {
        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray currentKota = new JSONArray(tokener);
        JSONObject kotaDetails = new JSONObject();
        kotaDetails.put("kodeKota", getKodeKota());
        kotaDetails.put("nama", getNamaKota());
        kotaDetails.put("id", String.valueOf(this.getMaxId() + 1));
        currentKota.put(kotaDetails);
        this.writeToJson(currentKota.toString(2), "DataJson/cities" + ".json");
    }

    public JSONArray getDataKota() throws FileNotFoundException, IOException {
        JSONArray city = readJson("DataJson/cities.json");
        return city;

    }

    public void editCity(String kodelama, String kodeBaru, String kotaBaru) throws JSONException, IOException {

        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray currentkota = new JSONArray(tokener);
        int cek = 0;
        for (int i = 0; i < currentkota.length(); i++) {

            JSONObject object = new JSONObject(currentkota.get(i).toString());
            if (object.getString("kodeKota").equals(kodelama)) {

                object.put("kodeKota", kodeBaru);
                object.put("nama", kotaBaru);
                currentkota.put(i, object);

            }
        }

        this.writeToJson(currentkota.toString(2), "DataJson/cities" + ".json");

    }
    
    public String getKodeCity(String namakota) throws JSONException, IOException {

        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray currentkota = new JSONArray(tokener);
        String kodekota=" ";
        for (int i = 0; i < currentkota.length(); i++) {

            JSONObject object = new JSONObject(currentkota.get(i).toString());
            if (object.getString("nama").equals(namakota)) {

              kodekota=object.getString("kodeKota");

            }
        }

        return kodekota;

    }

    public void deleteCity(String kodelama) throws JSONException, IOException {
        JSONArray currentkota = readJson("DataJson/cities.json");
        int cek = 0;
        for (int i = 0; i < currentkota.length(); i++) {
            JSONObject object = new JSONObject(currentkota.get(i).toString());
            if (object.getString("kodeKota").equals(kodelama)) {

                currentkota.remove(i);

            }
        }
        this.writeToJson(currentkota.toString(2), "DataJson/cities" + ".json");
    }

    //cek city exist
    public boolean cityExist(String kode) throws FileNotFoundException {
        JSONArray city = readJson("DataJson/cities.json");
        boolean cek=false;
        for(int i=0;i<city.length();i++){
            JSONObject o = city.getJSONObject(i);
            if(kode.equals(o.get("kodeKota"))){                
                cek=true;               
            }
        }
        return cek;
    }
}
