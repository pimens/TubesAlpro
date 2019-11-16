/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import controller.ControllerAdmin;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author pmen
 */
public class MenuAdmin {

    public static JSONObject session;

    public void index() throws FileNotFoundException {
        Scanner cin = new Scanner(System.in);
        int pil;
        ControllerAdmin admin = new ControllerAdmin();
        System.out.println("#Menu Admin#");
        System.out.println("Welcome, Admin");
        System.out.println("1. Kelola Akun ");
        System.out.println("2. Kelola Data Kota ");
        System.out.println("3. Generate Waktu ");
        System.out.println("4. Kelola Rute ");
        System.out.println("5. Kelola Stasiun ");
        System.out.println("6. Kelola Jalur Stasiun Pada Rute ");
        System.out.println("7. Kelola Waktu Pada Rute ");
        System.out.println("8. Kelola Kereta Pada Rute ");
        System.out.println("9. Generate Jadwal Kereta Api ");
        System.out.println("10. Lihat Pemasukan ");
        System.out.println("11. Lihat Jadwal Kereta Api ");
        System.out.println("0. Logout ");
        System.out.print("Pilihan  :");
        pil = cin.nextInt();
        switch (pil) {
            case 1:
                admin.managePassenger();
                break;
            case 2:
                System.out.println("2");
                break;
            default:
                System.out.println("Salah");
                break;
        }
    }
}
