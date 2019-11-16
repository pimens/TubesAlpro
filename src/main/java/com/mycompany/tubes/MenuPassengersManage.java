package com.mycompany.tubes;

import controller.Home;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import model.ModelPassengers;

public class MenuPassengersManage {

    public void index() throws FileNotFoundException, IOException {
        Scanner cin = new Scanner(System.in);
        ModelPassengers p = new ModelPassengers();
        Home u = new Home();
        String ktp, nama, hp, email, p2;
        System.out.println("#Kelola Akun By Admin#");
        System.out.println("");
        do {
            System.out.print("Masukkan Nomor KTP : ");
            ktp = cin.nextLine();
        } while (!ktp.matches("^[0-9]*$") || ktp.length() != 16);
        p.getDataByKTP(ktp);
        System.out.println("--Data Pengguna--");
        System.out.println("Nama Lengkap : " + p.getNama());
        System.out.println("Nomor Handphone : " + p.getNomorHandphone());
        System.out.println("Email : " + p.getEmail());
        System.out.println("Password : " + p.getPassword());
        System.out.println("");
        System.out.println("--Ubah Data Pengguna--");
        do {
            System.out.print("Nama Lengkap : ");
            nama = cin.nextLine();
            if (!nama.matches("^[a-zA-Z\\s]*$")) {
                System.out.println("Tidak Valid ");
            }
        } while (!nama.matches("^[a-zA-Z\\s]*$"));
        do {
            System.out.print("Nomor Handphone : ");
            hp = cin.nextLine();
            if (!u.isNumber(hp, 11, 12)) {
                System.out.println("Tidak Valid");
            }
        } while (!u.isNumber(hp, 11, 12));
        System.out.print("Email : ");
        email = cin.nextLine();
        System.out.print("Password : ");
        p2 = cin.nextLine();
        p.setEmail(email);
        p.setNama(nama);
        p.setNomorHandphone(hp);
        p.setPassword(p2);
        p.pushNewDataUser();
        System.out.println("Data Berhasil Diupdate, Berikut Data Terbaru:");
        System.out.println("Nomor KTP : " + p.getKtp());
        System.out.println("Nama Lengkap : " + p.getNama());
        System.out.println("Nomor Handphone : " + p.getNomorHandphone());
        System.out.println("Email : " + p.getEmail());
        System.out.println("Password : " + p.getPassword());
        System.out.println("");
    }
}
