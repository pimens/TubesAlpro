package com.mycompany.tubes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import controller.ControllerTrainsByRoute;

public class MenuTrainsByRouteAdd {
    Scanner sc = new Scanner(System.in);
    ControllerTrainsByRoute con;

    public MenuTrainsByRouteAdd() throws FileNotFoundException, IOException {
        con = new ControllerTrainsByRoute();
    }

    public void index() throws FileNotFoundException, IOException {
        System.out.println("\n# TAMBAH KERETA API BERDASARKAN RUTE #");
        System.out.print("Kode Rute : ");
        String rute = sc.next();
        System.out.println("Kereta Api berdasarkan Rute");
        System.out.println("------------------------------");
        addData(rute);
        System.out.println("------------------------------");
        System.out.println("1. Tambah Kereta Pada Rute");
        System.out.println("2. Lihat Kereta Pada Rute");
        System.out.println("3. Delete Kereta Pada Rute");
        System.out.println("99. Menu Utama");
        System.out.print("Masukkan nomor : ");
        int pil = sc.nextInt();
        con.subMenu(pil);
    }

    public void addData(String rute) throws FileNotFoundException, IOException {
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> temp2 = new ArrayList<>();

        String code;

        temp = con.getCurrent(rute);

        int i = temp.size();

        for (int j=0; j<i; j++) {
            System.out.println("Kereta " + j + " : " + temp.get(j));
        }

        int flag = 0;
        do {
            System.out.print("Kereta " + i + " : ");
            code = sc.next();
            if (!code.equals("99")) {
                temp2.add(code);
                i += 1;
                flag = 1;
            }
        } while (!code.equals("99"));

        con.addData(rute, temp2);

        System.out.println("------------------------------");
        if (flag == 0) {
            System.out.println("Tidak ada kereta baru ditambahkan pada rute");
        } else {
            System.out.println("Kereta Api Untuk Rute Berhasil Ditambahkan");
        }
    }
}