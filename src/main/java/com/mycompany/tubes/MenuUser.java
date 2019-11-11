/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import controller.User;
import java.util.Scanner;

public class MenuUser {  

    public void index() {
        Scanner cin = new Scanner(System.in);
        int pil;
        System.out.println("#Menu Pengguna#");
        System.out.println("Welcome, " + User.session.getString("nama".toString()));
        System.out.println("1. Booking Tiket ");
        System.out.println("2. Kelola Profil ;");
        System.out.println("3. History ;");
        System.out.println("0. Logout ;");
        cin.nextInt();
        
    }
    
}
