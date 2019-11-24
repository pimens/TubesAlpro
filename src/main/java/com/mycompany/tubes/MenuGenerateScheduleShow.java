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

/**
 *
 * @author pmen
 */
public class MenuGenerateScheduleShow {

    ControllerSchedules c;

    public MenuGenerateScheduleShow() {
        c = new ControllerSchedules();

    }

    public void index() throws FileNotFoundException, IOException {
        Table st = new Table();
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("Kode Jadwal", "Tanggal", "Waktu Keberangkatan", "Keberangkatan", "Tujuan", "Waktu Tiba", "KAI", "Status");//optional - if not used then there will be no header and horizontal lines    
        ArrayList<Schedule> jadwal = new ArrayList<>();
        jadwal = c.getAllSchedules();
        for (Schedule j : jadwal) {
            st.addRow(j.getKode(),j.getDate(),j.getDepart(),j.getOrigin(),j.getDestination(),j.getArrive(),j.getName(),j.descStatus());
        }
        st.print();

    }
}
