package com.mycompany.tubes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import controller.ControllerTrainsByRoute;

public class MenuTrainsByRoute {
    Scanner sc = new Scanner(System.in);
    ControllerTrainsByRoute con;

    public MenuTrainsByRoute() throws FileNotFoundException, IOException {
        con = new ControllerTrainsByRoute();
    }

    public void index() throws FileNotFoundException, IOException {
        System.out.println("\n# KELOLA KERETA API BERDASARKAN RUTE #");
        System.out.println("1. Tambah Kereta Pada Rute");
        System.out.println("2. Lihat Kereta Pada Rute");
        System.out.println("3. Delete Kereta Pada Rute");
        System.out.println("99. Menu Utama");
        System.out.print("Masukkan nomor : ");
        int pil = sc.nextInt();
        con.subMenu(pil);
    }
}