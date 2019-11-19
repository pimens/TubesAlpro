package com.mycompany.tubes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IMenuTimeByRoute {
	public String getLabel();
	public void index() throws FileNotFoundException, IOException;
	public void showContent(ArrayList<HashMap<String, String>> data);
}
