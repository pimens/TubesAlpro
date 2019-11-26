package com.mycompany.tubes;

import controller.ControllerRoutes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MenuRoutes {

    ControllerRoutes c;

    public MenuRoutes() throws FileNotFoundException {
        c = new ControllerRoutes();
    }

    public void index() throws FileNotFoundException, IOException {
        Scanner cin = new Scanner(System.in);
        int pil=0;
        System.out.println("#Kelola Rute#");
        System.out.println("1. Tambah Data Rute");
        System.out.println("2. Lihat Data Rute");
        System.out.println("3. Edit Data Rute");
        System.out.println("4. Delete Data Rute");
        System.out.print("Pilihan : ");
        pil = cin.nextInt();
        switch (pil) {
            case 1:
                c.menuAdd();
                break;
            case 2:
                c.menuShow();
                break;
            case 3:
                c.menuEdit();
                break;
            case 4:
                c.menuDelete();
                break;
            default:
                this.index();
                break;
        }       
    }

}
