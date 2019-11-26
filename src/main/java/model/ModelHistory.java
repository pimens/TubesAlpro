/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;

public class ModelHistory extends ModelJSON {

    public JSONArray getDataBooking(String ktp) throws FileNotFoundException {
        JSONArray book = new JSONArray();
        JSONArray filter = new JSONArray();

        book = readJson("DataJson/booking.json");
        for (int i = 0; i < book.length(); i++) {
            JSONObject o = book.getJSONObject(i);
            if(ktp.equals(o.get("user"))){
               filter.put(o);
            }
        }
        return filter;
    }
}
