/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import static com.mycompany.tubes.Data.indexUser;
import java.util.Scanner;
import org.json.JSONObject;

public class User {

    public static JSONObject session;

    public void menu() {
        Scanner cin = new Scanner(System.in);
        int pil;
        System.out.println("#Menu Pengguna#");
        System.out.println("Welcome," + session.getString("nama".toString()));
        System.out.println("1. Booking Tiket ");
        System.out.println("2. Kelola Profil ;");
        System.out.println("3. History ;");
        System.out.println("0. Logout ;");
        cin.nextInt();
        

    }
}
