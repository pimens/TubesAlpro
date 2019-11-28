package controller;

import com.mycompany.tubes.MenuTrain;
import model.ModelManageTrain;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import org.json.JSONArray;
import org.json.JSONException;

public class ControllerTrain {
    ModelManageTrain model;
    public ControllerTrain() {
        model = new ModelManageTrain();
    }

    public void index() throws IOException, FileNotFoundException, ParseException {
        MenuTrain m = new MenuTrain();
        m.index();
    }

    public void addTrainMenu() throws IOException, FileNotFoundException, ParseException {
        MenuTrain m = new MenuTrain();
        m.addTrainMenu();
    }

    public boolean addTrain(String kodeKAI, String gerbong, String premium, String business, String nameStation) throws IOException {
        model.setBusiness(business);
        model.setGerbong(gerbong);
        model.setKodeKAI(kodeKAI);
        model.setNameStation(nameStation);
        model.setPremium(premium);
        return model.addTrain();
    }

    public void showTrainMenu() throws IOException, FileNotFoundException, ParseException {
        MenuTrain m = new MenuTrain();
        m.showTrainMenu();
    }

    public JSONArray getDataTrain() throws IOException, FileNotFoundException, ParseException {
        return model.getDataTrain();
    }

    public void editTrainMenu() throws IOException, FileNotFoundException, ParseException {
        MenuTrain m = new MenuTrain();
        m.editTrainMenu();
    }

    public void editTrain(String string, String kodeKAI, String nameStation, String gerbong, String business, String premium) throws JSONException, IOException {
        model.editTrain(string, kodeKAI, nameStation, gerbong, business, premium);
    }

    public void deleteTrainMenu() throws IOException, FileNotFoundException, ParseException {
        MenuTrain m = new MenuTrain();
        m.deleteTrainMenu();
    }
    public boolean deleteTrain(String kodelama) throws JSONException, IOException{
        return model.deleteTrain(kodelama);
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        ControllerTrain c = new ControllerTrain();
        c.index();
    }
}
