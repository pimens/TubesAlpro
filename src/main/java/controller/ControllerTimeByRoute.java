package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.tubes.IMenuTimeByRoute;
import com.mycompany.tubes.MenuTimeByRoute;
import com.mycompany.tubes.MenuTimeByRouteAdd;
import com.mycompany.tubes.MenuTimeByRouteShow;
import com.mycompany.tubes.MenuTimeByRouteDelete;

import model.ModelRoutes;
import model.ModelTimeByRoutes;
import model.ModelTimes;

public class ControllerTimeByRoute {
	private MenuTimeByRoute m;
	public HashMap<Integer,IMenuTimeByRoute> subMenu;
	ModelTimes times;
	ModelRoutes routes;
	ModelTimeByRoutes timeByRoutes;

	public ControllerTimeByRoute() throws FileNotFoundException{
		times = new ModelTimes();
		routes = new ModelRoutes();
		timeByRoutes = new ModelTimeByRoutes();
		
		subMenu = new HashMap<Integer,IMenuTimeByRoute>();
		subMenu.put(1, new MenuTimeByRouteAdd(this));
		subMenu.put(2, new MenuTimeByRouteShow(this));
		subMenu.put(3, new MenuTimeByRouteDelete(this));
		
		m = new MenuTimeByRoute(this);
	}
	
    public void index() throws FileNotFoundException, IOException, ParseException {
        m.index();
    }
    
	public void subMenu(IMenuTimeByRoute subMenu) throws FileNotFoundException, IOException, ParseException {
		subMenu.index();
	}

    public String getLabel(IMenuTimeByRoute subMenu) throws FileNotFoundException, IOException {
    	return subMenu.getLabel();
    }
    
    public boolean isExistByTime(String input) {
    	return times.isExistByKodeWaktu(input);
    }
    public boolean isExistByRoute(String input) {
    	return routes.isExistByKodeRute(input);
    }
    public boolean isExistByTimeByRoute(String input) {
    	return timeByRoutes.isExistByKodeWaktu(input);
    }

    public void addTimeByRoute(HashMap<String, String> input) throws FileNotFoundException, IOException {
    	String kodeRute = input.get("kodeRute");
    	String id = routes.getIdByKodeRute(kodeRute);
    	input.put("kodeRute",id);
    	timeByRoutes.addData(input);
    	timeByRoutes.pushToJSONFile();
    }
    public void deleteTimeByRoute(String input) throws FileNotFoundException, IOException {
    	String id = routes.getIdByKodeRute(input);
    	timeByRoutes.deleteDataById(id);
    	timeByRoutes.pushToJSONFile();
    }
    
    public ArrayList<HashMap<String,String>> getDataTimeByRoute(String input) throws FileNotFoundException, IOException {
    	String id = routes.getIdByKodeRute(input);
    	ArrayList<HashMap<String,String>> array_final = timeByRoutes.getDataById(id);
    	
    	return array_final;
    }
    
	public String getRuteByKodeRute(String string) {
		
    	String rute = routes.getKodeRuteById(string);
    	return rute;
	}
    public String getWaktuByKodeWaktu(String inputRoute) throws FileNotFoundException, IOException {	
    	
    	String waktu = times.getTimeByKodeWaktu(inputRoute);
    	return waktu;
    }
   

	public HashMap<Integer, IMenuTimeByRoute> getSubMenu() {
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
