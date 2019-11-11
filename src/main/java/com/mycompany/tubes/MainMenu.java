
package com.mycompany.tubes;

import controller.User;
import controller.Home;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONObject;
//controller utama
public class MainMenu {
    public void index() throws FileNotFoundException, IOException{
        Scanner cin = new Scanner(System.in);
        Home u = new Home();
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
        if(pil==1){
            u.login();
        }else{
            u.register();
        }
    }
    public void login() throws FileNotFoundException, IOException{
        Scanner cin = new Scanner(System.in);
        String email = "", pass = "";
        System.out.println("Email :");
        email = cin.next();
        System.out.print("Password ;");
        pass = cin.next();
        Home conUtama = new Home();
        JSONObject object = conUtama.doLogin(email, pass);
        if (object.getString("id").equals("gagal")) {
              System.out.println("gagal!!");
        } else {
             if (object.getString("rule").equals("0")) {
                User u = new User();
                User.session = object;
                u.index();                
             }else{
                //cont admin
             }
        }
    }
    public void registerUser() throws FileNotFoundException, IOException {
        Scanner cin = new Scanner(System.in);
        String ktp, nama, alamat, email, p, p2;
        System.out.println("#Register#");
        System.out.println("Nomor KTP : ");
        ktp = cin.nextLine();
        System.out.println("Nama Lengkap : ");
        nama = cin.nextLine();
        System.out.println("Nomor Handphone : ");
        alamat = cin.nextLine();
        System.out.println("Email : ");
        email = cin.nextLine();
        System.out.println("Password : ");
        p = cin.nextLine();
        System.out.println("Re-Password : ");
        p2 = cin.nextLine();
        Home u = new Home();
        u.addUser(ktp, nama, alamat, email,p);
    }
    
}
