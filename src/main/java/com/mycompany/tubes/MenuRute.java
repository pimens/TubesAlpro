package com.mycompany.tubes;

import controller.ControllerAdmin;
import controller.ControllerCities;
import controller.ControllerRute;
import controller.User;
import controller.ControllerTrain;
import controller.Home;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

//controller utama

public class MenuRute {

    ControllerRute dkc=new ControllerRute();
    
    public void index() throws FileNotFoundException, IOException, ParseException {
        Scanner cin = new Scanner(System.in);
        int pil;
        
        System.out.println(" ");        
        System.out.println("#KELOLA DATA RUTE#");
        System.out.println("1. Tambah Data Rute ");
        System.out.println("2. Lihat Data Rute ");
        System.out.println("3. Edit Data Rute ");
        System.out.println("4. Delete Data Rute ");
        System.out.println("99. Menu Utama ");

        System.out.print("Pilihan  :");
        pil=cin.nextInt();
        if (pil == 1) {
            addRuteMenu();
           
        }  if (pil==2) {
      	  showRuteMenu();
        } if (pil==3) {
      	  editRuteMenu();
        }if (pil==4) {
      	  deleteRuteMenu();
        }else {
           	MenuAdmin ma= new MenuAdmin();
        	ma.index();
        }
    }

    public void addRuteMenu() throws FileNotFoundException, IOException, ParseException {
        Scanner cin = new Scanner(System.in);
        String input = "";
        String kode_rute = "";
        String keberangkatan = "";
        String tujuan = "";
        String business = "";
        String premium = "";

        
        System.out.print("tambah rute :");
        input = cin.nextLine();
        if(input.contains(" ")){
        	
           System.out.print("proses.....mohon tunggu");

           String[] datarute = input.split(" (?=(?:[^\']*\'[^\']*\')*[^\']*$)");

        
           
           keberangkatan= datarute[0];
           tujuan=datarute[1];
           business=datarute[2];
           premium=datarute[3];
           ControllerCities cc= new ControllerCities();
           
           System.out.println("Kota"+keberangkatan);
           String kk= cc.getKodeCity(keberangkatan);
           String tj=cc.getKodeCity(tujuan);
           
           
       	if(!kk.equals(" ")&&!tj.equals(" ")) {
       		
       			kode_rute=kk+"-"+tj;
        	   
        	   if (dkc.RuteExist(kode_rute)) {
                   System.out.println("Rute Sudah Ada");
               } else {
            	   dkc.addRute(kode_rute,keberangkatan,tujuan,business,premium);
                   index();
               }
                 
           }else {
               System.out.println("Kota Belum Terdaftar");
               index();
           }
           
     
           

        }else {
            System.out.print("(Input Format Tidak Sesuai");
            index();

        }
        
        
        
        
    }

    public void showRuteMenu() throws FileNotFoundException, IOException, ParseException {
    	
    	
        System.out.println("No.		Keberangkatan		Tujuan		Kode_Rute		Business		Premium");
        JSONArray train = dkc.getDataRute();
        for (int i = 0; i < train.length(); i++) {
            JSONObject object = new JSONObject(train.get(i).toString());
            System.out.println((i+1)+"		"+object.getString("src")+"			"+object.getString("dst")+"		"+object.getString("kodeRute")+"			"+object.getString("bisnis")+"			"+object.getString("premium"));

            }
    	index();
       }
    
    public void editRuteMenu() throws FileNotFoundException, IOException, ParseException {
    	
        Scanner cin = new Scanner(System.in);
        String input = "";
        String kode_rute = "";
        String keberangkatan = "";
        String tujuan = "";
        String business = "";
        String premium = "";
        
        System.out.print("Edit Rute :");
        input = cin.nextLine();
        
        if(input.contains("EDIT_")) {
            System.out.print("Kode Rute :");
            kode_rute = cin.nextLine();
            System.out.print("Keberangkatan :");
            keberangkatan = cin.nextLine();
            System.out.print("Tujuan :");
            tujuan = cin.nextLine();
            System.out.print("Business :");
            business = cin.nextLine();
            System.out.print("Premium :");
            premium = cin.nextLine();
            
            
           
                String[] kotalama = input.split("_",2);
                dkc.editRute(kotalama[1], kode_rute, keberangkatan,tujuan,business,premium);
                index();
          
 
        }else {
            System.out.println("format salah");
            index();

        }


    }
    public void deleteRuteMenu() throws FileNotFoundException, IOException, ParseException {
    	
        Scanner cin = new Scanner(System.in);
        String input = "";

        System.out.print("Hapus Rute :");
        input = cin.nextLine();
        
        
        if(input.contains("DELETE_")) {
  
            String[] kotalama = input.split("_",2);
            dkc.deleteRute(kotalama[1]);
            index();
        }else {
            System.out.println("format salah");
            index();

        }


    }

}
