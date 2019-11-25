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
        c.print(kode);
        System.out.print("Delete Last Station (0/1):");
        del = cin.nextInt();
        if(del==1){
            c.deleteStation(kode);
            c.indexDelete();
        }
        c.index("#MENU - Delete Stasiun#");
    }
}
