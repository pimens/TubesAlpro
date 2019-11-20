package com.mycompany.tubes;

import controller.ControllerAdmin;
import controller.ControllerStationsByRoutes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class MenuStationViewByRoute {

    public void index() throws FileNotFoundException, IOException {
        Scanner cin = new Scanner(System.in);
        ControllerStationsByRoutes c = new ControllerStationsByRoutes();
        JSONObject r = null;
        JSONArray rute = null;
        int total = 0,pil=0;
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
        System.out.println("No. \t KodeJalur \t KodeRute \t Jalur Yang Dilewati \t Waktu");
        r = c.getDataByRoute(kode);
        rute = r.getJSONArray("routes");
        for (int i = 0; i < rute.length(); i++) {
            JSONObject a = rute.getJSONObject(i);
            total = total + Integer.valueOf(a.getString("time"));
        }
        for (int i = 0; i < rute.length(); i++) {
            JSONObject a = rute.getJSONObject(i);            
            if (i == 0) {
                System.out.print(1 + " \t " + r.get("kodeJalur") + " \t "+r.getString("kodeRute")+" \t ");
                System.out.println(a.getString("kodeSrc") + "-" + a.get("kodeDst")+" \t\t "+total);
            } else {
                System.out.println("----\t----------\t---------\t "+a.getString("kodeSrc")+"-"+a.getString("kodeDst"));
            }
        }
        c.index("#- MENU - Lihat Statsiun#");

    }
}
