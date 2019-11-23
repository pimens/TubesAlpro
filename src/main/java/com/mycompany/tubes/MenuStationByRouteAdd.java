package com.mycompany.tubes;

import controller.ControllerStationsByRoutes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class MenuStationByRouteAdd {
    ControllerStationsByRoutes c;
    public MenuStationByRouteAdd() {
        c = new ControllerStationsByRoutes();
    }
    public void index() throws FileNotFoundException, IOException {
        Scanner cin = new Scanner(System.in);
        JSONArray r = null;
        String lastStation = "";
        int i, finish = 0, cek1 = 0, cek2 = 0, pil = 0;
        String kode = "", jalur = "", jalur2 = "", waktu = "";
        System.out.println("#Kelola Stasiun Berdasarkan Rute#");
        do {
            System.out.print("Kode Rute : ");
            kode = cin.nextLine();
            if (!c.cekRoute(kode)) {
                System.out.println("Tidak Ada Rute Tersebut");
            }
        } while (!c.cekRoute(kode));
        System.out.println("");
        System.out.println("Stasiun Awal Sampai Stasiun Akhir");
        System.out.println("------------------------------------------------------------------");
        r = c.getStationsByRoutes(kode);
        if (r == null) {
            c.initialStation(kode);
            r = c.getStationsByRoutes(kode);
        }
        for (i = 0; i < r.length(); i++) {
            JSONObject s = new JSONObject(r.get(i).toString());
            System.out.println("Jalur " + (i + 1) + " : " + c.getNameCityById(s.getString("src")) + " " + c.getNameCityById(s.getString("dst")) + " " + s.getString("time"));
            lastStation = s.getString("dst");
        }
        do {
            System.out.print("Jalur " + (i + 1) + " : ");
            jalur = cin.next();
            if (jalur.equals("99")) {
                finish = 1;
                cek1 = 1;
            }
            if (finish != 1) {
                jalur2 = cin.next();
                waktu = cin.next();
                //validate station & time in json
                if (!c.cekCity(jalur)) {
                    System.out.println("Tidak Ada Kota " + jalur);
                }
                if (!c.cekCity(jalur2)) {
                    System.out.println("Tidak Ada Kota " + jalur2);
                }
                if (!waktu.matches("^[0-9]*$")) {
                    System.out.println("Waktu Harus Angka 0-9");
                }
                //validate station in routes
                if ((c.cekCity(jalur) && c.cekCity(jalur2) && waktu.matches("^[0-9]*$"))) {
                    if (c.cekStation(jalur, kode) || c.cekStation(jalur2, kode)) {
                        System.out.println("Jalur sudah dilewati");
                    } else if (!jalur.equals(c.getNameCityById(lastStation)) && i != 0) {//src in next route must be last station
                        System.out.println("Jalur harus " + c.getNameCityById(lastStation));
                    } else {
                        lastStation = c.getIdByCity(jalur2);
                        cek1 = 1;
                        i++;
                        c.addRoute(kode, c.getIdByCity(jalur), c.getIdByCity(jalur2), String.valueOf(waktu), String.valueOf(i));
                    }
                }
            }
        } while (cek1 == 0 || finish == 0);
        c.index("Jalur Stasiun Yang Dilewati berdasarkan Rute Berhasil Ditambahkan");

    }
}
