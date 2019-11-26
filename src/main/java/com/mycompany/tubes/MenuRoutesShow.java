/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import controller.ControllerRoutes;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author pmen
 */
public class MenuRoutesShow {

    ControllerRoutes c;

    public MenuRoutesShow() throws FileNotFoundException {
        c = new ControllerRoutes();
    }

    public void index() throws FileNotFoundException, IOException {
        System.out.println("#LIHAT DATA RUTE#");
        System.out.println("Lihat Data Lengkap Rute");
        print();
        c.index();

    }

    public void print() throws FileNotFoundException {
        Table st = new Table();
        st.setShowVerticalLines(true);
        st.setHeaders("No.", "Keberangkatan", "Tujuan", "Kode_Rute", "Bisnis", "Premium");//optional - if not used then there will be no header and horizontal lines    
        JSONArray routes = c.getAllRoutes();
        for (int i = 0; i < routes.length(); i++) {
            JSONObject object = new JSONObject(routes.get(i).toString());
            st.addRow(String.valueOf(i + 1), c.getNama(object.getString("src")), c.getNama(object.getString("dst")), object.getString("kodeRute"), object.getString("bisnis"), object.getString("premium"));
        }
        st.print();
    }

}
