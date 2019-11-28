package com.mycompany.tubes;

import controller.ControllerTrain;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

//controller utama
public class MenuTrain {

    ControllerTrain c;

    public MenuTrain() {
        c = new ControllerTrain();
    }

    public void index() throws FileNotFoundException, IOException, ParseException {
        Scanner cin = new Scanner(System.in);
        int pil;

        System.out.println(" ");
        System.out.println("#Menu Kelola Data Kereta Api#");
        System.out.println("1. Tambah Data Kereta Api ");
        System.out.println("2. Lihat Data Kereta Api ");
        System.out.println("3. Edit Data Kereta Api ");
        System.out.println("4. Delete Data Kereta Api ");
        System.out.println("99. Menu Utama ");

        System.out.print("Pilihan  :");
        pil = cin.nextInt();
        if (pil == 1) {
            c.addTrainMenu();
        }
        if (pil == 2) {
            c.showTrainMenu();
        }
        if (pil == 3) {
            c.editTrainMenu();
        }
        if (pil == 4) {
            c.deleteTrainMenu();
        } else {
            MenuAdmin ma = new MenuAdmin();
            ma.index();
        }
    }

    public void addTrainMenu() throws FileNotFoundException, IOException, ParseException {
        Scanner cin = new Scanner(System.in);
        String input = "";
        String kodeKAI = "";
        String nameStation = "";
        String gerbong = "";
        String business = "";
        String premium = "";

        System.out.print("tambah stasiun :");
        input = cin.nextLine();
        if (input.contains(" ")) {
            System.out.println("proses.....mohon tunggu");
            String[] datakota = input.split(" (?=(?:[^\']*\'[^\']*\')*[^\']*$)");
            kodeKAI = datakota[0];
            nameStation = datakota[1];
            gerbong = datakota[2].substring(1);
            business = datakota[3].substring(1);
            premium = datakota[4].substring(1);

            if (Integer.valueOf(gerbong) == (Integer.valueOf(business) + Integer.valueOf(premium))) {
                if (Integer.valueOf(gerbong) <= 6) {
                    if(c.addTrain(kodeKAI, gerbong, premium, business, nameStation)){
                        System.out.println("Sudah Ada");
                    }
                    System.out.println("");
                }else{
                    System.out.println("Gerbong maksimal 6 ");
                }
                index();
            } else {
                System.out.println("format salah");
                index();
            }

        } else {
            System.out.print("(Input Format Tidak Sesuai");
            index();

        }

    }

    public void showTrainMenu() throws FileNotFoundException, IOException, ParseException {
        JSONArray train = new JSONArray();
        train = c.getDataTrain();

        Table st = new Table();
        int total = 0;
        st.setShowVerticalLines(true);
        st.setHeaders("No.", "Kode KAI", "Nama Stasiun", "Gerbong", "Business", "Premium");
        for (int i = 0; i < train.length(); i++) {
            JSONObject object = new JSONObject(train.get(i).toString());
            st.addRow(String.valueOf(i + 1), object.getString("kodeKAI"), object.getString("nameStation"), object.getString("gerbong"), object.getString("business"), object.getString("premium"));
        }
        st.print();
        c.index();
    }

    public void editTrainMenu() throws FileNotFoundException, IOException, ParseException {
        Scanner cin = new Scanner(System.in);
        String input = "";
        String kodeKAI = "";
        String nameStation = "";
        String gerbong = "";
        String business = "";
        String premium = "";

        System.out.print("Edit Kereta Api :");
        input = cin.nextLine();

        if (input.contains("EDIT_")) {
            System.out.print("kode KAI :");
            kodeKAI = cin.nextLine();
            System.out.print("Nama Stasiun :");
            nameStation = cin.nextLine();
            System.out.print("Gerbong :");
            gerbong = cin.nextLine();
            System.out.print("Business :");
            business = cin.nextLine();
            System.out.print("Premium :");
            premium = cin.nextLine();

            if (Integer.valueOf(gerbong) == (Integer.valueOf(business) + Integer.valueOf(premium))) {
                String[] kotalama = input.split("_", 2);
                c.editTrain(kotalama[1], kodeKAI, nameStation, gerbong, business, premium);
                index();
            } else {
                System.out.println("format salah");
                index();
            }

        } else {
            System.out.println("format salah");
            index();

        }

    }

    public void deleteTrainMenu() throws FileNotFoundException, IOException, ParseException {

        Scanner cin = new Scanner(System.in);
        String input = "";
        System.out.print("Hapus Kereta Api :");
        input = cin.nextLine();
        if (input.contains("DELETE_")) {
            String[] kotalama = input.split("_", 2);
            if(c.deleteTrain(kotalama[1])){
                System.out.println("Tidak Dapat Dihapus");
            }
            index();
        } else {
            System.out.println("format salah");
            index();

        }

    }

}
