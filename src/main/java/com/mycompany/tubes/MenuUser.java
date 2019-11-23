/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import controller.User;
import java.io.IOException;
import java.util.Scanner;

public class MenuUser {  

    public void index() throws IOException {
        Scanner cin = new Scanner(System.in);
        int pil = 0;
        User u = new User();
        System.out.println("#Menu Pengguna#");
        System.out.println("Welcome, " + User.session.getString("namaLengkap".toString()));
        System.out.println("1. Booking Tiket ");
        System.out.println("2. Kelola Profil ;");
        System.out.println("3. History ;");
        System.out.println("0. Logout ;");
        System.out.print("Pilihan :");
        pil=cin.nextInt();
        switch (pil) {
            case 1:
                System.out.println("Book");
                break;
            case 2:
                u.kelolaUser();
                this.index();
                break;
            case 3:
                System.out.println("History");
                break;
            default:
                System.out.println("Log");
                break;
        }
    }
    
}
