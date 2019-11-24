package com.mycompany.tubes;

import controller.ControllerStationsByRoutes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class MenuStationByRouteDelete {
    ControllerStationsByRoutes c;
    public MenuStationByRouteDelete() {
        c = new ControllerStationsByRoutes();
    }
    public void index() throws FileNotFoundException, IOException, ParseException {
        Scanner cin = new Scanner(System.in);
        JSONObject r = null;
        JSONArray rute = null;
        int total = 0,pil,del=0;
        String kode = "";
        System.out.println("#DELETE STASIUN BERDASARKAN RUTE#");
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
                System.out.print(1 + " \t " + r.get("kodeJalur") + " \t "+kode+" \t ");
                System.out.println(c.getCityById(a.getString("src")) + "-" + c.getCityById(a.getString("dst"))+" \t\t "+total);
            } else {
                System.out.println("----\t----------\t---------\t "+c.getCityById(a.getString("src"))+"-"+c.getCityById(a.getString("dst")));
            }
        }
        System.out.print("Delete Last Station (0/1):");
        del = cin.nextInt();
        if(del==1){
            c.deleteStation(kode);
            c.indexDelete();
        }
        c.index("#MENU - Delete Stasiun#");
    }
}
