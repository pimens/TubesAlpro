/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mycompany.tubes.MenuRoutes;
import com.mycompany.tubes.MenuRoutesAdd;
import com.mycompany.tubes.MenuRoutesDelete;
import com.mycompany.tubes.MenuRoutesEdit;
import com.mycompany.tubes.MenuRoutesShow;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.ModelRoutes;
import model.ModelStationsByRoutes;
import org.json.JSONArray;

/**
 *
 * @author pmen
 */
public class ControllerRoutes {

    ModelRoutes model;

    public ControllerRoutes() throws FileNotFoundException {
        model = new ModelRoutes();
    }

    public void index() throws FileNotFoundException, FileNotFoundException, IOException {
        MenuRoutes m = new MenuRoutes();
        m.index();
    }

    public void menuAdd() throws FileNotFoundException, IOException {
        MenuRoutesAdd m = new MenuRoutesAdd();
        m.index();
    }

    public void menuEdit() throws FileNotFoundException, IOException {
        MenuRoutesEdit m = new MenuRoutesEdit();
        m.index();
    }

    public void menuDelete() throws FileNotFoundException, IOException {
        MenuRoutesDelete m = new MenuRoutesDelete();
        m.index();
    }

    public void menuShow() throws FileNotFoundException, IOException {
        MenuRoutesShow m = new MenuRoutesShow();
        m.index();
    }

    public void print() throws FileNotFoundException {
        MenuRoutesShow m = new MenuRoutesShow();
        m.print();
    }

    public JSONArray getAllRoutes() {
        return model.getRoutes();
    }

    public boolean cityIsExist(String city) throws FileNotFoundException {
        ModelStationsByRoutes m = new ModelStationsByRoutes();
        return !"".equals(m.getIdByCity(city));
    }

    public String getIdByCity(String city) throws FileNotFoundException {
        ModelStationsByRoutes m = new ModelStationsByRoutes();
        return m.getIdByCity(city);
    }

    public boolean isExistBySrcDsc(String src, String dst) {
        return model.isExistBySrcDst(src, dst);
    }

    public void addRoute(String src, String dst, String b, String p) throws FileNotFoundException, IOException {
        model.add(src, dst, b, p);
    }

    public String getNama(String id) throws FileNotFoundException {
        ModelStationsByRoutes m = new ModelStationsByRoutes();
        return m.getCityByName(id);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ControllerRoutes c = new ControllerRoutes();
        c.index();
    }

    public void deleteRoute(String kode) throws IOException {
        model.deleteRoute(kode);
    }

    public void editRoute(String src, String dst, String b, String p,String kode) throws FileNotFoundException, IOException {
        model.edit(src, dst, b, p,kode);
    }
}
