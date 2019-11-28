package model;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class ModelRoutes extends ModelJSON {

    private JSONArray routes;

    public ModelRoutes() throws FileNotFoundException {
        try {
            routes = readJson("DataJson/routes.json");
        } catch (FileNotFoundException e) {
            routes = new JSONArray();
        }
    }

    public boolean isExistByKodeRute(String input) {
        JSONObject object = null;
        String kodeRute;

        for (int i = 0; i < getRoutes().length(); i++) {
            object = new JSONObject(getRoutes().get(i).toString());
            kodeRute = object.getString("kodeRute");
            if (input.equals(kodeRute)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistBySrcDst(String src, String dst) {
        JSONObject object = null;

        for (int i = 0; i < getRoutes().length(); i++) {
            object = new JSONObject(getRoutes().get(i).toString());
            if (src.equals(object.get("src")) && dst.equals(object.get("dst"))) {
                return true;
            }
        }
        return false;
    }

    public int getMaxId() throws FileNotFoundException {

        int max = 0;
        for (int i = 0; i < routes.length(); i++) {
            JSONObject object = new JSONObject(routes.get(i).toString());
            if (Integer.valueOf(object.getString("id")) > max) {
                max = Integer.valueOf(object.getString("id"));
            }
        }
        return max;
    }

    public void add(String src, String dst, String b, String p) throws FileNotFoundException, IOException {
        int id = getMaxId() + 1;
        ModelStationsByRoutes m = new ModelStationsByRoutes();
        String kodeS = m.getCityById(src);
        String kodeD = m.getCityById(dst);
        JSONObject o = new JSONObject();
        o.put("id", String.valueOf(id));
        o.put("src", src);
        o.put("dst", dst);
        o.put("bisnis", b);
        o.put("kodeRute", kodeS + "-" + kodeD);
        o.put("premium", p);
        routes.put(o);
        writeToJson(routes.toString(2), "DataJson/routes.json");

    }

    public String getIdByKodeRute(String input) {
        JSONObject object = null;

        for (int i = 0; i < getRoutes().length(); i++) {
            object = new JSONObject(getRoutes().get(i).toString());

            if (object.getString("kodeRute").equals(input)) {
                return object.getString("id");
            }
        }
        return "";
    }

    public String getKodeRuteById(String string) {
        JSONObject object = null;

        for (int i = 0; i < getRoutes().length(); i++) {
            object = new JSONObject(getRoutes().get(i).toString());
            if (object.getString("id").equals(string)) {
                return object.getString("kodeRute");
            }
        }
        return "";
    }

    public JSONArray getRoutes() {
        return routes;
    }

    public void setRoutes(JSONArray route) {
        routes = route;
    }

    public boolean deleteRoute(String kode) throws IOException {
        JSONArray r = new JSONArray();
        JSONArray sr = readJson("DataJson/StationsByRoute.json");
        boolean exist = false;
        for (int i = 0; i < sr.length(); i++) {
            if (sr.getJSONObject(i).getString("id").equals(getIdByKodeRute(kode))) {
                exist = true;
            }
        }
        if (!exist) {
            for (int i = 0; i < routes.length(); i++) {
                JSONObject object = new JSONObject(routes.get(i).toString());
                if (!object.getString("kodeRute").equals(kode)) {
                    r.put(object);
                }
            }
            this.setRoutes(r);
            writeToJson(routes.toString(2), "DataJson/routes.json");
        }
        return exist;

    }

    public void edit(String src, String dst, String b, String p, String kode) throws FileNotFoundException, IOException {
        JSONObject o;
        ModelStationsByRoutes m = new ModelStationsByRoutes();
        String kodeS = m.getCityById(src);
        String kodeD = m.getCityById(dst);
        for (int i = 0; i < getRoutes().length(); i++) {
            o = new JSONObject(getRoutes().get(i).toString());
            if (kode.equals(o.get("kodeRute"))) {
                o.put("src", src);
                o.put("dst", dst);
                o.put("bisnis", b);
                o.put("kodeRute", kodeS + "-" + kodeD);
                o.put("premium", p);
                routes.put(i, o);
            }
        }
        writeToJson(routes.toString(2), "DataJson/routes.json");
    }
}
