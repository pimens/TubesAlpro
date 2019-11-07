package com.mycompany.tubes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;
import org.json.JSONException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class Data {

    File file = new File("C:\\Users\\pmen\\Documents\\NetBeansProjects\\Tubes\\d.json");
    BufferedReader br;
    static int indexUser = 0;

    boolean cek(String nama, String pass) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray user = new JSONArray(tokener);
        int cek = 0;
        for (int i = 0; i < user.length(); i++) {
            JSONObject object = new JSONObject(user.get(i).toString());
            if (object.getString("nama").equals(nama) && object.getString("pass").equals(nama)) {
                cek = 1;                
                indexUser = i;
            }
        }
        if (cek == 1) {
            return true;
        } else {
            return false;
        }
    }

    public JSONObject getDataById(String uname, String pass) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray user = new JSONArray(tokener);
        if (cek(uname, pass)) {
            JSONObject object = new JSONObject(user.get(indexUser).toString());
            return object;
        } else {
            JSONObject object = new JSONObject();
            object.put("id", "gagal");
            return object;
        }
    }
}
