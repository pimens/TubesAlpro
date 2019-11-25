package com.mycompany.tubes;

import model.ModelCities;
import controller.ControllerCities;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class MenuKota {

    ControllerCities cc = new ControllerCities();

    public void index() throws FileNotFoundException, IOException {
        Scanner cin = new Scanner(System.in);
        int pil;

        System.out.println(" ");
        System.out.println("#Menu Kelola Kota#");
        System.out.println("1. Tambah Data Kota ");
        System.out.println("2. Lihat Data Kota ");
        System.out.println("3. Edit Data Kota ");
        System.out.println("4. Delete Data Kota ");
        System.out.println("99. Menu Utama ");

        System.out.print("Pilihan  :");
        pil = cin.nextInt();
        if (pil == 1) {
            cc.menuAddCity();
        }
        if (pil == 2) {
            cc.showCity();
        }
        if (pil == 3) {
            cc.editCity();
        }
        if (pil == 4) {
            cc.deleteCity();
        } else {
            MenuAdmin ma = new MenuAdmin();
            ma.index();
        }
    }

    public void menuAddCity() throws FileNotFoundException, IOException {
        Scanner cin = new Scanner(System.in);
        String input = "";
        String kota = "";
        String kodekota = "";
        int cek = 0;
        do {
            System.out.print("Tambah Kota :");
            input = cin.nextLine();
            if (input.contains(" ")) {

                String[] datakota = input.split(" ", 2);
                kodekota = datakota[0];
                if (cc.cityExist(kodekota)) {
                    cek = 1;
                    System.out.println("Kota Sudah Ada");
                } else {
                    cek=0;
                    kota = datakota[1];
                    cc.addCity(kota, kodekota);
                }
            } else {
                System.out.println("Format Tidak Valid");
                cek = 1;
            }
        } while (cek == 1);
        cc.index();

    }

    public void showCity() throws FileNotFoundException, IOException {
        System.out.println("No.		Kode		Nama		");
        JSONArray kota = cc.getDataKota();
        for (int i = 0; i < kota.length(); i++) {
            JSONObject object = new JSONObject(kota.get(i).toString());
            System.out.println((i + 1) + "		" + object.getString("kodeKota") + "		" + object.getString("nama") + "		");

        }
        cc.index();
    }

    public void editCityMenu() throws FileNotFoundException, IOException {

        Scanner cin = new Scanner(System.in);
        String input = "";
        String kota = "";
        String kodekota = "";
        int cek = 0;
        do {
            System.out.print("Edit kota :");
            input = cin.nextLine();
            if (input.contains("EDIT_")) {
                String[] kotalama = input.split("_", 2);

                if (!cc.cityExist(kotalama[1])) {
                    cek = 1;
                    System.out.print("Kota Tidak Ada");
                } else {
                    cek=0;
                    System.out.print("kode kota :");
                    kodekota = cin.nextLine();
                    System.out.print("kota :");
                    kota = cin.nextLine();
                    cc.editCity(kotalama[1], kodekota, kota);
                }
            } else {
                System.out.println("Format Tidak Valid");
                cek = 1;
            }
        } while (cek == 1);
        cc.index();

    }

    public void deleteCityMenu() throws FileNotFoundException, IOException {

        Scanner cin = new Scanner(System.in);
        String input = "";
        System.out.print("Hapus Kota :");
        input = cin.nextLine();

        if (input.contains("DELETE_")) {

            String[] kotalama = input.split("_", 2);
            cc.deleteCity(kotalama[1]);
            index();
        } else {
            System.out.println("format salah");
            index();

        }
    }

}
