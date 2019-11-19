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

public class MenuTimeByRouteDelete implements IMenuTimeByRoute{
	private ControllerTimeByRoute timeByRoute;
	private HashMap<Integer,Integer> inputMapper;
	private String inputRoute ;

    public MenuTimeByRouteDelete(ControllerTimeByRoute c){
    	timeByRoute = c;
    }

	public String getLabel() {
		return "Delete Waktu Pada Rute";
	}

	public void index() throws FileNotFoundException, IOException {

        System.out.println("#DELETE WAKTU BERDASARKAN RUTE#");
        
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
	
		// DELETE DATA
		timeByRoute.deleteTimeByRoute(inputRoute);
		
    	System.out.println("-------------------------------------------------------------------------------------------------------");
    	System.out.println("Waktu Untuk Rute Berhasil Dihapus");
    	System.out.println("-------------------------------------------------------------------------------------------------------");

		// SHOW CONTENT WITH DATA
    }

	private boolean checkInputRoute(String input) {
        return timeByRoute.isExistByRoute(input);
	}

	@Override
	public void showContent(ArrayList<HashMap<String, String>> data){
    }
	
}
