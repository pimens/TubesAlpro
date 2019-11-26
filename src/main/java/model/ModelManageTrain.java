package model;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ModelManageTrain extends ModelJSON {

    private String kodeKAI;
    private String nameStation;
    private String gerbong;
    private String business;
    private String premium;

    public String getKodeKAI() {
        return kodeKAI;
    }

    public void setKodeKAI(String kodeKAI) {
        this.kodeKAI = kodeKAI;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    public String getGerbong() {
        return gerbong;
    }

    public void setGerbong(String gerbong) {
        this.gerbong = gerbong;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public int getMaxId() throws FileNotFoundException {

        JSONArray currentTrain = readJson("DataJson/train.json");
        int max = 0;
        if (currentTrain.length() > 0) {
            for (int i = 0; i < currentTrain.length(); i++) {
                JSONObject object = new JSONObject(currentTrain.get(i).toString());
                if (Integer.valueOf(object.getString("id")) > max) {
                    max = Integer.valueOf(object.getString("id"));
                }
            }
        }

        return max;
    }

    public void addTrain() throws FileNotFoundException, IOException {
        JSONArray currentTrain = readJson("DataJson/train.json");
        JSONObject trainDetails = new JSONObject();
        trainDetails.put("kodeKAI", kodeKAI);
        trainDetails.put("nameStation", nameStation);
        trainDetails.put("gerbong", gerbong);
        trainDetails.put("business", business);
        trainDetails.put("premium", premium);
        System.out.println("idmu " + this.getMaxId());
        trainDetails.put("id", String.valueOf(this.getMaxId() + 1));
        currentTrain.put(trainDetails);
        this.writeToJson(currentTrain.toString(2), "DataJson/train" + ".json");
    }

    public JSONArray getDataTrain() throws FileNotFoundException, IOException {
        JSONArray train = readJson("DataJson/train.json");
        return train;
    }

    public void editTrain(String kodelama, String kodeBaru, String station, String gerbong, String business, String premium) throws JSONException, IOException {
        JSONArray currentTrain = readJson("DataJson/train.json");
        int cek = 0;
        for (int i = 0; i < currentTrain.length(); i++) {
            JSONObject object = new JSONObject(currentTrain.get(i).toString());
            if (object.getString("kodeKAI").equals(kodelama)) {

                object.put("kodeKAI", kodeBaru);
                object.put("nameStation", station);
                object.put("gerbong", gerbong);
                object.put("business", business);
                object.put("premium", premium);
                currentTrain.put(i, object);

            }
        }
        this.writeToJson(currentTrain.toString(2), "DataJson/train" + ".json");
    }

    public void deleteTrain(String kodelama) throws JSONException, IOException {
        JSONArray currentTrain = readJson("DataJson/train.json");
        int cek = 0;
        for (int i = 0; i < currentTrain.length(); i++) {
            JSONObject object = new JSONObject(currentTrain.get(i).toString());
            if (object.getString("kodeKAI").equals(kodelama)) {

                currentTrain.remove(i);

            }
        }

        this.writeToJson(currentTrain.toString(2), "DataJson/train" + ".json");

    }

}