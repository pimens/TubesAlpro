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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
        
		// SHOW CONTENT
		ArrayList<HashMap<String,String>>  data = timeByRoute.getDataTimeByRoute(inputRoute);
		
		List array = new ArrayList<String>();
		
		try {
			array = new ArrayList<String>(Arrays.asList(data.get(0).get("kodeWaktu").split("\\s*,\\s*")));
		}catch  (IndexOutOfBoundsException e)  {
		}
		
    	// PRINT INIT KODE WAKTU
    	showContent(data);
    	
    	// ADD DATA
    	count = 1;
    	for(int i=0;i<array.size();i++) {
    		System.out.println("Time "+count+" : "+array.get(i));
    		count++;
    	}
    	
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
    	
    	Collections.sort(array);
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

	int count;
	String waktu;
	String rute;
	@Override
	public void showContent(ArrayList<HashMap<String,String>>  data){
		System.out.println();
		System.out.println("Waktu Available Untuk Rute");
    	System.out.println("-------------------------------------------------------------------------------------------------------");
    	System.out.println("No   \tKode Waktu Rute \tKode Rute  \tWaktu Tersedia Rute");
    	
        count = 1;
        data.forEach((row) -> {
        	List<String> kodeWaktu = new ArrayList<String>();
        	kodeWaktu = (List<String>) Arrays.asList(row.get("kodeWaktu").split("\\s*,\\s*"));
        	
        	rute = timeByRoute.getRuteByKodeRute(row.get("kodeRute"));

        	for(int i=0;i<kodeWaktu.size();i++) {
        		try {
					waktu = timeByRoute.getWaktuByKodeWaktu(kodeWaktu.get(i));
				} catch (IOException e) {
					e.printStackTrace();
				}
        				
        		if(i==0) {
        			System.out.println(count+"\t"+row.get("kodeWaktuRute")+"                 \t"+rute+"\t\t - "+waktu);
        		}else {
        			System.out.println("   "+"\t"+"     "                 +"                 \t"+"        "         +"   \t - "+waktu);
            		}
        	}
        	count++;
    		});
    	
			
    	
    	System.out.println("-------------------------------------------------------------------------------------------------------");
	}




}
