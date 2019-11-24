package model;

import controller.ControllerStationsByRoutes;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.StringTokenizer;
import org.json.JSONArray;
import org.json.JSONObject;

public class scc extends ModelJSON {

    public String getWaktuByKode(String kode) throws FileNotFoundException {
        JSONArray times = readJson("DataJson/times.json");
        String waktu = "";
        for (int i = 0; i < times.length(); i++) {
            JSONObject time = times.getJSONObject(i);
            if (kode.equals(time.get("kodeWaktu"))) {
                waktu = time.getString("waktu");
            }
        }
        return waktu;

    }

    //=====================================cari waktu *2
    public JSONArray getDataRuteById(String id) throws FileNotFoundException {
        JSONArray routes = new JSONArray();
        routes = readJson("DataJson/StationsByRoute.json");
        for (int i = 0; i < routes.length(); i++) {
            JSONObject a = new JSONObject(routes.get(i).toString());
            if (id.equals(a.get("id"))) {
                routes = a.getJSONArray("routes");
                break;
            }
        }
        return routes;
    }

    public int getSumOfTime(String id) throws FileNotFoundException {
        ControllerStationsByRoutes c = new ControllerStationsByRoutes();
        JSONArray rute = null;
        int total = 0;
        rute = getDataRuteById(id);
        for (int i = 0; i < rute.length(); i++) {
            JSONObject a = rute.getJSONObject(i);
            total = total + Integer.valueOf(a.getString("time"));
        }
        return total;
    }
    //============================== end cari waktu *2

