package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import controller.ControllerCities;


public class ModelRute extends ModelJSON {

	private String kode_rute;
	private String keberangkatan;
	private String tujuan;
	private String business;
	private String premium;
	   File file = new File("DataJson/routes.json");
	    BufferedReader br;
	    static int indexUser = 0;
		
	
	
	public ModelRute(String kode_rute, String keberangkatan, String tujuan, String business, String premium) {
		super();
		this.kode_rute = kode_rute;
		this.keberangkatan = keberangkatan;
		this.tujuan = tujuan;
		this.business = business;
		this.premium = premium;
	}
	
    public ModelRute() {

    }
	
	

	  
    public String getKode_rute() {
		return kode_rute;
	}

	public void setKode_rute(String kode_rute) {
		this.kode_rute = kode_rute;
	}

	public String getKeberangkatan() {
		return keberangkatan;
	}

	public void setKeberangkatan(String keberangkatan) {
		this.keberangkatan = keberangkatan;
	}

	public String getTujuan() {
		return tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public int getMaxId() throws FileNotFoundException {
        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray currentTrain = new JSONArray(tokener);
        int max = 0;
        if(currentTrain.length()>0) {
            for (int i = 0; i < currentTrain.length(); i++) {
                JSONObject object = new JSONObject(currentTrain.get(i).toString());
                if (Integer.valueOf(object.getString("id")) > max) {
                    max = Integer.valueOf(object.getString("id"));
                }
            }
        }

        return max;
    }
    public void addRute() throws FileNotFoundException, IOException {
        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray currentTrain = new JSONArray(tokener);
    	JSONObject trainDetails = new JSONObject();
    	
   
    	
    
    		  
            trainDetails.put("kodeRute", getKode_rute());
            trainDetails.put("src", getKeberangkatan());
            trainDetails.put("dst", getTujuan());
            trainDetails.put("bisnis",getBusiness());
            trainDetails.put("premium", getPremium());
           
            System.out.println("idmu "+this.getMaxId());

            trainDetails.put("id", String.valueOf(this.getMaxId()+1));
            
             
            currentTrain.put(trainDetails);
            System.out.println(currentTrain.toString(2));
            this.writeToJson(currentTrain.toString(2),"DataJson/routes.json");
    		
  

      
    }
    
    public void showRute() throws FileNotFoundException, IOException {
    	

    	 br = new BufferedReader(new FileReader(file));
         JSONTokener tokener = new JSONTokener(br);
         JSONArray train = new JSONArray(tokener);
         System.out.println("No.		Keberangkatan		Tujuan		Kode_Rute		Business		Premium");
         for (int i = 0; i < train.length(); i++) {
             JSONObject object = new JSONObject(train.get(i).toString());
             System.out.println((i+1)+"		"+object.getString("src")+"		"+object.getString("dst")+"		"+object.getString("kodeRute")+"		"+object.getString("bisnis")+"		"+object.getString("premium"));

             }
    }

    public void editRute(String kodelama, String kodeBaru, String keberangkatan,String tujuan,String business,String premium) throws JSONException, IOException {

    	br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray currentTrain = new JSONArray(tokener);
        int cek = 0;
        for (int i = 0; i < currentTrain.length(); i++) {
            System.out.println("loop");

            JSONObject object = new JSONObject(currentTrain.get(i).toString());
            if (object.getString("kodeRute").equals(kodelama)) {
            	
                object.put("kodeRute", kodeBaru);
                object.put("src", keberangkatan);
                object.put("dst", tujuan);
                object.put("bisnis", business);
                object.put("premium", premium);
                currentTrain.put(i, object);

            	
            	
            }
        }
        
        this.writeToJson(currentTrain.toString(2),"DataJson/routes.json");

    }
    
    public void deleteRute(String kodelama) throws JSONException, IOException {
        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray currentRute = new JSONArray(tokener);
        int cek = 0;
        for (int i = 0; i < currentRute.length(); i++) {
            JSONObject object = new JSONObject(currentRute.get(i).toString());
            if (object.getString("kodeRute").equals(kodelama)) {
                currentRute.remove(i);
            	
            }
        }
        
        this.writeToJson(currentRute.toString(2),"DataJson/routes.json");

    }
    
    public JSONArray getDataRute() throws FileNotFoundException, IOException {
        JSONArray rute = readJson("DataJson/routes.json");
        return rute;

    }
    
    //cek train exist
    public boolean RuteExist(String kode) throws FileNotFoundException {
        JSONArray rute = readJson("DataJson/routes.json");
        boolean cek=false;
        for(int i=0;i<rute.length();i++){
            JSONObject o = rute.getJSONObject(i);
            if(kode.equals(o.get("kodeRute"))){                
                cek=true;               
            }
        }
        return cek;
    }




}
