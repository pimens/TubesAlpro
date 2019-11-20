package com.mycompany.tubes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import controller.ControllerTrainsByRoute;

public class MenuTrainsByRoute {
    Scanner sc = new Scanner(System.in);
    ControllerTrainsByRoute con;

    public MenuTrainsByRoute() throws FileNotFoundException, IOException {
        con = new ControllerTrainsByRoute();
    }

    public void index(int act) throws FileNotFoundException, IOException {
        if (act == 0) {
            System.out.print("Kode Rute : ");
            String rute = sc.next();

            System.out.println("Kereta Api berdasarkan Rute");
            System.out.println("------------------------------");
            printData(rute);
            System.out.println("------------------------------");
        } else {
            System.out.print("Kode Rute : ");
            String rute = sc.next();
            System.out.println("Kereta Api berdasarkan Rute");
            System.out.println("------------------------------");
            addData(rute);
            System.out.println("------------------------------");
            System.out.println("Kereta Api Untuk Rute Berhasil Ditambahkan");
            System.out.println("------------------------------");
        }
        
    }

    public void printData(String rute) {
        ArrayList<String> data = con.showData(rute);
        
        System.out.println("No\tKode Kereta Rute\tKode Rute\tKereta Tersedia");
        System.out.print("1\tKR01\t\t\t" + rute + "\t\t");
        
        int i = 0;
        for (String a : data) {
            if (i==0) {
                i = 1;
                System.out.println(a);
            } else {
                System.out.println("\t\t\t\t\t\t" + a);
            }
        }
    }

    public void addData(String rute) throws FileNotFoundException, IOException {
        ArrayList<String> temp = new ArrayList<>();

        String code;
        int i = 1;
        do {
            System.out.print("Kereta " + i + " : ");
            code = sc.next();
            if (!code.equals("99")) {
                temp.add(code);
            }
            i += 1;
        } while (!code.equals("99"));

        con.addData(rute, temp);
    }
}