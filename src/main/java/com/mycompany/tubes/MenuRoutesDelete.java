/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import controller.ControllerRoutes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author pmen
 */
public class MenuRoutesDelete {
    ControllerRoutes c;
    Scanner cin;
    public MenuRoutesDelete() throws FileNotFoundException {
        c = new ControllerRoutes();
        cin = new Scanner(System.in);
    }

    public void index() throws FileNotFoundException, IOException, ParseException {
        String kode;
        System.out.println("#DELETE DATA RUTE#");
        c.print();
        System.out.print("Kode Rute : ");
        kode = cin.next();
        if(c.deleteRoute(kode)){
            System.out.println("Tidak dapat hapus rute, rute digunakan oleh banyak data");
        }
        c.index();
    }

}
