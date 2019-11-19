package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.mycompany.tubes.IMenuTimeByRoute;
import com.mycompany.tubes.MenuTimeByRoute;
import com.mycompany.tubes.MenuTimeByRouteAdd;
import com.mycompany.tubes.MenuTimeByRouteShow;
import com.mycompany.tubes.MenuTimeByRouteDelete;

import model.ModelInvoices;
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
	
    public void index() throws FileNotFoundException, IOException {
        m.index();
    }
    
	public void subMenu(IMenuTimeByRoute subMenu) throws FileNotFoundException, IOException {
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
    	timeByRoutes.addData(input);
    	timeByRoutes.pushToJSONFile();
    }
    public void deleteTimeByRoute(String input) throws FileNotFoundException, IOException {
    	timeByRoutes.deleteData(input);
    	timeByRoutes.pushToJSONFile();
    }
    public ArrayList<HashMap<String,String>> getDataTimeByRoute(String input) throws FileNotFoundException, IOException {	
    	ArrayList<HashMap<String,String>> array_final = timeByRoutes.getDataByKodeRute(input);
    	return array_final;
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
