/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import controller.ControllerAdmin;
import controller.ControllerReport;
import controller.ControllerSchedules;
import controller.ControllerStationsByRoutes;
import controller.ControllerTimeByRoute;
import controller.ControllerTrainsByRoute;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author pmen
 */
public class MenuAdmin {

    public static JSONObject session;

    public void index() throws FileNotFoundException, IOException, ParseException {
        Scanner cin = new Scanner(System.in);
        int pil;
        ControllerAdmin admin = new ControllerAdmin();
        ControllerReport reportService = new ControllerReport();
        ControllerTimeByRoute timeByRoute = new ControllerTimeByRoute();
        ControllerTrainsByRoute trainsByRoute = new ControllerTrainsByRoute();
        ControllerStationsByRoutes stationByRoute = new ControllerStationsByRoutes();
        ControllerSchedules schedules = new ControllerSchedules();
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
            case 0:                
                System.out.println("Terim Kasih");
                break;
            case 1:
                admin.managePassenger();
                this.index();
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("Waktu");
                break;
            case 4:
                System.out.println("Rute");
                break;
            case 5:
                System.out.println("Stasiun");
                break;          
            case 6:
                stationByRoute.index("#Menu Stasiun Berdasarkan Rute#");
                this.index();
                break;
            case 7:
                timeByRoute.index();
                this.index();
                break;
            case 8:
                trainsByRoute.index();
                this.index();
                break;
            case 9:
                schedules.index();
                this.index();
                break;
            case 10:
                reportService.index();
                this.index();
                break;
            case 11:
                schedules.showJadwal();
                this.index();
                break;
            default:
                System.out.println("Salah");
                this.index();
                break;
        }
    }
}
