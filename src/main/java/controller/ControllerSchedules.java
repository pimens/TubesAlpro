
package controller;

import com.mycompany.tubes.MenuGenerateScheduleShow;
import com.mycompany.tubes.MenuGenerateSchedules;
import com.mycompany.tubes.MenuGenerateSchedulesMain;
import java.io.IOException;

/**
 *
 * @author pmen
 */
public class ControllerSchedules {

    public void index() throws IOException {
        MenuGenerateSchedulesMain m = new MenuGenerateSchedulesMain();
        m.index();
    }
    //======================generate sch
    public void menuGenerateSchedules() throws IOException {
        MenuGenerateSchedules m = new MenuGenerateSchedules();
        m.index();
    }
    public void generateSchedules() {
        System.out.println("Generate Jadwal berhasil - cont");       
    }
    //========================end generate sch
    public void showJadwal(){
        MenuGenerateScheduleShow m = new MenuGenerateScheduleShow();
        m.index();
    }
}
