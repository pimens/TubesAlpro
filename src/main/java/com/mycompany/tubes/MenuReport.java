package com.mycompany.tubes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.ControllerAdmin;
import controller.ControllerReport;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

/**
 *
 * @author radenfajrus
 */
public class MenuReport {
	private ControllerAdmin admin ;
	private ControllerReport reportService;
	private HashMap<Integer,IMenuReport> subMenu;
	private HashMap<Integer,Integer> inputMapper;
	private String input ;
    
    public MenuReport(ControllerReport c){
        admin = new ControllerAdmin();
        reportService = c;
        subMenu    = reportService.getSubMenu();
    }

    // HEADER MENU
    public void index() throws FileNotFoundException, IOException, ParseException {
    	System.out.println("#LIHAT PEMASUKAN#");
    	this.menu(0);
    }
    public void menu(int input_pil) throws FileNotFoundException, IOException, ParseException {
        Scanner cin = new Scanner(System.in);
        int pil = 0;
        
        // Get Latest Menu List
        inputMapper   = reportService.getInputMapper(input_pil);
         
    	int index=1;
    	for(int i=1; i<=subMenu.size(); i++) {
    		if(i!=input_pil) {
    			System.out.println(index+".  "+reportService.getLabel(subMenu.get(inputMapper.get(index))));
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
            		reportService.subMenu(subMenu.get(inputMapper.get(pil)));
            		this.menu(inputMapper.get(pil));
            	}else {
            		System.out.println("Input Salah");
            		this.menu(input_pil);
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

