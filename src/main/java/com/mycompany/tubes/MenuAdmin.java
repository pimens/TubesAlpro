/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author pmen
 */
public class MenuAdmin {
    public static JSONObject session;
    public void menu() {
        Scanner cin = new Scanner(System.in);
        int pil;
        System.out.println("#Menu Admin#");
        System.out.println("Welcome, Admin");
        System.out.println("1. Booking Tiket ");
        System.out.println("2. Kelola Profil ;");
        System.out.println("3. History ;");
        System.out.println("0. Logout ;");
        cin.nextInt();
        

    }
}
