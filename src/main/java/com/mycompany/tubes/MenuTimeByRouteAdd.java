package com.mycompany.tubes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import controller.ControllerTimeByRoute;

public class MenuTimeByRouteAdd implements IMenuTimeByRoute{
	private ControllerTimeByRoute timeByRoute;
	private HashMap<Integer,Integer> inputMapper;
	private String inputRoute ;
	private String inputTime ;

    public MenuTimeByRouteAdd(ControllerTimeByRoute c){
    	timeByRoute = c;
    }
    
	public String getLabel() {
		return "Tambah Waktu Pada Rute";
	}
	
	public void index() throws FileNotFoundException, IOException {
		
        System.out.println("#KELOLA WAKTU BERDASARKAN RUTE#");
        
        Scanner cin = new Scanner(System.in);
        do {
        	System.out.print("Kode Rute : ");
        	inputRoute = cin.nextLine();
        	if(!checkInputRoute(inputRoute)) {
        		System.out.println("Kode Rute tidak ditemukan");
        	}
        }
        while(!checkInputRoute(inputRoute));
		System.out.println();
		System.out.println("Waktu Available Untuk Rute");
    	System.out.println("-------------------------------------------------------------------------------------------------------");
	
		// INPUT DATA
    	int count = 1;
    	ArrayList array = new ArrayList();
    	do {
    		System.out.print("Time "+count+" : ");
        	inputTime = cin.nextLine();
        	if(!inputTime.equals("99")){
	        	if(!checkInputTime(inputTime)) {
	        		System.out.println("Kode Waktu tidak ditemukan");
	        	}
	        	else if(array.contains(inputTime)) {
	        		System.out.println("Kode Waktu sudah diinput sebelumnya");
	        	}else {
	        		array.add(inputTime);
	        		count++;
	        	}
        	}
        }
        while(!inputTime.equals("99"));
    	
		// ADD DATA
    	HashMap<String,String> map = new HashMap<String,String>();
    	map.put("kodeRute",inputRoute);
    	map.put("kodeWaktu",String.join(",", array));

		timeByRoute.addTimeByRoute(map);
		
    	System.out.println("-------------------------------------------------------------------------------------------------------");
    	System.out.println("Waktu Untuk Rute Berhasil Ditambahkan");
    	System.out.println("-------------------------------------------------------------------------------------------------------");

		// SHOW CONTENT WITH DATA
    }

    private boolean checkInputRoute(String input) {
        return timeByRoute.isExistByRoute(input);
	}
    private boolean checkInputTime(String input) {
        return timeByRoute.isExistByTime(input);
	}

	@Override
	public void showContent(ArrayList<HashMap<String, String>> data){
    }




}
