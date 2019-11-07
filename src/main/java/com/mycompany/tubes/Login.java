package com.mycompany.tubes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;
import org.json.JSONException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Login {

    public static void main(String[] args) throws IOException, JSONException {
        Scanner cin = new Scanner(System.in);
        String uname = "";
        String pass = "";
        System.out.print("Username ;");
        uname = cin.next();
        System.out.print("Pass ;");
        pass = cin.next();
        Data d = new Data();
        JSONObject object = d.getDataById(uname, pass);
        if(object.getString("id").equals("gagal")){
            System.out.println("gagal!!");
        }else{
            System.out.println("berhasil");
        }

    }
}
