package controller;

import java.util.ArrayList;

import com.mycompany.tubes.Table;

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

    public String temp(char j, int n, int no, char status) {
        return (j+String.valueOf(n)+"-"+String.valueOf(no)+" "+status);
    }

    public void print() {
        String nama = "Premium";
        if (type == 1) {
            nama = "Business";
        }
        System.out.println("Gerbong " + nama + " " + number);
        
        Table st = new Table();
        st.setShowVerticalLines(true);

        int count = 0;
        char [] p1 = new char[10];
        int [] p2 = new int[10];
        int [] p3 = new int[10];
        char [] p4 = new char[10];
        
        for (Seat s : seats) {
            //System.out.print(String.format("| %c%d-%d %c ", nama.charAt(0), number, s.getNumber(), s.representStatus()));
            p1[count] = nama.charAt(0);
            p2[count] = number;
            p3[count] = s.getNumber();
            p4[count] = s.representStatus();
            count += 1;
            if (count == 10) {
                st.addRow(temp(p1[0],p2[0],p3[0],p4[0]),temp(p1[1],p2[1],p3[1],p4[1]),temp(p1[2],p2[2],p3[2],p4[2]),temp(p1[3],p2[3],p3[3],p4[3]),temp(p1[4],p2[4],p3[4],p4[4]),temp(p1[5],p2[5],p3[5],p4[5]),temp(p1[6],p2[6],p3[6],p4[6]),temp(p1[7],p2[7],p3[7],p4[7]),temp(p1[8],p2[8],p3[8],p4[8]),temp(p1[9],p2[9],p3[9],p4[9]));
                //System.out.println("|");
                count = 0;
            }
        }
        st.print();
    }
}