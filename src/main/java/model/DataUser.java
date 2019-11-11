/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

public class DataUser {

    File file = new File("C:\\Users\\pmen\\Documents\\NetBeansProjects\\TubesAlpro\\DataJson\\d.json");
    BufferedReader br;
    static int indexUser = 0;

    boolean cek(String email, String pass) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray user = new JSONArray(tokener);
        int cek = 0;
        for (int i = 0; i < user.length(); i++) {
            JSONObject object = new JSONObject(user.get(i).toString());
            if (object.getString("email").equals(email) && object.getString("password").equals(pass)) {
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

    public void writeToJson(String data, String path)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(data);
        writer.close();
    }

    public void addUser(String ktp, String nama,String handphone,String email,String pass) throws FileNotFoundException, IOException {
        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray curentUser = new JSONArray(tokener);
        JSONObject object = new JSONObject();
        object.put("id", "8");
        object.put("KTP", ktp);
        object.put("nama", nama);
        object.put("email", email);
        object.put("password", pass);
        object.put("rule", "0");
        String jsonString = object.toString();
        curentUser.put(object);
        System.out.println(curentUser.toString(2));
        this.writeToJson(curentUser.toString(2), "C:\\Users\\pmen\\Documents\\NetBeansProjects\\TubesAlpro\\DataJson\\d.json");
    }
}
