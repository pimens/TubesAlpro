/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mycompany.tubes.MenuHistory;
import java.io.FileNotFoundException;
import model.ModelHistory;
import model.ModelSchedules;
import org.json.JSONArray;

/**
 *
 * @author pmen
 */
public class ControllerHistory {
    ModelHistory model;
    public ControllerHistory() {
        model=new ModelHistory();
    }
    
    public void index() throws FileNotFoundException{
        MenuHistory m = new MenuHistory();
        m.index();        
    }
    public JSONArray getDataBook(String ktp) throws FileNotFoundException{
        JSONArray book = new JSONArray();
        book = model.getDataBooking(ktp);
        return book;
    }
    public String getDataRute(String kode) throws FileNotFoundException{
        ModelSchedules m = new ModelSchedules();
        return m.getRuteByKodeJadwal(kode);        
    }
    public static void main(String [] args) throws FileNotFoundException{
        ControllerHistory c = new ControllerHistory();
        c.index();
    }
    
}
