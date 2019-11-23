package com.mycompany.tubes;

import controller.Home;
import controller.User;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author pmen
 */
public class MenuUserEdit {

    public void index() throws IOException {
        Scanner cin = new Scanner(System.in);
        String nama = null, hp = null, email = null, p = null;
        User us = new User();
        Home u = new Home();
        System.out.println("#KELOLA PROFILE BY PENUMPANG#");
        System.out.println("-- Data Pengguna --");
        System.out.println("Nama Lengkap : " + User.session.getString("namaLengkap"));
        System.out.println("Nomor Handphone : " + User.session.getString("handphone"));
        System.out.println("Email : " + User.session.getString("email"));
        System.out.println("Password : " + User.session.getString("password"));
        System.out.println("");
        //form
        System.out.println("-- Ubah Data Pengguna --");
        do {
            System.out.print("Nama Lengkap : ");
            nama = cin.nextLine();
            if (!u.isString(nama)) {
                System.out.print("Tidak Valid");
            }
        } while (!u.isString(nama));
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
        p = cin.nextLine();
        //call controller to parsing data into json
        us.editUser(User.session.getString("id"), User.session.getString("KTP"), nama, hp, email, p);
        System.out.println("-- Data Berhasil Diupdate, Berikut Data Terbaru --");
        System.out.println("Nomor KTP : " + User.session.getString("KTP"));
        System.out.println("Nama Lengkap : " + User.session.getString("namaLengkap"));
        System.out.println("Nomor Handphone : " + User.session.getString("handphone"));
        System.out.println("Email : " + User.session.getString("email"));
        System.out.println("Password : " + User.session.getString("password"));
        System.out.println("");
    }
}
