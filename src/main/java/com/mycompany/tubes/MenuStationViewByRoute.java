package com.mycompany.tubes;

import controller.ControllerStationsByRoutes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class MenuStationViewByRoute {

    ControllerStationsByRoutes c;

    public MenuStationViewByRoute() {
        c = new ControllerStationsByRoutes();
    }

    public void index() throws FileNotFoundException, IOException, ParseException {
        Scanner cin = new Scanner(System.in);
        JSONObject r = null;
        int total = 0, pil = 0;
        String kode = "";
        System.out.println("#LIHAT STASIUN BERDASARKAN RUTE#");
        do {
            System.out.print("Kode Rute : ");
            kode = cin.nextLine();
            if (!c.cekRoute(kode)) {
                System.out.println("Tidak Ada Rute Tersebut");
            }
        } while (!c.cekRoute(kode));
        System.out.println("Stasiun Awal Sampai Stasiun Akhir");
        System.out.println("-----------------------------------------------------------");
        c.print(kode);
        c.index("#- MENU - Lihat Statsiun#");
    }

    public void print(String kode) throws FileNotFoundException {
        JSONObject r = null;
        JSONArray rute = new JSONArray();
        Table st = new Table();
        int total=0;
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("No.", "KodeJalur", "KodeRute", "Jalur Yang Dilewati", "Waktu");//optional - if not used then there will be no header and horizontal lines    
        r = c.getDataByRoute(kode);
        if (r.length() != 0) {
            rute = r.getJSONArray("routes");
        }
        for (int i = 0; i < rute.length(); i++) {
            JSONObject a = rute.getJSONObject(i);
            total = total + Integer.valueOf(a.getString("time"));
        }
        for (int i = 0; i < rute.length(); i++) {
            JSONObject a = rute.getJSONObject(i);
            if (i == 0) {
                st.addRow("1", r.get("kodeJalur").toString(), kode, c.getCityById(a.getString("src")) + "-" + c.getCityById(a.getString("dst")), String.valueOf(total));
            } else {
                st.addRow("", "", "", c.getCityById(a.getString("src")) + "-" + c.getCityById(a.getString("dst")), "");
            }
        }
        st.print();
    }
}
