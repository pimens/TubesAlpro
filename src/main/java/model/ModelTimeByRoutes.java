package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ModelTimeByRoutes extends ModelJSON {
	private JSONArray timeByRoutes;

	public ModelTimeByRoutes() throws FileNotFoundException {
		try {
			timeByRoutes = readJson("DataJson/timebyroutes.json");
		}catch(FileNotFoundException e){
			timeByRoutes = new JSONArray();
		}
	}
	public void pushToJSONFile() throws JSONException, IOException {
		this.writeToJson(timeByRoutes.toString(2),"DataJson/timebyroutes.json");
	}
	
	public boolean isExistByKodeWaktu(String input){
        JSONObject object = null;
        String kodeWaktuRute;
        
        for (int i = 0; i < timeByRoutes.length(); i++) {
            object = new JSONObject(timeByRoutes.get(i).toString());
            kodeWaktuRute = object.getString("kodeWaktu");
            if (input.equals(kodeWaktuRute)) {
            	return true;
            }
        }
		return false;
	}
	public boolean isExistByKodeWaktuRute(String input){
        JSONObject object = null;
        String kodeWaktuRute;
        
        for (int i = 0; i < timeByRoutes.length(); i++) {
            object = new JSONObject(timeByRoutes.get(i).toString());
            kodeWaktuRute = object.getString("kodeWaktuRute");
            if (input.equals(kodeWaktuRute)) {
            	return true;
            }
        }
		return false;
	}
	public void addData(HashMap<String, String> input){

        JSONObject object = null;
        String kodeRute;
        
		boolean isExist = false;
		Integer i;
		for (i = 0; i < timeByRoutes.length(); i++) {
            object = new JSONObject(timeByRoutes.get(i).toString());
            kodeRute = object.getString("kodeRute");
            if (input.get("kodeRute").equals(kodeRute)) {
            	input.put("id",object.getString("id"));
            	input.put("kodeWaktuRute",object.getString("kodeWaktuRute"));
            	timeByRoutes.put(i, input);
            	isExist = true;
            }
        }
		if(!isExist) {
        	input.put("id",i.toString());
        	input.put("kodeWaktuRute","WR"+i.toString(2));
        	timeByRoutes.put(i, input);
		}
	}
	public void deleteData(String input) {
        JSONObject object = null;
        String kodeRute;
        
		Integer i;
		for (i = 0; i < timeByRoutes.length(); i++) {
            object = new JSONObject(timeByRoutes.get(i).toString());
            kodeRute = object.getString("kodeRute");
            if (input.equals(kodeRute)) {
            	timeByRoutes.remove(i);
            }
        }
	}
	public ArrayList<HashMap<String, String>> getDataByKodeRute(String input) {

        JSONObject object = null;
        String kodeRute;

    	HashMap<String,String> map = new HashMap<String,String>();
    	ArrayList<HashMap<String,String>> array = new ArrayList<HashMap<String,String>>();

		Integer i;
		for (i = 0; i < timeByRoutes.length(); i++) {
            object = new JSONObject(timeByRoutes.get(i).toString());
            kodeRute = object.getString("kodeRute");
            if (input.equals(kodeRute)) {
            	map.put("id",object.getString("id"));
            	map.put("kodeWaktuRute",object.getString("kodeWaktuRute"));
            	map.put("kodeRute",object.getString("kodeRute"));
            	map.put("kodeWaktu",object.getString("kodeWaktu"));
            	array.add(map);
            }
        }
		return array;
	}
}
