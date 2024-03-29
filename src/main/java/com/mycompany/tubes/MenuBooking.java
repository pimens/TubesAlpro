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
        System.out.println("# Cari Jadwal Kereta Api #");

        System.out.print("Keberangkatan : ");
        String origin = sc.next();
        System.out.print("Tujuan : ");
        String destination = sc.next();
        System.out.print("Tanggal : ");
        String date = sc.next();
        System.out.println("------------------------------");

        ArrayList<Schedule> jadwal = new ArrayList<>();

        jadwal = con.getSchedule(origin, destination, date);

        Table st = new Table();
        st.setShowVerticalLines(true);
        st.setHeaders("Kode Jadwal", "Tanggal", "Waktu Keberangkatan", "Keberangkatan", "Tujuan", "Waktu Tiba", "KAI", "Status");//optional - if not used then there will be no header and horizontal lines    
        for (Schedule j : jadwal) {
            st.addRow(j.getKode(),j.getDate(),j.getDepart(),j.getOrigin(),j.getDestination(),j.getArrive(),j.getName(),j.descStatus());
        }
        st.print();

        System.out.println("------------------------------");
        System.out.println("1. Booking Tiket");
        System.out.println("99. Menu Utama");

        System.out.print("\nPilih Aksi : ");
        int pil = sc.nextInt();
        con.subMenu(pil,date);
    }

    public void bookTicket(String tgl) throws JSONException, IOException {
        System.out.println("# Booking Ticket #");
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
        
        double jumlah = 0;
        ArrayList<String> kursi = new ArrayList<>();
        for (int i=0; i<jml; i++) {
            System.out.print("Kursi " + (i+1) + " : ");
            String temp = sc.next();
            kursi.add(temp);
            String te = "" + temp.charAt(0);
            jumlah += con.getHarga(tgl, kode, te);
        }

        con.booking(tgl, kode, kursi);

        String rekening = con.addBooking(kode, tgl, penumpang, kursi, (long) jumlah);

        System.out.println("------------------------------------------");

        System.out.println("Total Pembayaran = " + printRupiah(jumlah));
        System.out.println("Kode Rekening = " + rekening);

        System.out.println("------------------------------------------");
        System.out.println("1. Pembayaran");
        System.out.println("99. Menu Utama");
        System.out.print("\nPilih Aksi : ");
        int pil = sc.nextInt();
        con.subMenu(pil + 1,"");
    }

    public String printRupiah(double j) {
        String rp = "Rp";
        String angka = String.valueOf((int) j);
        String tampung = "";

        int flag = 0;
        for (int i=angka.length()-1; i>=0; i--) {
            tampung += angka.charAt(i);
            flag += 1;
            if (flag == 3) {
                flag = 0;
                tampung += '.';
            }
        }

        StringBuilder input1 = new StringBuilder(); 
  
        // append a string into StringBuilder input1 
        input1.append(tampung); 
  
        // reverse StringBuilder input1 
        input1 = input1.reverse(); 
  
        // print reversed String 
        rp += input1 + ",00";

        return rp;
    }

    public void payment() throws JSONException, IOException {
        System.out.println("# Pembayaran Tiket #");
        
        String status = "N";
        String rek;
        double jumlah;
        int flag = 0;
        do {
            System.out.print("Kode Rekening : ");
            rek = sc.next();
            System.out.print("Total Pembayaran : ");
            jumlah = sc.nextDouble();
            System.out.print("Apakah data pembayaran sudah benar (Y/N)? ");
            status = sc.next();

            if (status.equals("Y")) {
                System.out.println("------------------------------");
                int cek = con.checkPayment(rek, String.valueOf((int) jumlah));
                if (cek == 0) {
                    System.out.println("Kode Rekening Tidak Ditemukan!");
                } else if (cek == 2) {
                    System.out.println("Harga kereta tidak sesuai dengan pembayaran!");
                } else if (cek == 3) {
                    System.out.println("Kode rekening di atas sudah dibayar!");
                } else if (cek == 1) {
                    System.out.println("Pembayaran Berhasil!");
                    flag = 1;
                } else if (cek == 4) {
                    System.out.println("Kode Rekening tidak ditemukan pada akun anda!");   
                }
            }
        } while (status.equals("N"));

        if (flag == 1) {
            ArrayList<String> penumpang = con.payment(rek);

            System.out.println("Kode Tiket Anda = " + Character.toUpperCase(penumpang.get(0).charAt(0)) + rek.substring(7) + Character.toUpperCase(penumpang.get(0).charAt(penumpang.get(0).length()-1)) + 'Z');
            for (int i=0; i<penumpang.size(); i++) {
                int no = i+1;
                System.out.println("Penumpang " + no + " = " + penumpang.get(i));
            }
        }
        System.out.println("------------------------------");
        System.out.println("99. Menu Utama");
        System.out.print("\nPilih Aksi : ");
        int pil = sc.nextInt();
    }
    
}