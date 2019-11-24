
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import org.json.JSONException;


public class Main {
     public static void main(String[] args) throws IOException, JSONException, FileNotFoundException, ParseException {
        Home u = new Home();
        u.index();         
    }
}
