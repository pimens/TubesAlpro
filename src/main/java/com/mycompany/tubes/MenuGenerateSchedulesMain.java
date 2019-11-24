/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import controller.ControllerAdmin;
import controller.ControllerSchedules;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author pmen
 */
public class MenuGenerateSchedulesMain {
    ControllerSchedules c;
    public MenuGenerateSchedulesMain() {
        c = new ControllerSchedules();
    }
    public void index() throws FileNotFoundException, IOException, ParseException {        
        int pil;
        Scanner cin = new Scanner(System.in);
        System.out.println("1.Generate Jadwal");
        System.out.println("2.Lihat Data Jadwal");
        System.out.println("99. Menu Utama");
        do {
            System.out.print("Pilihan : ");
            pil = cin.nextInt();
        } while (pil<=0);
        switch (pil) {
            case 1:
                c.menuGenerateSchedules();
                break;
            case 2:
                c.showJadwal();
                break;
            default:
                break;
        }

    }
}
