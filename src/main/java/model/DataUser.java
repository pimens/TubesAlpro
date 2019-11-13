/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.User;
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

    public JSONObject doLogin(String email, String pass) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray user = new JSONArray(tokener);
        if (cek(email, pass)) {
            JSONObject object = new JSONObject(user.get(indexUser).toString());
            return object;
        } else {
            JSONObject object = new JSONObject();
            object.put("KTP", "gagal");
            return object;
        }
    }

    public void writeToJson(String data, String path)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(data);
        writer.close();
    }

    public void addUser(String ktp, String nama, String handphone, String email, String pass) throws FileNotFoundException, IOException {
        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray curentUser = new JSONArray(tokener);
        JSONObject object = new JSONObject();
        object.put("KTP", ktp);
        object.put("namaLengkap", nama);
        object.put("handphone", handphone);
        object.put("email", email);
        object.put("password", pass);
        object.put("rule", "0");
        String jsonString = object.toString();
        curentUser.put(object);
        System.out.println(curentUser.toString(2));
        this.writeToJson(curentUser.toString(2), "C:\\Users\\pmen\\Documents\\NetBeansProjects\\TubesAlpro\\DataJson\\d.json");
    }
    public void editUser(String ktp, String nama, String handphone, String email, String pass) throws FileNotFoundException, IOException {
        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray user = new JSONArray(tokener);
        JSONObject object = new JSONObject();
        //getDataWithoutCurrentUser
        JSONArray newUser = new JSONArray();
        for (int i = 0; i < user.length(); i++) {
            JSONObject object1 = new JSONObject(user.get(i).toString());
            if (!object1.getString("KTP").equals(ktp)) {
                newUser.put(user.get(i));
            }
        }  
        //create new json object for store new data user        
        object.put("KTP", ktp);
        object.put("namaLengkap", nama);
        object.put("handphone", handphone);
        object.put("email", email);
        object.put("password", pass);
        object.put("rule", "0");
        newUser.put(object);
        User.session = object;
//        System.out.println(newUser.toString(2));
        this.writeToJson(newUser.toString(2), "C:\\Users\\pmen\\Documents\\NetBeansProjects\\TubesAlpro\\DataJson\\d.json");
    }
}
