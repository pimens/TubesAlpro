package com.mycompany.tubes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import controller.ControllerReport;

public class MenuReportYearly implements IMenuReport{
	private ControllerReport reportService;

    public MenuReportYearly(ControllerReport c){
        reportService = c;
    }
    
	public String getLabel() {
		return "Laporan Tahunan";
	}
	
	public void index() throws FileNotFoundException, IOException {
        
        System.out.println("#LAPORAN TAHUNAN PEMASUKAN#");
        System.out.print("Masukkan tahun pencarian : ");
        
        Scanner cin = new Scanner(System.in);
        String input ;
        do {
        	input = cin.nextLine();
        	if(!checkInput(input)) {
        		System.out.println("Input Salah");
        		System.out.print("Masukkan tahun pencarian : ");
        	}
        }
        while(!checkInput(input));
		System.out.println();
		
		// GET DATA
		ArrayList<HashMap<String,String>>  data = reportService.getDataByYear(this, input);
		
		// SHOW CONTENT WITH DATA
		showContent(data);
    }

    private boolean checkInput(String input) {
        DateFormat sdf = new SimpleDateFormat("yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(input);
        } catch (ParseException e) {
            return false;
        }
        return true;
	}
    
    DecimalFormat formatter = new DecimalFormat("#,###,###,###,###");
	Long sum;
	Integer count;
	@Override
	public void showContent(ArrayList<HashMap<String,String>>  list) throws FileNotFoundException, IOException {
		System.out.println();
    	System.out.println("Data Pemasukan Tahunan");
    	System.out.println("-------------------------------------------------------------------------------------------------------");
    	System.out.println("No  \tBulan \tJumlah Pendapatan");

        sum = (long) 0;
        count = 0;
        list.forEach((data) -> {
        	Long totalPembayaran = Long.parseLong(data.get("totalPembayaran"));
        	sum = sum + totalPembayaran;
        	count++;
    		System.out.println(count+"\t"+data.get("tanggal")+"   \t"+formatter.format(totalPembayaran));
    	});
    	System.out.println();
    	System.out.println("Total Masukan Tahunan : "+formatter.format(sum));
    	System.out.println("-------------------------------------------------------------------------------------------------------");

		
	}
}
