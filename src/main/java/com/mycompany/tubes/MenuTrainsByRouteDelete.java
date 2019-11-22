package com.mycompany.tubes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import controller.ControllerTrainsByRoute;

public class MenuTrainsByRouteDelete {
    Scanner sc = new Scanner(System.in);
    ControllerTrainsByRoute con;

    public MenuTrainsByRouteDelete() throws FileNotFoundException, IOException {
        con = new ControllerTrainsByRoute();
    }

    public void index() throws FileNotFoundException, IOException {
        System.out.println("\n# DELETE KERETA API BERDASARKAN RUTE #");
        System.out.print("Kode Rute : ");
        String rute = sc.next();
        System.out.println("Kereta Api berdasarkan Rute");
        System.out.println("------------------------------");
        printData(rute);
        delete(rute);
        System.out.println("------------------------------");
        System.out.println("1. Tambah Kereta Pada Rute");
        System.out.println("2. Lihat Kereta Pada Rute");
        System.out.println("3. Delete Kereta Pada Rute");
        System.out.println("99. Menu Utama");
        System.out.print("Masukkan nomor : ");
        int pil = sc.nextInt();
        con.subMenu(pil);
    }

    public void printData(String rute) {
        ArrayList<String> data = con.showData(rute);
        
        System.out.println("No\tKode Kereta Rute\tKode Rute\tKereta Tersedia");
        System.out.print("1\tKR01\t\t\t" + rute + "\t\t");
        
        int i = 0;
        for (String a : data) {
            if (i==0) {
                i = 1;
                System.out.println(a);
            } else {
                System.out.println("\t\t\t\t\t\t" + a);
            }
        }
    }

    public void delete(String rute) throws FileNotFoundException, IOException {
        System.out.print("Masukkan kode kereta : ");
        String kode = sc.next();

        if (con.deleteData(rute,kode)) {
            System.out.println("------------------------------");
            System.out.println("Kereta Api Untuk Rute Berhasil Dihapus");
        } else {
            System.out.println("------------------------------");
            System.out.println("Kereta Api Untuk Rute Gagal Dihapus/Tidak Ditemukan");
        }
    }
}