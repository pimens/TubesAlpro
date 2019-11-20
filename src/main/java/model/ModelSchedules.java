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

public class ModelSchedules extends ModelJSON {

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

    public int getSumOfTime(String kode) throws FileNotFoundException {
        ControllerStationsByRoutes c = new ControllerStationsByRoutes();
        JSONObject r = null;
        JSONArray rute = null;
        int total = 0;
        r = c.getDataByRoute(kode);
        rute = r.getJSONArray("routes");
        for (int i = 0; i < rute.length(); i++) {
            JSONObject a = rute.getJSONObject(i);
            total = total + Integer.valueOf(a.getString("time"));
        }
        return total;
    }

    public boolean cekTimeGr(String t, String t2) throws ParseException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        if (dateFormat.parse(t).after(dateFormat.parse(t2))) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        ModelSchedules m = new ModelSchedules();
        JSONArray ruteWaktu = m.readJson("DataJson/timebyroutes.json");
        JSONArray trains = m.readJson("DataJson/trainbyroute.json");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        JSONArray sch = new JSONArray();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
//        if (m.cekTimeGr("10:46", "10:45")) {
//            System.out.println("lebih");
//        }else{
//            System.out.println("kura");
//        }
        int cekKA = 0;
        //for all rute
        for (int i = 0; i < ruteWaktu.length(); i++) {
            JSONObject dataOneRute = ruteWaktu.getJSONObject(i);
            int sumTime = m.getSumOfTime(dataOneRute.getString("kodeRute")) * 2;
            StringTokenizer st = new StringTokenizer(dataOneRute.getString("kodeWaktu"), ",");
            System.out.println(dataOneRute.getString("kodeRute")+"==========================");
            //for all times by route
            while (st.hasMoreTokens()) {
                String departure = m.getWaktuByKode(st.nextToken());
                LocalTime arrivalAtSame = LocalTime.parse(departure);
                String arrNewSc = df.format(arrivalAtSame.plusMinutes(sumTime));
                System.out.println(arrNewSc + "===========" + departure+"=="+sumTime);
                for (int j = 0; j < trains.length(); j++) {
                    JSONObject train = trains.getJSONObject(j);
                    System.out.println("cari kereta yang dipake dirute ini");
                    if (train.getString("kodeRute").equals(dataOneRute.getString("kodeRute"))) {
                        System.out.println("cek kereta == ========================================"+train.getString("kodeKereta"));
                        for (int k = 0; k < sch.length(); k++) {
                            JSONObject sc = sch.getJSONObject(k);
                            //sudah dpt waktu pertama di st, trus cari kereta di kode rute i
                            //cek kereta 1per1 di schedule avail gak
                            if (train.getString("kodeKereta").equals(sc.getString("kodeKereta"))) {

                                LocalTime lt = LocalTime.parse(sc.getString("dep"));
                                int w = Integer.valueOf(sc.getString("time")) * 2;
                                String arrAtSameStation = df.format(lt.plusMinutes(w));//waktu sampai kembali ke stasiun awal
                                System.out.println("Kereta " + k + " : " + sc.get("kodeKereta") + ":" + sc.getString("time"));
                                System.out.println(sc.getString("dep") + ":" + arrAtSameStation);
                                //kalau arrival lebih dari dari departure artinya normal gak lewat tngah malam
                                if (m.cekTimeGr(arrAtSameStation, sc.getString("dep"))) {
                                    System.out.println("\t gak lewat tngah malam");
                                    //kalau departure lebih besar dari departure kereta
                                    //perlu dicek dia juga lebih bsar gak dari waktu sampai kembali ke stasiun awal
                                    if (m.cekTimeGr(departure, sc.getString("dep"))) {
                                        System.out.println("\t departure greater than departure kereta");
                                        //cek lebih bsar gak dariwaktu sampai kembali ke stasiun awal, kalau nggak, false
                                        if (m.cekTimeGr(arrAtSameStation, departure)) {
                                            System.out.println("\t depature kurang dari waktu arrival di stasiun yang sama");
                                            cekKA = 1;//artinya waktu brngkat jadwal ini bnetrok dengan kereta ini krna sdg diperjalanan
                                        }
                                    } //kalau waktu departure kurang dari departure kereta
                                    else {
                                        //cek apakah arrival di samestation dari departure, bentrokan sama departure kereta
                                        if (m.cekTimeGr(arrNewSc, sc.getString("dep"))) {
                                            System.out.println("waktu sampai lebih besar dari waktu berngkat kereta");
                                            cekKA = 1;
                                        }
                                    }
                                } //kalau arrival lebih kecil artinya lwat tngah malem baliknya distasiun yg saama
                                else {
                                    System.out.println("Current time is less than 12.07");
                                }
                            }
                            if (cekKA == 1) {
                                //sudahpasti ga bisa pake ka ini, stop ganti kereta
                                
                                break;
                            }
                        }//for sc
                    }else{
                        cekKA=1;
                    }
                    if (cekKA == 0) {
                        System.out.println("\t \t asssign "+dataOneRute.getString("kodeRute")+" "+departure+" "+train.getString("kodeKereta"));
                        JSONObject newSc = new JSONObject();
                        newSc.put("kodeRute", dataOneRute.getString("kodeRute"));
                        newSc.put("time", String.valueOf(m.getSumOfTime(dataOneRute.getString("kodeRute"))));
                        newSc.put("kodeKereta", train.getString("kodeKereta"));
                        newSc.put("dep", departure);
                        newSc.put("arr", "00:00-blm dipake");
                        sch.put(newSc);
                        break;
                    }else{
                        cekKA=0;
                    }
                }//for tra

               

            }

        }
        System.out.println(sch.toString(2));

    }
}
