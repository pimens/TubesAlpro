/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import controller.ControllerSchedules;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author pmen
 */
public class MenuGenerateSchedules {
    ControllerSchedules c;
    public MenuGenerateSchedules() {
        c = new ControllerSchedules();
    }

    public void index() throws FileNotFoundException, IOException, ParseException, ParseException {
        char pil;
        int cek = 0;
        Scanner cin = new Scanner(System.in);
        do {
            System.out.print("Apakah anda yakin untuk generate Waktu (Y/N)? ");
            pil = cin.next().charAt(0);
            if (pil == 'Y' || pil == 'N') {
                cek = 1;
            }

        } while (cek == 0);
        if (pil == 'Y') {
            c.generateSchedules();
            System.out.println("Generate Jadwal Berhasil");
        }
    }
}
