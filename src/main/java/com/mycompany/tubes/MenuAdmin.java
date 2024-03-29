/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import controller.ControllerAdmin;
import controller.ControllerCities;
import controller.ControllerReport;
import controller.ControllerRoutes;
import controller.ControllerSchedules;
import controller.ControllerStationsByRoutes;
import controller.ControllerTime;
import controller.ControllerTimeByRoute;
import controller.ControllerTrain;
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
    Scanner cin;
    public MenuAdmin(){
       cin = new Scanner(System.in);
    }
    public void index() throws FileNotFoundException, IOException, ParseException {
        int pil = 0;
        String input;
        
        ControllerAdmin admin = new ControllerAdmin();
        ControllerReport reportService = new ControllerReport();
        ControllerTimeByRoute timeByRoute = new ControllerTimeByRoute();
        ControllerTrainsByRoute trainsByRoute = new ControllerTrainsByRoute();
        ControllerStationsByRoutes stationByRoute = new ControllerStationsByRoutes();
        ControllerSchedules schedules = new ControllerSchedules();
        ControllerCities city = new ControllerCities();
        ControllerTime time = new ControllerTime();
        ControllerRoutes rute = new ControllerRoutes();
        ControllerTrain train = new ControllerTrain();
        System.out.println("#Menu Admin#");
        System.out.println("Welcome, Admin");
        System.out.println("1. Kelola Akun ");
        System.out.println("2. Kelola Data Kota ");
        System.out.println("3. Generate Waktu ");
        System.out.println("4. Kelola Rute ");
        System.out.println("5. Kelola Kereta ");
        System.out.println("6. Kelola Jalur Stasiun Pada Rute ");
        System.out.println("7. Kelola Waktu Pada Rute ");
        System.out.println("8. Kelola Kereta Pada Rute ");
        System.out.println("9. Generate Jadwal Kereta Api ");
        System.out.println("10. Lihat Pemasukan ");
        System.out.println("11. Lihat Jadwal Kereta Api ");
        System.out.println("0. Logout ");
        
        do {
            System.out.print("Pilihan  :");
            input = cin.nextLine();
        	if(!checkInput(input)) {
        		System.out.println("Input Salah");
        	}else {
        		pil = Integer.parseInt(input);
        	}
        }
        while(!checkInput(input));
        
        switch (pil) {
            case 0:
                System.out.println("Terima Kasih");
                System.exit(0);
                break;
            case 1:
                admin.managePassenger();
                this.index();
                break;
            case 2:
                city.index();
                break;
            case 3:
                time.index();
                break;
            case 4:
                rute.index();
                break;
            case 5:
                train.index();
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
    private boolean checkInput(String input) {
        try {
            Integer.valueOf(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
	}
}
