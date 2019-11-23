package controller;

import java.util.ArrayList;

public class Wagon {
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
    public void addSeat(int no, int status) {
        Seat s = new Seat(no, status);

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