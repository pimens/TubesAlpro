package com.mycompany.tubes;

import controller.ControllerAdmin;
import controller.ControllerStationsByRoutes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MenuStationByRouteMain {
    ControllerStationsByRoutes c;
    public MenuStationByRouteMain() {
        c = new ControllerStationsByRoutes();
    }
    public void index(String m) throws IOException {
        Scanner cin = new Scanner(System.in);
        int pil;
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(m);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("1. Tambah Jalur Stasiun Pada Rute");
        System.out.println("2. Lihat Jalur Stasiun Pada Rute");
        System.out.println("3. Delete Jalur Stasiun Pada Rute");
        System.out.println("99. Menu Utama");
        do {
            System.out.print("Pilihan : ");
            pil = cin.nextInt();
        } while (pil < 0 || (pil > 3 && pil != 99));
        switch (pil) {
            case 1:
                c.indexAdd();
                break;
            case 2:
                c.MenuSeeStation();
                break;
            case 3:
                c.indexDelete();
                break;
            default:
                ControllerAdmin a = new ControllerAdmin();
                a.index();
                break;
        }
    }
}
