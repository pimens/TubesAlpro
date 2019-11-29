package com.mycompany.tubes;

import controller.ControllerAdmin;
import controller.User;
import controller.Home;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import org.json.JSONObject;
//controller utama

public class MainMenu {

    Home u;
    Scanner cin ;
    public MainMenu() {
        u = new Home();
        cin = new Scanner(System.in);
    }

    public void index() throws FileNotFoundException, IOException, ParseException {
        int pil=-1;
        String input;
        
        System.out.println("Menu : ");
        System.out.println("1. Login ");
        System.out.println("2. Register ");
        do {
            System.out.print("Pilihan  :");
            input = cin.nextLine();
        	if(!checkInput(input)) {
        		System.out.println("Input Salah");
        	}else {
        		pil = Integer.parseInt(input);
        	}
        }
        while(!checkInput(input));
        
        
        switch (pil) {
            case 99:
            	index();
                break;
            case 1:
            	u.login();
            	break;
            case 2:
            	u.register();
            	break;
            default:
            	System.out.println("Input Salah");
                System.out.println();
                index();
                break;
        }
    }
    private boolean checkInput(String input) {
        try {
            Integer.valueOf(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
	}
    
    public void login() throws FileNotFoundException, IOException, ParseException {
       
        String email = "", pass = "";
        System.out.println("#Login Sistem#");
        System.out.print("Email :");
        email = cin.next();
        System.out.print("Password :");
        pass = cin.next();
        Home conUtama = new Home();
        JSONObject object = conUtama.doLogin(email, pass);
        if (object.getString("id").equals("gagal")) {
            System.out.println("gagal!!");
            this.login();
        } else {
            if (object.getString("rule").equals("0")) {
                User u = new User();
                User.session = object;
                u.index();
            } else {
                ControllerAdmin c = new ControllerAdmin();
                c.index();
            }
        }
    }

    public void registerUser() throws FileNotFoundException, IOException, ParseException {
        Home u = new Home();
        String ktp, nama, hp, email, p, p2;
        System.out.println("#Register Sistem#");
        do {
            System.out.print("Nomor KTP : ");
            ktp = cin.nextLine();
            if (!ktp.matches("^[0-9]*$") || ktp.length() != 16) {
                System.out.println("Tidak Valid ");
            }
        } while (!ktp.matches("^[0-9]*$") || ktp.length() != 16);
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
        p = cin.nextLine();
        do {
            System.out.print("Re-Password : ");
            p2 = cin.nextLine();
            if (!u.isEqual(p, p2)) {
                System.out.println("Tidak Sama");
            }
        } while (!u.isEqual(p, p2));
        u.addUser(ktp, nama, hp, email, p);
    }

}
