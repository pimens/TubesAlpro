package com.mycompany.tubes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;
import org.json.JSONException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Login {
    public static void login() throws FileNotFoundException {
        Scanner cin = new Scanner(System.in);
        String email = "", pass = "";
        System.out.println("Email :");
        email = cin.next();
        System.out.print("Password ;");
        pass = cin.next();
        Data d = new Data();
        JSONObject object = d.getDataById(email, pass);
        if (object.getString("id").equals("gagal")) {
            System.out.println("gagal!!");
        } else {
             if (object.getString("rule").equals("0")) {
                 User.session = object;
                 User u = new User();
                 u.menu();
             }else{
                 Admin.session = object;
                 Admin a = new Admin();
                 a.menu();
             }
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        Scanner cin = new Scanner(System.in);
        int pil;
        do {
            System.out.println("Menu : ");
            System.out.println("1. Login ");
            System.out.print("2. Register ;");
            pil = cin.nextInt();           
            if(pil>2 || pil<=0){
                System.out.println("Menu tidak valid");
            }            
        }while(pil>2 || pil<=0);
        login();
    
    }
}
