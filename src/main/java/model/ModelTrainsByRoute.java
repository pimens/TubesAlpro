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

    public void read() throws FileNotFoundException, IOException {
        JSONArray arr = readJson("DataJson/trainbyroute.json");

        for (int i=0; i<arr.length(); i++) {
            trainCodes.add(arr.getJSONObject(i).getString("kodeKereta"));
            routes.add(arr.getJSONObject(i).getString("kodeRute"));
        }
    }

    public void write() throws FileNotFoundException, IOException {
        JSONArray arr = new JSONArray();
        for (int i=0; i<routes.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("kodeKereta", trainCodes.get(i));
            object.put("kodeRute", routes.get(i));
            arr.put(object);
        }

        this.writeToJson(arr.toString(2), "DataJson/trainbyroute.json");
    }
}