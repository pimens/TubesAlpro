package model;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class ModelTimes extends ModelJSON {

    private JSONArray times;

    public ModelTimes() throws FileNotFoundException {
        try {
            times = readJson("DataJson/times.json");
        } catch (FileNotFoundException e) {
            times = new JSONArray();
        }
    }

    public boolean isExistByKodeWaktu(String input) {
        JSONObject object = null;
        String kodeWaktu;

        for (int i = 0; i < getTimes().length(); i++) {
            object = new JSONObject(getTimes().get(i).toString());
            kodeWaktu = object.getString("kodeWaktu");
            if (input.equals(kodeWaktu)) {
                return true;
            }
        }
        return false;
    }
    public void generateWaktu() throws IOException{
         String[] quarterHours = { "00", "15", "30", "45" };
      
        JSONArray newUser = new JSONArray();      
    
        // System.out.println(newUser.toString(2));
           
        int in=1;
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 4; j++) {
                String time = i + ":" + quarterHours[j];
                if (i < 10) {
                    time = "0" + time;
                }    
                JSONObject object = new JSONObject();            
                object.put("kodeWaktu", "TM"+in);
                object.put("waktu", time);
                object.put("id", String.valueOf(in));
                newUser.put(object);
                // System.out.println(time);
                in=in+1;
            }
        }
        // System.out.println(newUser.toString(2));
        writeToJson(newUser.toString(2),
        "DataJson/times.json");   

    }

    /**
     * @return the times
     */
    public JSONArray getTimes() {
        return times;
    }

}
