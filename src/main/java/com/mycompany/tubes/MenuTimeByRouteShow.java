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
import java.util.List;
import java.util.Scanner;

import controller.ControllerTimeByRoute;

public class MenuTimeByRouteShow implements IMenuTimeByRoute{
	private ControllerTimeByRoute timeByRoute;
	private HashMap<Integer,Integer> inputMapper;
	private String inputRoute ;

    public MenuTimeByRouteShow(ControllerTimeByRoute c){
    	timeByRoute = c;
    }
    
	public String getLabel() {
		return "Lihat Waktu Pada Rute";
	}
	
	public void index() throws FileNotFoundException, IOException {
		
        System.out.println("#LIHAT WAKTU BERDASARKAN RUTE#");
        
        Scanner cin = new Scanner(System.in);
        System.out.print("Kode Rute : ");
        inputRoute = cin.nextLine();
		System.out.println();

		// GET DATA
		ArrayList<HashMap<String,String>>  data = timeByRoute.getDataTimeByRoute(inputRoute);
		
		// SHOW CONTENT WITH DATA
		showContent(data);
    }

	private boolean checkInputRoute(String input) {
        return timeByRoute.isExistByRoute(input);
	}
	
	int count;
	String waktu;
	String rute;
	@Override
	public void showContent(ArrayList<HashMap<String,String>>  data){
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
