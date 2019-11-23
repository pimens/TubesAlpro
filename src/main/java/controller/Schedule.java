package controller;

public class Schedule {
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
    public Schedule(String k, String d, String dt, String a, String o, String ds, String n, int s) {
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
        System.out.println(String.format("%s\t\t%s\t%s\t\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s", kode, date, depart, origin, destination, arrive, name, descStatus()));
    }
}