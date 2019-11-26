package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ModelTrainsByRoute extends ModelJSON {
    private ArrayList<String> trainCodes;
    private ArrayList<String> routes;

    public ModelTrainsByRoute() throws FileNotFoundException, IOException {
        trainCodes = new ArrayList<>();
        routes = new ArrayList<>();

        read();
    }

    public ArrayList<String> getTrainCodes() {
        return trainCodes;
    }

    public ArrayList<String> getRoutes() {
        return routes;
    }

    public void addTrainCodes(String t) {
        trainCodes.add(t);
    }

    public void addRoutes(String t) {
        routes.add(t);
    }

    public boolean del(String train, String route) {
        for (int i=0; i<trainCodes.size(); i++) {
            if ((trainCodes.get(i).equals(train)) && (routes.get(i).equals(route))) {
                trainCodes.remove(i);
                routes.remove(i);
                return true;
            }
        }

        return false;
    }

    public ArrayList<String> getCurrent(String rute) {
        ArrayList<String> temp = new ArrayList<>();

        for (int i=0; i<routes.size(); i++) {
            if (routes.get(i).equals(rute)) {
                temp.add(trainCodes.get(i));
            }
        }

        return (temp);
    }

    public void read() throws FileNotFoundException, IOException {
        JSONArray arr = readJson("DataJson/trainbyroute.json");
        JSONArray kereta = readJson("DataJson/train.json");
        JSONArray rute = readJson("DataJson/routes.json");

        for (int i=0; i<arr.length(); i++) {
            String tempKereta = "";
            String tempRute = "";
            for (int j=0; j<kereta.length(); j++) {
                if (arr.getJSONObject(i).getString("kodeKereta").equals(kereta.getJSONObject(j).getString("id"))) {
                    tempKereta = kereta.getJSONObject(j).getString("kodeKAI");
                    break;
                }
            }

            for (int j=0; j<rute.length(); j++) {
                if (arr.getJSONObject(i).getString("kodeRute").equals(rute.getJSONObject(j).getString("id"))) {
                    tempRute = rute.getJSONObject(j).getString("kodeRute");
                    break;
                }
            }
            routes.add(tempRute);
            trainCodes.add(tempKereta);
        }
    }
    public String getIdKereta(String kode) throws FileNotFoundException{
        String id="";
        JSONArray kereta = readJson("DataJson/train.json");
        for(int i=0;i<kereta.length();i++){
            if(kereta.getJSONObject(i).get("kodeKAI").equals(kode)){
                id=kereta.getJSONObject(i).getString("id");
            }
        }
        return id;
    }
    public String getIdRute(String kode) throws FileNotFoundException{
        String id="";
        JSONArray kereta = readJson("DataJson/routes.json");
        for(int i=0;i<kereta.length();i++){
            if(kereta.getJSONObject(i).get("kodeRute").equals(kode)){
                id=kereta.getJSONObject(i).getString("id");
            }
        }
        return id;
    }
    public void write() throws FileNotFoundException, IOException {
        JSONArray arr = new JSONArray();
        
        for (int i=0; i<trainCodes.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("kodeKereta", getIdKereta(trainCodes.get(i)));
            object.put("kodeRute", getIdRute(routes.get(i)));
            arr.put(object);
        }

        this.writeToJson(arr.toString(2), "DataJson/trainbyroute.json");
    }
}