package model;

import java.io.FileNotFoundException;

import org.json.JSONArray;
import org.json.JSONObject;

public class ModelTimes  extends ModelJSON {
	private JSONArray times;

	public ModelTimes() throws FileNotFoundException {
		try {
			times = readJson("DataJson/times.json");
		}catch(FileNotFoundException e){
			times = new JSONArray();
		}
	}
	public boolean isExistByKodeWaktu(String input){
        JSONObject object = null;
        String kodeWaktu;
        
        for (int i = 0; i < times.length(); i++) {
            object = new JSONObject(times.get(i).toString());
            kodeWaktu = object.getString("kodeWaktu");
            if (input.equals(kodeWaktu)) {
            	return true;
            }
        }
		return false;
	}
}
