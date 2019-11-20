package com.mycompany.tubes;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuBooking {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> penumpang = new ArrayList<>();

    public static void main(String[] args) {
        MenuBooking b = new MenuBooking();
        b.searchSchedule();
    }

    public void searchSchedule() {
        System.out.println("Cari Jadwal Kereta Api");

        System.out.print("Keberangkatan : ");
        String origin = sc.next();
        System.out.print("Tujuan : ");
        String destination = sc.next();
        System.out.print("Tanggal : ");
        String date = sc.next();
        System.out.println("------------------------------");

        ArrayList<Jadwal> jadwal = new ArrayList<>();

        // Dummy
        jadwal.add(new Jadwal("JW00001", "01-11-2019", "08.00", "12.00", "Jakarta", "Bandung", "KAI18801", 4));
        jadwal.add(new Jadwal("JW00002", "01-11-2019", "13.00", "17.00", "Jakarta", "Bandung", "KAI18801", 20));
        jadwal.add(new Jadwal("JW00003", "01-11-2019", "09.00", "13.00", "Bandung", "Jakarta", "KAI18801", 0));
        System.out.println("Kode Jadwal\tTanggal\t\tWaktu Keberangkatan\tKeberangkatan\tTujuan\t\tWaktu Tiba\tKAI\t\tStatus");
        for (Jadwal j : jadwal) {
            j.print();
        }

        System.out.println("------------------------------");
        System.out.println("1. Booking Tiket");
        System.out.println("99. Menu Utama");

        System.out.print("\nPilih Aksi : ");
        int nextstep = sc.nextInt();
        if (nextstep == 1) {
            bookTicket();
        }
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
    }
}

class Train {
    // Attributes
    private ArrayList<Wagon> wagons = new ArrayList<>();
    
    // Constructor
    public Train() {
        // Do nothing
    }

    public Train(ArrayList<Wagon> w) {
        wagons.addAll(w);
    }

    // Method
    public void addWagon(Wagon w) {
        wagons.add(w);
    }

    public void removeWagon() {
        wagons.remove(wagons.size() - 1);
    }

    public void print() {
        for (Wagon w : wagons) {
            w.print();
            System.out.println("");
        }
    }
}

class Wagon {
    // Attributes
    private int type; // 0 : Premium, 1 : Bisnis
    private int number; // Nomor gerbong
    private ArrayList<Seat> seats = new ArrayList<>(); // Kumpulan kursi

    // Constructor
    public Wagon() {
        type = 0;
        number = 0;
    }

    public Wagon(int t, int n) {
        type = t;
        number = n;
    }

    // Getter
    public int getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    // Setter
    public void setType(int t) {
        type = t;
    }

    public void setNumber(int n) {
        number = n;
    }

    // Method
    public void addSeat() {
        Seat s = new Seat(seats.size() + 1, 0);

        seats.add(s);
    }

    public void deleteSeat() {
        seats.remove(seats.size() - 1);
    }

    public void print() {
        String nama = "Premium";
        if (type == 1) {
            nama = "Business";
        }
        System.out.println("Gerbong " + nama + " " + number);

        int count = 0;
        for (Seat s : seats) {
            System.out.print(String.format("| %c%d-%d %c ", nama.charAt(0), number, s.getNumber(), s.representStatus()));
            count += 1;
            if (count == 10) {
                System.out.println("|");
                count = 0;
            }
        }
        if (count != 0) {
            System.out.println("|");
        }
    }
}

class Seat {
    // Attributes
    private int number;
    private int status;
    private double price;

    // Constructor
    public Seat() {
        number = 0;
        status = 0;
    }

    public Seat(int n, int s) {
        number = n;
        status = s;
    }

    // Getter
    public int getNumber() {
        return number;
    }

    public int getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    // Setter
    public void setNumber(int n) {
        number = n;
    }

    public void setStatus(int s) {
        status = s;
    }

    public void setPrice(double p) {
        price = p;
    }

    // Method
    public boolean isAvailable() {
        return (status == 0);
    }

    public char representStatus() {
        if (status == 0) {
            return 'E';
        } else {
            return 'F';
        }
    }
}

class Jadwal {
    // Attributes
    private String kode;
    private String date;
    private String depart;
    private String arrive;
    private String origin;
    private String destination;
    private String name;
    private int status;

    // Constructor
    public Jadwal(String k, String d, String dt, String a, String o, String ds, String n, int s) {
        kode = k;
        date = d;
        depart = dt;
        arrive = a;
        origin = o;
        destination = ds;
        name = n;
        status = s;
    }

    // Getter
    public String getKode() {
        return kode;
    }

    public String getDate() {
        return date;
    }

    public String getDepart() {
        return depart;
    }

    public String getArrive() {
        return arrive;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    // Setter
    public void setKode(String k) {
        kode = k;
    }

    public void setDate(String d) {
        date = d;
    }

    public void setDepart(String d) {
        depart = d;
    }

    public void setArrive(String a) {
        arrive = a;
    }

    public void setOrigin(String o) {
        origin = o;
    }

    public void setDestination(String d) {
        destination = d;
    }

    public void setName(String n) {
        name = n;
    }

    public void setStatus(int s) {
        status = s;
    }

    // Method
    public String descStatus() {
        String temp;
        if (status > 0) {
            temp = "Sisa " + status + " Kursi";
        } else {
            temp = "Full";
        }

        return temp;
    }

    public void print() {
        System.out.println(String.format("%s\t\t%s\t%s\t\t\t%s\t\t%s\t\t%s\t\t%s\t%s", kode, date, depart, origin, destination, arrive, name, descStatus()));
    }
}