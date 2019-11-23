package com.mycompany.tubes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONException;

import controller.ControllerBooking;
import controller.Train;
import controller.Schedule;

public class MenuBooking {
    Scanner sc = new Scanner(System.in);
    ControllerBooking con;

    public MenuBooking() throws JSONException, IOException {
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
        con.subMenu(pil,date);
    }

    public void bookTicket(String tgl) throws JSONException, IOException {
        System.out.print("Kode Jadwal : ");
        String kode = sc.next();
        System.out.print("Jumlah : ");
        int jml = sc.nextInt();
        System.out.println("---------------------");
        
        ArrayList<String> penumpang = new ArrayList<>();
        for (int i=0; i<jml; i++) {
            System.out.print("Penumpang " + (i+1) + " : ");
            String temp = sc.next();
            penumpang.add(temp);
        }

        Train sepur = con.getTrain(kode, tgl);

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

        con.booking(tgl, kode, kursi);

        System.out.println("------------------------------------------");

        System.out.println("Total Pembayaran = ");
        System.out.println("Kode Rekening = ");

        System.out.println("------------------------------------------");
        System.out.println("1. Pembayaran");
        System.out.println("99. Menu Utama");
        System.out.print("\nPilih Aksi : ");
        int pil = sc.nextInt();
        con.subMenu(pil + 1,"");
    }
}