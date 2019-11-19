package model;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ModelTrainsByRoute {
    private ArrayList<String> trainCodes;
    private ArrayList<String> routes;

    public ModelTrainsByRoute() {
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

    public void read () {
        InputStream is = ModelTrainsByRoute.class.getResourceAsStream("../DataJson/trainbyroute.json");
        if (is == null) {
            throw new NullPointerException("Cannot find resource file ");
        }

        JSONTokener tokener = new JSONTokener(is);
        JSONArray arr = new JSONArray(tokener);

        for (int i=0; i<arr.length(); i++) {
            trainCodes.add(arr.getJSONObject(i).getString("kodeKereta"));
            routes.add(arr.getJSONObject(i).getString("kodeRute"));
        }
    }

    public void write () throws FileNotFoundException {
        JSONArray arr = new JSONArray();
        for (int i=0; i<routes.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("kodeKereta", trainCodes.get(i));
            object.put("kodeRute", routes.get(i));
            arr.put(object);
        }

        try (PrintWriter out = new PrintWriter("src/main/java/DataJson/trainbyroute.json")) {
            out.println(arr);
            out.close();
        }
    }
    /*public static void main (String[] args) {
        //InputStream is = classloader.getResourceAsStream("test.csv");
        InputStream is = ModelTrainsByRoute.class.getResourceAsStream("../DataJson/trainbyroute.json");
        if (is == null) {
            throw new NullPointerException("Cannot find resource file ");
        }

        JSONTokener tokener = new JSONTokener(is);
        JSONArray arr = new JSONArray(tokener);
        for (int i=0; i<arr.length(); i++) {
            System.out.println(arr.getJSONObject(i).getString("kodeRute"));
        }
    }*/
}