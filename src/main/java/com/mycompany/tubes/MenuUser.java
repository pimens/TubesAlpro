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
        pil=cin.nextInt();
        if(pil==2){
           u.kelolaUser();           
        }
    }
    
}
