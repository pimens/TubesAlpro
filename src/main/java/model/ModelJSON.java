package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONTokener;

public class ModelJSON {

    public JSONArray readJson(String path) throws FileNotFoundException {
        BufferedReader br;
        File file = new File(path);
        br = new BufferedReader(new FileReader(file));
        JSONTokener tokener = new JSONTokener(br);
        JSONArray data = new JSONArray(tokener);
        return data;
    } 
    
    public void writeToJson(String data, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(data);
        writer.close();
    }
    
}
