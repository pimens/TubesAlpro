package model;

public class Seat {
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