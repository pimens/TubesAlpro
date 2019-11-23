package com.mycompany.tubes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ControllerBooking;
import controller.Schedule;

public class MenuBooking {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> penumpang = new ArrayList<>();
    ControllerBooking con;

    public MenuBooking() {
        con = new ControllerBooking();
    }

    public void searchSchedule() throws FileNotFoundException, IOException {
        System.out.println("Cari Jadwal Kereta Api");

        System.out.print("Keberangkatan : ");
        String origin = sc.next();
        System.out.print("Tujuan : ");
        String destination = sc.next();
        System.out.print("Tanggal : ");
        String date = sc.next();
        System.out.println("------------------------------");

        ArrayList<Schedule> jadwal = new ArrayList<>();

        jadwal = con.getSchedule(origin, destination, date);

        System.out.println("Kode Jadwal\tTanggal\t\tWaktu Keberangkatan\tKeberangkatan\tTujuan\t\tWaktu Tiba\tKAI\t\tStatus");
        for (Schedule j : jadwal) {
            j.print();
        }

        System.out.println("------------------------------");
        System.out.println("1. Booking Tiket");
        System.out.println("99. Menu Utama");

        System.out.print("\nPilih Aksi : ");
        int pil = sc.nextInt();
        con.subMenu(pil);
    }

    public void bookTicket() {
        System.out.print("Kode Jadwal : ");
        String kode = sc.next();
        System.out.print("Jumlah : ");
        int jml = sc.nextInt();
        System.out.println("---------------------");
        
        for (int i=0; i<jml; i++) {
            System.out.print("Penumpang " + (i+1) + " : ");
            String temp = sc.next();
            penumpang.add(temp);
        }

        // Create Dummy Data
        Train sepur = new Train();
        Wagon w1 = new Wagon(1,1);
        Wagon w2 = new Wagon(1,2);
        Wagon w3 = new Wagon(1,3);
        Wagon w4 = new Wagon(0,1);
        Wagon w5 = new Wagon(0,2);
        Wagon w6 = new Wagon(0,3);
        for (int j=0; j<6; j++) {
            int k = 10;
            if (j > 2) k = 20;
            for (int l=0; l<k; l++) {
                if (j == 0) {
                    w1.addSeat();
                } else if (j == 1) {
                    w2.addSeat();
                } else if (j == 2) {
                    w3.addSeat();
                } else if (j == 3) {
                    w4.addSeat();
                } else if (j == 4) {
                    w5.addSeat();
                } else {
                    w6.addSeat();
                }
            }
        }

        sepur.addWagon(w1);
        sepur.addWagon(w2);
        sepur.addWagon(w3);
        sepur.addWagon(w4);
        sepur.addWagon(w5);
        sepur.addWagon(w6);

        System.out.println("------------------------------------------");

        sepur.print();

        System.out.println("------------------------------------------");

        System.out.println("Pilih Kursi (Dengan Tanda E/Empty) :");
        
        ArrayList<String> kursi = new ArrayList<>();
        for (int i=0; i<jml; i++) {
            System.out.print("Kursi " + (i+1) + " : ");
            String temp = sc.next();
            kursi.add(temp);
        }

        System.out.println("------------------------------------------");

        System.out.println("Total Pembayaran = ");
        System.out.println("Kode Rekening = ");

        System.out.println("------------------------------------------");
        System.out.println("1. Pembayaran");
        System.out.println("99. Menu Utama");
        System.out.print("\nPilih Aksi : ");
        int pil = sc.nextInt();
        con.subMenu(pil + 1);
    }
}