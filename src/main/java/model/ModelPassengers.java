package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class ModelPassengers extends ModelJSON {

    private String nama;
    private String ktp;
    private String nomorHandphone;
    private String email;
    private String password;
    private String id;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorHandphone() {
        return nomorHandphone;
    }

    public void setNomorHandphone(String nomorHanphone) {
        this.nomorHandphone = nomorHanphone;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getKtp() {
        return ktp;
    }
    public void setKtp(String ktp) {
        this.ktp = ktp;
    }
    public void getDataByKTP(String ktp) throws FileNotFoundException {
        JSONArray user = readJson("C:\\Users\\pmen\\Documents\\NetBeansProjects\\TubesAlpro\\DataJson\\d.json");
        JSONObject object = null;
        int i = 0;
        for (i = 0; i < user.length(); i++) {
            object = new JSONObject(user.get(i).toString());
            if (ktp.equals(object.get("KTP"))) {
                break;
            }
        }
        setEmail(object.getString("email"));
        setNama(object.getString("namaLengkap"));
        setKtp(object.getString("KTP"));
        setPassword(object.getString("password"));
        setId(object.getString("id"));
        setNomorHandphone(object.getString("handphone"));
    }
    public void pushNewDataUser() throws FileNotFoundException, IOException{
        JSONArray user = readJson("C:\\Users\\pmen\\Documents\\NetBeansProjects\\TubesAlpro\\DataJson\\d.json");
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
        object.put("handphone", nomorHandphone);
        object.put("email", email);
        object.put("password", password);
        object.put("rule", "0");
        object.put("id", id);
        newUser.put(object);        
        this.writeToJson(newUser.toString(2),
                "C:\\Users\\pmen\\Documents\\NetBeansProjects\\TubesAlpro\\DataJson\\d.json");
    }

}
