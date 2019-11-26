package controller;

import com.mycompany.tubes.MenuReport;
import com.mycompany.tubes.MenuReportDaily;
import com.mycompany.tubes.MenuReportMonthly;
import com.mycompany.tubes.MenuReportYearly;
import com.mycompany.tubes.IMenuReport;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;

import model.ModelInvoices;
import model.ModelSchedules;
import model.ModelTrains;

public class ControllerReport{
	private MenuReport m;
	public HashMap<Integer,IMenuReport> subMenu;
	private ModelTrains invoice;
	private ModelSchedules searchSchedule;
	
	public ControllerReport() throws FileNotFoundException{
		invoice = new ModelTrains();
		searchSchedule = new ModelSchedules();
		
		subMenu = new HashMap<Integer,IMenuReport>();
		subMenu.put(1, new MenuReportDaily(this));
		subMenu.put(2, new MenuReportMonthly(this));
		subMenu.put(3, new MenuReportYearly(this));
		
		m = new MenuReport(this);
	}
	
    public void index() throws FileNotFoundException, IOException, ParseException {
        m.index();
    }
    
	public void subMenu(IMenuReport subMenu) throws FileNotFoundException, IOException {
		subMenu.index();
	}

    public String getLabel(IMenuReport subMenu) throws FileNotFoundException, IOException {
    	return subMenu.getLabel();
    }
    
    public ArrayList<HashMap<String,String>> getDataByDate(IMenuReport subMenu, String input) throws FileNotFoundException, IOException {
    	
    	ArrayList<HashMap<String,String>> array_raw = invoice.getDataByDate(input);
    	HashMap<String,HashMap<String,String>>  data_final = new HashMap<String,HashMap<String,String>>();
    	ArrayList array_final = new ArrayList();
    	
    	
    	array_raw.forEach((data) -> {
        	String kodeKereta = "";
        	String kereta = "";

			try {
				kodeKereta = searchSchedule.getKodeKeretaByKodeJadwalTanggal(data.get("kodeJadwal"),data.get("tanggal"));
	        	kereta = searchSchedule.getKeretaByKodeKereta(kodeKereta);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
        	data.put("kodeJadwal",kereta);
    		String primary_key = data.get("tanggal")+data.get("kodeJadwal");
    		
    		if(data_final.containsKey(primary_key) ){
    			Long newTotal =  Long.parseLong(data.get("harga")) +  Long.parseLong(data_final.get(primary_key).get("harga"));
    			data.put("harga",newTotal.toString());
    		}
    		
    		data_final.put(primary_key,data);
    	}); 
    	
    	for (Map.Entry<String,HashMap<String,String>> entry : data_final.entrySet())  {
    		HashMap map = new HashMap();
    		map.put("tanggal",entry.getValue().get("tanggal"));
    		map.put("kodeJadwal",entry.getValue().get("kodeJadwal"));
    		map.put("harga",entry.getValue().get("harga"));
    		array_final.add(map);
    		
    	}
    	
    	Collections.sort(array_final, new Comparator<HashMap< String,String >>() {
            @Override
            public int compare(HashMap<String, String> lhs,
                    HashMap<String, String> rhs) {
                String firstValue = lhs.get("kodeJadwal");
                String secondValue = rhs.get("kodeJadwal");
                return firstValue.compareTo(secondValue);
            }
        });   

    	return array_final;
    }
    public ArrayList<HashMap<String,String>> getDataByMonth(IMenuReport subMenu, String input) throws FileNotFoundException, IOException {
    	
    	ArrayList<HashMap<String,String>> array_raw = invoice.getDataByMonth(input);
    	HashMap<String,HashMap<String,String>>  data_final = new HashMap<String,HashMap<String,String>>();
    	ArrayList array_final = new ArrayList();
    	//System.out.println(Arrays.toString(array_raw.toArray()));
    	
    	array_raw.forEach((data) -> {
    		String primary_key = data.get("tanggal");
    		
    		if(data_final.containsKey(primary_key) ){
    			Long newTotal =  Long.parseLong(data.get("harga")) +  Long.parseLong(data_final.get(primary_key).get("harga"));
    			data.put("harga",newTotal.toString());
    		}
    		
    		data_final.put(primary_key,data);
    	}); 
    	
    	for (Map.Entry<String,HashMap<String,String>> entry : data_final.entrySet())  {
    		HashMap map = new HashMap();
    		map.put("tanggal",entry.getValue().get("tanggal"));
    		map.put("kodeJadwal",entry.getValue().get("kodeJadwal"));
    		map.put("harga",entry.getValue().get("harga"));
    		array_final.add(map);
    		
    	}

        Collections.sort(array_final, new Comparator<HashMap< String,String >>() {
            @Override
            public int compare(HashMap<String, String> lhs,
                    HashMap<String, String> rhs) {
                String firstValue = lhs.get("tanggal");
                String secondValue = rhs.get("tanggal");
                return firstValue.compareTo(secondValue);
            }
        });

        return array_final;
    }
    public ArrayList<HashMap<String,String>> getDataByYear(IMenuReport subMenu, String input) throws FileNotFoundException, IOException {
    	
    	ArrayList<HashMap<String,String>> array_raw = invoice.getDataByYear(input);
    	HashMap<String,HashMap<String,String>>  data_final = new HashMap<String,HashMap<String,String>>();
    	ArrayList array_final = new ArrayList();
    	
    	
    	array_raw.forEach((data) -> {
    		String primary_key = data.get("tanggal");
    		
    		if(data_final.containsKey(primary_key) ){
    			Long newTotal =  Long.parseLong(data.get("harga")) +  Long.parseLong(data_final.get(primary_key).get("harga"));
    			data.put("harga",newTotal.toString());
    		}
    		
    		data_final.put(primary_key,data);
    	}); 
    	
    	for (Map.Entry<String,HashMap<String,String>> entry : data_final.entrySet())  {
    		HashMap map = new HashMap();
    		map.put("tanggal",entry.getValue().get("tanggal"));
    		map.put("kodeJadwal",entry.getValue().get("kodeJadwal"));
    		map.put("harga",entry.getValue().get("harga"));
    		array_final.add(map);
    		
    	}

        Collections.sort(array_final, new Comparator<HashMap< String,String >>() {
            @Override
            public int compare(HashMap<String, String> lhs,
                    HashMap<String, String> rhs) {
                String firstValue = lhs.get("tanggal");
                String secondValue = rhs.get("tanggal");
                return firstValue.compareTo(secondValue);
            }
        });   	

        return array_final;
    }

	public HashMap<Integer, IMenuReport> getSubMenu() {
		return subMenu;
	}

	int count;
	public HashMap<Integer, Integer> getInputMapper(int input_pil) {
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		count = 1;
		for(int i=1; i<=subMenu.size(); i++) {
	        if(i!=input_pil) {
	    		map.put(count,i);
	    		count++;
			}
        }
		return map;
	}



}
