package com.mycompany.tubes;

import controller.ControllerAdmin;
import controller.ControllerTime;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class MenuTimes {

    public void index() throws FileNotFoundException, IOException {
        ControllerTime c = new ControllerTime();
        int pil;
        Scanner cin = new Scanner(System.in);
        System.out.println("1.Generate Waktu");
        System.out.println("2.Lihat Data Waktu");
        System.out.println("99. Menu Utama");
        do {
            System.out.print("Pilihan : ");
            pil = cin.nextInt();
        } while (pil<=0);
        if (pil == 1) {
            c.menuGenerateWaktu();
        } else if (pil == 2) {
            c.menuShowWaktu();
        } else {
            ControllerAdmin a = new ControllerAdmin();
            a.index();
        }

    }

    public void generateWaktuMenu() throws FileNotFoundException, IOException {
        char pil;
        int cek=0;
        ControllerTime c = new ControllerTime();
        Scanner cin = new Scanner(System.in);
        do {
            System.out.print("Apakah anda yakin untuk generate Waktu (Y/N)? ");
            pil = cin.next().charAt(0);
            if(pil=='Y'||pil=='N'){cek=1;}
            
        } while (cek==0);
        if (pil == 'Y') {
            c.generateWaktu();
            System.out.println("Generate Waktu Berhasil");
        }
        c.index();
    }

    public void showTimeMenu() throws FileNotFoundException, IOException {
        ControllerTime c = new ControllerTime();
        JSONArray time = c.getTime();
        System.out.println("No.		Kode Waktu		Waktu");
        for (int i = 0; i < time.length(); i++) {
            JSONObject object = new JSONObject(time.get(i).toString());
            System.out.println((i + 1) + "		" + object.getString("kodeWaktu") + "			" + object.getString("waktu"));

        }
        c.index();
    }

}
