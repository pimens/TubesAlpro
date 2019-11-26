package com.mycompany.tubes;

import controller.ControllerRoutes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MenuRoutesAdd {

    ControllerRoutes c;
    Scanner cin;

    public MenuRoutesAdd() throws FileNotFoundException {
        c = new ControllerRoutes();
        cin = new Scanner(System.in);
    }

    public void index() throws FileNotFoundException, IOException {
        String src, dst, b, p, idS, idD;
        int cek = 0;
        System.out.println("#TAMBAH DATA RUTE#");
        do {
            System.out.print("Tambah Data Rute :");
            src = cin.next();
            dst = cin.next();
            b = cin.next();
            p = cin.next();
            cek = 0;
            if (!c.cityIsExist(src)) {
                System.out.println("Kota " + src + " Tidak Ada");
                cek = 1;
            }
            if (!c.cityIsExist(dst)) {
                System.out.println("Kota " + dst + " Tidak Ada");
                cek = 1;
            }
            idS = c.getIdByCity(src);
            idD = c.getIdByCity(dst);
            if (c.isExistBySrcDsc(idS, idD)) {
                System.out.println("Rute Sudah Ada");
                cek = 1;
            }
        } while (cek == 1);
        c.addRoute(idS, idD, b, p);
        c.index();

    }
}
