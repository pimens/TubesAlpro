/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import controller.ControllerHistory;
import controller.User;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author pmen
 */
public class MenuHistory {

    ControllerHistory c;
    Scanner cin;

    public MenuHistory() {
        c = new ControllerHistory();
        cin = new Scanner(System.in);
    }

    public void index() throws FileNotFoundException {
        //        JSONArray book = c.getDataBook("0831290031341234");
        JSONArray book = c.getDataBook(User.session.getString("KTP"));
        Table st = new Table();
        int pil=0;
        System.out.println("#Daftar History Pembelian Tiket#");
        st.setShowVerticalLines(true);
        st.setHeaders("No.", "Tanggal", "Rute", "Total", "Jumlah Book");
        for (int i = 0; i < book.length(); i++) {
            JSONObject object = new JSONObject(book.get(i).toString());
            String penumpang = String.valueOf(object.getJSONArray("penumpang").length());
            st.addRow(String.valueOf(i + 1), object.getString("tanggal"), c.getDataRute(object.getString("jadwal")), object.getString("harga"), penumpang);
        }
        st.print();        
    }
}
