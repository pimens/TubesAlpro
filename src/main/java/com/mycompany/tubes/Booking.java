package com.mycompany.tubes;

import java.util.ArrayList;
import java.util.Scanner;

import model.Train;
import model.Wagon;

public class Booking {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> penumpang = new ArrayList<>();

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
    }

    /*public static void main(String[] args) {
        Booking b = new Booking();
        b.bookTicket();
    }*/
}