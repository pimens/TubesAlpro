package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class ModelInvoices extends ModelJSON {
	private JSONArray invoice;
	
	public ModelInvoices() throws FileNotFoundException{
		try {
			invoice = readJson("DataJson/invoices.json");
		}catch(FileNotFoundException e){
			invoice = new JSONArray();
		}
	}

    public ArrayList getDataByTanggal(String tanggal) throws FileNotFoundException {
        JSONObject object = null;
        ArrayList array = new ArrayList();
        HashMap map = new HashMap();
        
        int i = 0;
        int cek = 0;
        for (i = 0; i < invoice.length(); i++) {
            object = new JSONObject(invoice.get(i).toString());
            if (object.getString("tanggal").equals(tanggal)) {

        		map = new HashMap();
        		map.put("id",object.getString("id"));
        		map.put("tanggal",object.getString("tanggal"));
        		map.put("kodeJadwal",object.getString("kodeJadwal"));
        		map.put("totalPembayaran",object.getString("totalPembayaran"));
        		array.add(map);
            }
        }
        return array;
    }
    public ArrayList getDataByBulan(String bulan) throws FileNotFoundException {
        JSONObject object = null;
        ArrayList array = new ArrayList();
        HashMap map = new HashMap();
        String dateString;
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
        
        for (int i = 0; i < invoice.length(); i++) {
            object = new JSONObject(invoice.get(i).toString());

            try {
                date = (Date) sdf.parse(object.getString("tanggal"));

                dateString =  dateFormat.format(date);  
            } catch (ParseException e) {
				dateString = "01-1970";
            }
            
            if (dateString.equals(bulan)) {

        		map = new HashMap();
        		map.put("id",object.getString("id"));
        		map.put("tanggal",object.getString("tanggal"));
        		map.put("kodeJadwal",object.getString("kodeJadwal"));
        		map.put("totalPembayaran",object.getString("totalPembayaran"));
        		array.add(map);
            }
        }
        return array;
    }
    public ArrayList getDataByTahun(String tahun) throws FileNotFoundException {
        JSONObject object = null;
        ArrayList array = new ArrayList();
        HashMap map = new HashMap();
        String dateString;
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        
        for (int i = 0; i < invoice.length(); i++) {
            object = new JSONObject(invoice.get(i).toString());
            
            try {
                date = (Date) sdf.parse(object.getString("tanggal"));
                
                dateString =  dateFormat.format(date);  
            } catch (ParseException e) {
                date = new Date(1970,01,01);
                
				dateString = "1970";
            }
            
            if (dateString.equals(tahun)) {

        		map = new HashMap();
        		map.put("id",object.getString("id"));

                dateString =  monthFormat.format(date);  
                    
        		map.put("tanggal",dateString);
        		map.put("kodeJadwal",object.getString("kodeJadwal"));
        		map.put("totalPembayaran",object.getString("totalPembayaran"));
        		array.add(map);
            }
        }
        return array;
    }

    public ArrayList getDataByDate(String date) throws FileNotFoundException {
        JSONArray invoice = readJson("DataJson/invoice.json");
        JSONObject object = null;
        ArrayList array = new ArrayList();
        HashMap map = new HashMap();
        
        int i = 0;
        int cek = 0;
        for (i = 0; i < invoice.length(); i++) {
            object = new JSONObject(invoice.get(i).toString());
            if (object.getString("tanggal").equals(date)) {

        		map = new HashMap();
        		map.put("id",object.getString("id"));
        		map.put("tanggal",object.getString("tanggal"));
        		map.put("kodeJadwal",object.getString("kodeJadwal"));
        		map.put("totalPembayaran",object.getString("totalPembayaran"));
        		array.add(map);
            }
        }
        return array;
    }
    public ArrayList getDataByMonth(String month) throws FileNotFoundException {
        JSONArray invoice = readJson("DataJson/invoice.json");
        JSONObject object = null;
        ArrayList array = new ArrayList();
        HashMap map = new HashMap();
        String dateString;
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
        
        for (int i = 0; i < invoice.length(); i++) {
            object = new JSONObject(invoice.get(i).toString());

            try {
                date = (Date) sdf.parse(object.getString("tanggal"));

                dateString =  dateFormat.format(date);  
            } catch (ParseException e) {
				dateString = "01-1970";
            }
            
            if (dateString.equals(month)) {

        		map = new HashMap();
        		map.put("id",object.getString("id"));
        		map.put("tanggal",object.getString("tanggal"));
        		map.put("kodeJadwal",object.getString("kodeJadwal"));
        		map.put("totalPembayaran",object.getString("totalPembayaran"));
        		array.add(map);
            }
        }
        return array;
    }
    public ArrayList getDataByYear(String year) throws FileNotFoundException {
        JSONArray invoice = readJson("DataJson/invoice.json");
        JSONObject object = null;
        ArrayList array = new ArrayList();
        HashMap map = new HashMap();
        String dateString;
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        
        for (int i = 0; i < invoice.length(); i++) {
            object = new JSONObject(invoice.get(i).toString());
            
            try {
                date = (Date) sdf.parse(object.getString("tanggal"));
                
                dateString =  dateFormat.format(date);  
            } catch (ParseException e) {
                date = new Date(1970,01,01);
                
				dateString = "1970";
            }
            
            if (dateString.equals(year)) {

        		map = new HashMap();
        		map.put("id",object.getString("id"));

                dateString =  monthFormat.format(date);  
                    
        		map.put("tanggal",dateString);
        		map.put("kodeJadwal",object.getString("kodeJadwal"));
        		map.put("totalPembayaran",object.getString("totalPembayaran"));
        		array.add(map);
            }
        }
        return array;
    }

}
