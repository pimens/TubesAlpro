package model;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class ModelRoutes extends ModelJSON {
	private JSONArray routes;

	public ModelRoutes() throws FileNotFoundException {
		try {
			routes = readJson("DataJson/routes.json");
		}catch(FileNotFoundException e){
			routes = new JSONArray();
		}
	}
	public boolean isExistByKodeRute(String input){
        JSONObject object = null;
        String kodeRute;
        
        for (int i = 0; i < routes.length(); i++) {
            object = new JSONObject(routes.get(i).toString());
            kodeRute = object.getString("kodeRute");
            if (input.equals(kodeRute)) {
            	return true;
            }
        }
		return false;
	}
}
