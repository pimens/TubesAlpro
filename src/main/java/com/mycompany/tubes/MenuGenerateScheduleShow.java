/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tubes;

import controller.ControllerSchedules;
import controller.Schedule;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;

/**
 *
 * @author pmen
 */
public class MenuGenerateScheduleShow {
    ControllerSchedules c;
    public MenuGenerateScheduleShow(){
        c = new ControllerSchedules();
        
    }
    public void index() throws FileNotFoundException, IOException{
        ArrayList<Schedule> jadwal = new ArrayList<>();

        jadwal = c.getAllSchedules();

        System.out.println("Kode Jadwal\tTanggal\t\tWaktu Keberangkatan\tKeberangkatan\tTujuan\t\tWaktu Tiba\tKAI\t\tStatus");
        for (Schedule j : jadwal) {
            j.print();
        }
       
    }
}
