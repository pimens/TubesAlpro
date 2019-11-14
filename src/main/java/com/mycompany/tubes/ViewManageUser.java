/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import controller.Home;
import controller.User;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author pmen
 */
public class ViewManageUser {

    public void index() throws IOException {
        Scanner cin = new Scanner(System.in);
        String nama = null, hp = null, email = null, p = null;
        System.out.println("#KELOLA PROFILE BY PENUMPANG#");
        System.out.println("-- Data Pengguna --");
        System.out.println("Nama Lengkap : " + User.session.getString("namaLengkap"));
        System.out.println("Nomor Handphone : " + User.session.getString("handphone"));
        System.out.println("Email : " + User.session.getString("email"));
        System.out.println("Password : " + User.session.getString("password"));
        System.out.println("");
        //form
        System.out.println("-- Ubah Data Pengguna --");
        Home u = new Home();
        System.out.println("Nama Lengkap : ");
        do {
            nama = cin.nextLine();
            if (!u.isString(nama)) {
                System.out.println("Tidak Valid");
            }
        } while (!u.isString(nama));
        System.out.println("Nomor Handphone : ");
        do {
            hp = cin.nextLine();
            if (!u.isNumber(hp, 11, 12)) {
                System.out.println("Tidak Valid");
            }
        } while (!u.isNumber(hp, 11, 12));
        System.out.println("Email : ");
        email = cin.nextLine();
        System.out.println("Password : ");
        p = cin.nextLine();
        //call controller to parsing data into json
        User us = new User();
        us.editUser(User.session.getString("KTP"), nama, hp, email, p);
        System.out.println("-- Data Berhasil Diupdate, Berikut Data Terbaru --");
        System.out.println("Nomor KTP : " + User.session.getString("KTP"));
        System.out.println("Nama Lengkap : " + User.session.getString("namaLengkap"));
        System.out.println("Nomor Handphone : " + User.session.getString("handphone"));
        System.out.println("Email : " + User.session.getString("email"));
        System.out.println("Password : " + User.session.getString("password"));
        System.out.println("");
        us.index();
    }
}
