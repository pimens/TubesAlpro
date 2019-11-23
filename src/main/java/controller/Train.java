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