    public boolean cekTimeGr(String t, String t2) throws ParseException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        if (dateFormat.parse(t).after(dateFormat.parse(t2))) {
            return true;
        } else {
            return false;
        }
    }

    public String rutebyid(String id) throws FileNotFoundException {
        JSONArray rute = readJson("DataJson/routes.json");
//        System.out.println(rute.toString(2));
        String n = "";
        for (int i = 0; i < rute.length(); i++) {
            JSONObject r = rute.getJSONObject(i);
            if (id.equals(r.getString("id"))) {
                n = r.getString("kodeRute");
            }
        }
        return n;
    }

    public int getDifference(String a, String b) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date1 = format.parse(a);
        Date date2 = format.parse(b);
        int difference = (int) ((date2.getTime() - date1.getTime()) / 1000 / 60);
        return difference;
    }

    public static boolean isHourInInterval(String target, String start, String end) {
        return ((target.compareTo(start) >= 0) && (target.compareTo(end) <= 0));
    }

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        scc m = new scc();
        JSONArray ruteWaktu = m.readJson("DataJson/timebyroutes.json");
        JSONArray trains = m.readJson("DataJson/trainbyroute.json");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        JSONArray sch = new JSONArray();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        int cekKA = 0;
        int selisih = 0;
        //for all rute
        for (int i = 0; i < ruteWaktu.length(); i++) {
            JSONObject dataOneRute = ruteWaktu.getJSONObject(i);
            int sumTime = m.getSumOfTime(dataOneRute.getString("kodeRute")) * 2;
            StringTokenizer st = new StringTokenizer(dataOneRute.getString("kodeWaktu"), ",");
            System.out.println(m.rutebyid(dataOneRute.getString("kodeRute")) + "==========================");
            System.out.println("Waktu Tempuh" + sumTime);
            //for all times by route
            while (st.hasMoreTokens()) {
                String departure = m.getWaktuByKode(st.nextToken());
                LocalTime arrivalAtSame = LocalTime.parse(departure);
                String arrNewSc = df.format(arrivalAtSame.plusMinutes(sumTime));
                System.out.println(departure + " - " + arrNewSc);
                for (int j = 0; j < trains.length(); j++) {
                    JSONObject train = trains.getJSONObject(j);
                    //filter keretanya by kode rute, dpt semua kereta dirute tertentu
                    if (train.getString("kodeRute").equals(dataOneRute.getString("kodeRute"))) {
                        System.out.println("   cek kereta ===" + train.getString("kodeKereta"));
                        for (int k = 0; k < sch.length(); k++) {
                            JSONObject sc = sch.getJSONObject(k);
                            //filter kereta yang ada dijadwal yang digenerate, berdasarkan kode kereta/rute
                            if (train.getString("kodeKereta").equals(sc.getString("kodeKereta"))) {//
                                LocalTime lt = LocalTime.parse(sc.getString("dep"));//ambil waktu berangkat
                                int w = Integer.valueOf(sc.getString("time")) * 2;//kalikan dua lama perjalanan ketujuan
                                String arrAtSameStation = df.format(lt.plusMinutes(w));//waktu sampai kembali ke stasiun awal = waktu brngkat + 2 kali perjalanan
                                System.out.println("\t Kereta " + " : " + sc.get("kodeKereta") + ":" + w);
                                System.out.println("\t Departure kereta = " + sc.getString("dep") + ": sampai kembali : " + arrAtSameStation);
                                //kalau dep > dep kereta
                                if (m.cekTimeGr(departure, sc.getString("dep"))) {
                                    System.out.println("\t \t departure " + departure +" lebih besar dari departure kereta " + sc.getString("dep"));
                                    if (m.cekTimeGr(departure, arrNewSc)) {  // 1111 cek tngh malam
                                        System.out.println("\t \t Tengah malam departurenya");
                                        if (m.cekTimeGr(sc.getString("dep"), arrAtSameStation)) {// 222 kalao tnghmalam dan kereta jg tngah mlm bsa cek slish
                                            selisih = m.getDifference(sc.getString("dep"), departure);
                                            if (selisih <= w) {//3333
                                                cekKA = 1;
                                            } else {

                                            }
                                        } else {//444
                                            if (isHourInInterval(arrNewSc, sc.getString("dep"), arrAtSameStation)) {
                                                System.out.println("ada di intervalnya");
                                                cekKA = 1;
                                            }
                                        }
                                    } else {//555
                                        selisih = m.getDifference(sc.getString("dep"), departure);
                                        if (selisih <= w) {//666
                                            System.out.println("\t \t \t selish kurang dari w, selisih =" + selisih + "  w =" + w + "----" + departure + "---" + sc.getString("dep"));
                                            cekKA = 1;
                                        } else {//samadengan masuk sini juga//777
                                            System.out.println("\t \t \t selisih lebih dari w, selisih = " + selisih + "  w =" + w + "----" + departure + "---" + sc.getString("dep"));
                                        }
                                    }
                                } else {
                                    System.out.println("\t \t departure "+ departure +" kurang dari departure kereta  " + sc.getString("dep"));
                                    if (m.cekTimeGr(sc.getString("dep"), arrAtSameStation)) {//999kalao tnghmalam dan kereta jg tngah mlm bsa cek slish
                                        System.out.println("Tngah malam departure keretanya");
                                        if (m.cekTimeGr(departure, arrNewSc)) {// 888 cek tngh malam
                                            selisih = m.getDifference(departure, sc.getString("dep"));
                                            if (selisih <= sumTime) {//10
                                                cekKA = 1;
                                            } else {

                                            }
                                        } else {///11
                                            if (isHourInInterval(arrAtSameStation, departure, arrNewSc)) {
                                                cekKA = 1;
                                            }
                                        }
                                    } else {//12
                                        selisih = m.getDifference(departure, sc.getString("dep"));
                                        if (selisih <= sumTime) {//13
                                            System.out.println("\t \t \t selisih kurang dari sumtime, selisih = " + selisih + "  sumtime =" + sumTime + "----" + departure + "---" + sc.getString("dep"));
                                            cekKA = 1;
                                        } else {//14
                                            System.out.println("\t \t \t selisih lebih besar dari sumtime, selisih = "+selisih+" sumtime="+ sumTime+ "----" + departure + "---" + sc.getString("dep"));

                                        }
                                    }
                                }

                            }
                            if (cekKA == 1) {
                                //sudahpasti ga bisa pake ka ini, stop ganti kereta
                                break;
                            }
                        }//for sc
                    } else {
                        cekKA = 1;
                    }
                    if (cekKA == 0) {
                        System.out.println("\t \t asssign " + dataOneRute.getString("kodeRute") + " " + departure + " " + train.getString("kodeKereta"));
                        JSONObject newSc = new JSONObject();
                        newSc.put("kodeRute", dataOneRute.getString("kodeRute"));
                        newSc.put("time", String.valueOf(m.getSumOfTime(dataOneRute.getString("kodeRute"))));
                        newSc.put("kodeKereta", train.getString("kodeKereta"));
                        newSc.put("dep", departure);
                        newSc.put("arr", arrNewSc);
                        sch.put(newSc);
                        break;
                    } else {
                        cekKA = 0;
                    }
                }//for tra
            }//while token
        }//loop time
        System.out.println("No.\t Rute       \t dep     \t arrival \t KodeKereta \t waktu");
        for(int i=0;i<sch.length();i++){
            JSONObject o = sch.getJSONObject(i);
            System.out.println(i+"--\t"+m.rutebyid(o.getString("kodeRute"))+" \t "+o.getString("dep")+" \t           "+o.getString("arr")+"\t      "+o.getString("kodeKereta")+" \t         "+o.getString("time"));
        }

    }
}
