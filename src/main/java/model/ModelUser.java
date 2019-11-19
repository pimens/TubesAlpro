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

public class ModelUser extends ModelJSON {

    private String ktp;
    private String nomorHp;
    private String nama;
    private String email;
    private String password;
    File file = new File("");
    BufferedReader br;
    static int indexUser = 0;

    public ModelUser(String ktp, String nomorHp, String nama, String email, String password) {
        this.ktp = ktp;
        this.nomorHp = nomorHp;
        this.nama = nama;
        this.email = email;
        this.password = password;
    }

    public ModelUser() {

    }

    public int getMaxId() throws FileNotFoundException {
        JSONArray user = readJson("DataJson/d.json");
        int max = 0;
        for (int i = 0; i < user.length(); i++) {
            JSONObject object = new JSONObject(user.get(i).toString());
            if (Integer.valueOf(object.getString("id")) > max) {
                max = Integer.valueOf(object.getString("id"));
            }
        }
        return max;
    }

    boolean cek(String email, String pass) throws FileNotFoundException {
        JSONArray user = readJson("DataJson/d.json");

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
        JSONArray user = readJson("DataJson/d.json");

        if (cek(email, pass)) {
            JSONObject object = new JSONObject(user.get(indexUser).toString());
            return object;
        } else {
            JSONObject object = new JSONObject();
            object.put("id", "gagal");
            return object;
        }
    }

    public void addUser() throws FileNotFoundException, IOException {
        JSONArray curentUser = readJson("DataJson/d.json");

        JSONObject object = new JSONObject();
        object.put("KTP", ktp);
        object.put("namaLengkap", nama);
        object.put("handphone", nomorHp);
        object.put("email", email);
        object.put("password", password);
        object.put("rule", "0");
        object.put("id", String.valueOf(getMaxId() + 1));
        String jsonString = object.toString();
        curentUser.put(object);
        // System.out.println(curentUser.toString(2));
        this.writeToJson(curentUser.toString(2),
                "DataJson/d.json");
    }

    public void editUser(String id, String ktp, String nama, String handphone, String email, String pass)
            throws FileNotFoundException, IOException {
        JSONArray user = readJson("DataJson/d.json");
        JSONObject object = new JSONObject();
        // getDataWithoutCurrentUser
        JSONArray newUser = new JSONArray();
        for (int i = 0; i < user.length(); i++) {
            JSONObject object1 = new JSONObject(user.get(i).toString());
            if (!object1.getString("KTP").equals(ktp)) {
                newUser.put(user.get(i));
            }
        }
        // create new json object for store new data user
        object.put("KTP", ktp);
        object.put("namaLengkap", nama);
        object.put("handphone", handphone);
        object.put("email", email);
        object.put("password", pass);
        object.put("rule", "0");
        object.put("id", id);
        newUser.put(object);
        User.session = object;
        // System.out.println(newUser.toString(2));
        this.writeToJson(newUser.toString(2),
                "DataJson/d.json");
    }
    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
