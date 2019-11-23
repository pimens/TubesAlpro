package controller;

import java.util.ArrayList;

public class Train {
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