package com.mycompany.tubes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONObject;

import controller.ControllerAdmin;
import controller.ControllerTimeByRoute;

public class MenuTimeByRoute {
	private ControllerAdmin admin ;
	private ControllerTimeByRoute timeByRoute;
	private HashMap<Integer,IMenuTimeByRoute> subMenu;
	private HashMap<Integer,Integer> inputMapper;
	private String input ;
	
    public static JSONObject session;
    
    public MenuTimeByRoute(ControllerTimeByRoute c){
        admin = new ControllerAdmin();
        timeByRoute = c;
        subMenu = timeByRoute.getSubMenu();
    }
    

    public void index() throws FileNotFoundException, IOException, ParseException {
    	System.out.println("#KELOLA WAKTU PADA RUTE#");
    	this.menu(0);
    }
    
    public void menu(int input_pil) throws FileNotFoundException, IOException, ParseException {
        Scanner cin = new Scanner(System.in);
        int pil = 0;
        
		// Get Latest Menu List
        inputMapper   = timeByRoute.getInputMapper(input_pil);
         
    	int index=1;
    	for(int i=1; i<=subMenu.size(); i++) {
    		if(i!=input_pil) {
    			System.out.println(index+".  "+timeByRoute.getLabel(subMenu.get(inputMapper.get(index))));
    			index++;
    		}
        }
        System.out.println("99. Menu Utama");
    	
    	
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
            	admin.index();
                break;
            default:
            	if(inputMapper.containsKey(pil)){
                    System.out.println();
                    timeByRoute.subMenu(subMenu.get(inputMapper.get(pil)));
                    System.out.println();
            		this.menu(0);
            	}else {
            		System.out.println("Input Salah");
                    System.out.println();
            		this.menu(0);
            	}
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
    
}
