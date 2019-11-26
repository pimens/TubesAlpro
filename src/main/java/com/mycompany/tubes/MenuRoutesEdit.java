/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import controller.ControllerRoutes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author pmen
 */
public class MenuRoutesEdit {

    ControllerRoutes c;
    Scanner cin;

    public MenuRoutesEdit() throws FileNotFoundException {
        c = new ControllerRoutes();
        cin = new Scanner(System.in);
    }

    public void index() throws FileNotFoundException, IOException {
        String kode;
        int cek = 0;
        String src, dst, b, p, idS, idD;
        System.out.println("#EDIT DATA RUTE#");
        c.print();
        System.out.print("Kode Rute : ");
        kode = cin.nextLine();
        do {
            System.out.print("New Data : ");
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
        c.editRoute(idS, idD, b, p,kode);
        c.index();
    }

}
