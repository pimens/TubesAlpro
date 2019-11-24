package model;

import controller.ControllerStationsByRoutes;
import controller.Schedule;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import org.json.JSONArray;
import org.json.JSONObject;

public class ModelSchedules extends ModelJSON {

    public JSONArray getSchedules() throws FileNotFoundException {
        JSONArray s = readJson("DataJson/searchSchedule.json");
        return s;
    }

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
        JSONArray r = new JSONArray();

        routes = readJson("DataJson/StationsByRoute.json");
        for (int i = 0; i < routes.length(); i++) {
            JSONObject a = new JSONObject(routes.get(i).toString());
            if (id.equals(a.get("id"))) {
                r = a.getJSONArray("routes");
                break;
            }
        }
        return r;
    }

    public int getSumOfTime(String id) throws FileNotFoundException {
        ControllerStationsByRoutes c = new ControllerStationsByRoutes();
        JSONArray rute = new JSONArray();
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

    public JSONObject getTrains(String id) throws FileNotFoundException {
        JSONArray trains = readJson("DataJson/train.json");
        JSONObject tr = new JSONObject();
        for (int i = 0; i < trains.length(); i++) {
            if (trains.getJSONObject(i).get("id").equals(id)) {
                tr = trains.getJSONObject(i);
                break;
            }
        }
        JSONArray duduk = new JSONArray();
        for (int i = 1; i < Integer.valueOf(tr.getString("premium")) + 1; i++) {
            JSONObject oo = new JSONObject();
            JSONArray kursi = new JSONArray();
            for (int j = 0; j < 20; j++) {
                JSONObject o = new JSONObject();
                o.put("status", "E");
                kursi.put(o);
            }
            oo.put("kursi", kursi);
            oo.put("jenis", "P");
            oo.put("gerbong", String.valueOf(i));
            duduk.put(oo);
        }
        for (int i = 1; i < Integer.valueOf(tr.getString("business")) + 1; i++) {
            JSONObject oo = new JSONObject();
            JSONArray kursi = new JSONArray();
            for (int j = 0; j < 10; j++) {
                JSONObject o = new JSONObject();
                o.put("status", "E");
                kursi.put(o);
            }
            oo.put("kursi", kursi);
            oo.put("jenis", "B");
            oo.put("gerbong", String.valueOf(i));
            duduk.put(oo);
        }
        JSONObject x = new JSONObject();
        x.put("duduk", duduk);
        return x;
    }

    public void genSch() throws FileNotFoundException, ParseException, IOException {
        ModelSchedules m = new ModelSchedules();
        JSONArray ruteWaktu = m.readJson("DataJson/timebyroutes.json");
        JSONArray trains = m.readJson("DataJson/trainbyroute.json");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm");
        JSONArray sch = new JSONArray();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        int cekKA = 0;
        int selisih = 0;
        int index = 0;
        for (int i = 0; i < ruteWaktu.length(); i++) {
            JSONObject dataOneRute = ruteWaktu.getJSONObject(i);
            int sumTime = m.getSumOfTime(dataOneRute.getString("kodeRute")) * 2;
            int wTempuhAss = sumTime / 2;
            StringTokenizer st = new StringTokenizer(dataOneRute.getString("kodeWaktu"), ",");
            while (st.hasMoreTokens()) {
                String dd = st.nextToken();
                String departure = m.getWaktuByKode(dd);
                LocalTime arrivalAtSame = LocalTime.parse(departure);
                String arrNewSc = df.format(arrivalAtSame.plusMinutes(sumTime));
                for (int j = 0; j < trains.length(); j++) {
                    JSONObject train = trains.getJSONObject(j);
                    if (train.getString("kodeRute").equals(dataOneRute.getString("kodeRute"))) {
                        for (int k = 0; k < sch.length(); k++) {
                            JSONObject sc = sch.getJSONObject(k);
                            if (train.getString("kodeKereta").equals(sc.getString("kereta"))) {
                                LocalTime lt = LocalTime.parse(m.getWaktuByKode(sc.getString("dep")));
                                int w = Integer.valueOf(sc.getString("time")) * 2;
                                String arrAtSameStation = df.format(lt.plusMinutes(w));
                                if (m.cekTimeGr(departure, m.getWaktuByKode(sc.getString("dep")))) {
                                    if (m.cekTimeGr(departure, arrNewSc)) {
                                        if (m.cekTimeGr(m.getWaktuByKode(sc.getString("dep")), arrAtSameStation)) {
                                            selisih = m.getDifference(m.getWaktuByKode(sc.getString("dep")), departure);
                                            if (selisih <= w) {
                                                cekKA = 1;
                                            } else {

                                            }
                                        } else {
                                            if (m.cekTimeGr(arrNewSc, m.getWaktuByKode(sc.getString("dep")))) {
                                                cekKA = 1;
                                            }
                                            if (arrNewSc.equals(sc.get("dep"))) {
                                                cekKA = 1;
                                            }
                                        }
                                    } else {
                                        selisih = m.getDifference(m.getWaktuByKode(sc.getString("dep")), departure);
                                        if (selisih <= w) {
                                            cekKA = 1;
                                        }
                                    }
                                } else {
                                    if (m.cekTimeGr(m.getWaktuByKode(sc.getString("dep")), arrAtSameStation)) {
                                        if (m.cekTimeGr(departure, arrNewSc)) {
                                            selisih = m.getDifference(departure, m.getWaktuByKode(sc.getString("dep")));
                                            if (selisih <= sumTime) {
                                                cekKA = 1;
                                            }
                                        } else {
                                            if (m.cekTimeGr(arrAtSameStation, departure)) {
                                                cekKA = 1;
                                            }
                                            if (arrAtSameStation.equals(departure)) {
                                                cekKA = 1;
                                            }
                                        }
                                    } else {
                                        selisih = m.getDifference(departure, m.getWaktuByKode(sc.getString("dep")));
                                        if (selisih <= sumTime) {
                                            cekKA = 1;
                                        }
                                    }
                                }

                            }
                            if (cekKA == 1) {
                                break;
                            }
                        }//for sc
                    } else {
                        cekKA = 1;
                    }
                    if (cekKA == 0) {
                        String waktuSampaiAssign = df.format(arrivalAtSame.plusMinutes(wTempuhAss));
                        JSONObject newSc = new JSONObject();
                        newSc.put("kode", "JW" + index);
                        newSc.put("rute", m.rutebyid(dataOneRute.getString("kodeRute")));
                        newSc.put("time", String.valueOf(m.getSumOfTime(dataOneRute.getString("kodeRute"))));
                        newSc.put("kereta", train.getString("kodeKereta"));
                        newSc.put("dep", dd);
                        newSc.put("arr", waktuSampaiAssign);
                        sch.put(newSc);
                        index++;
                        break;
                    } else {
                        cekKA = 0;
                    }
                }//for tra
            }//while token
        }//loop time

        System.out.println("No.\t Rute       \t dep     \t arrival \t KodeKereta \t waktu");
        for (int i = 0; i < sch.length(); i++) {
            JSONObject o = sch.getJSONObject(i);
            System.out.println(i + "--\t" + o.getString("rute") + " \t " + m.getWaktuByKode(o.getString("dep")) + " \t           " + o.getString("arr") + "\t      " + o.getString("kereta") + " \t         " + o.getString("time") + "   " + o.getString("kode"));
        }
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.NOVEMBER, 1);
        System.out.println();
        JSONArray jad = new JSONArray();
        //json array paling luar
        JSONArray ts = new JSONArray();

        for (int i = 0; i < 2; i++) {
            //for json sch
            JSONObject jadwal = new JSONObject();
            String tgl = simpleDateFormat1.format(calendar.getTime());
            jadwal.put("tanggal", tgl);
            JSONArray jj = new JSONArray();

            //for json train
            JSONArray kereta = new JSONArray();
            JSONObject tmp = new JSONObject();
            for (int j = 0; j < sch.length(); j++) {
                JSONObject o = new JSONObject();
                //for train
                o = m.getTrains(sch.getJSONObject(j).getString("kereta"));
                o.put("id", sch.getJSONObject(j).get("kode"));
                kereta.put(o);
                //for sch    
                jj.put(sch.get(j));
            }
            //for json train
            tmp.put("kereta", kereta);
            tmp.put("tanggal", tgl);
            ts.put(tmp);
            //for json sch
            jadwal.put("jadwal", jj);
            jad.put(jadwal);
            calendar.add(Calendar.DATE, 1);

        }
        m.writeToJson(ts.toString(2), "DataJson/trainStatus.json");
        m.writeToJson(jad.toString(2), "DataJson/searchSchedule.json");
    }

    public ArrayList<Schedule> readSearch() throws FileNotFoundException, IOException {
        JSONArray arr = readJson("DataJson/searchSchedule.json");
        JSONArray ruteArr = readJson("DataJson/routes.json");
        JSONArray waktu = readJson("DataJson/times.json");
        JSONArray sepur = readJson("DataJson/train.json");
        JSONArray status = readJson("DataJson/trainStatus.json");
        ModelTrains mm = new ModelTrains();
        ArrayList<Schedule> jadwal = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            JSONArray jd = arr.getJSONObject(i).getJSONArray("jadwal");
            for (int j = 0; j < jd.length(); j++) {
                String kode = jd.getJSONObject(j).getString("kode");
                String date = arr.getJSONObject(i).getString("tanggal");
                String depart = "";
                String arrive = jd.getJSONObject(j).getString("arr");
                String origin = "";
                String destination = "";
                String name = "";
                int statuss = 0;

                for (int k = 0; k < waktu.length(); k++) {
                    if (waktu.getJSONObject(k).getString("kodeWaktu")
                            .equals(jd.getJSONObject(j).getString("dep"))) {
                        depart = waktu.getJSONObject(k).getString("waktu");
                    }
                }

                String rute = jd.getJSONObject(j).getString("rute");
                for (int k = 0; k < ruteArr.length(); k++) {
                    if (ruteArr.getJSONObject(k).getString("kodeRute").equals(rute)) {
                        String tmporigin = ruteArr.getJSONObject(k).getString("src");
                        String tmpdestination = ruteArr.getJSONObject(k).getString("dst");
                        origin = mm.getCityNameById(tmporigin);
                        destination = mm.getCityNameById(tmpdestination);
                        break;
                    }
                }

                for (int k = 0; k < sepur.length(); k++) {
                    if (sepur.getJSONObject(k).getString("id")
                            .equals(jd.getJSONObject(j).getString("kereta"))) {
                        name = sepur.getJSONObject(k).getString("kodeKAI");
                        break;
                    }
                }

                for (int k = 0; k < status.length(); k++) {
                    if (date.equals(status.getJSONObject(k).getString("tanggal"))) {
                        JSONArray temp1 = status.getJSONObject(k).getJSONArray("kereta");
                        for (int l = 0; l < temp1.length(); l++) {
                            if (temp1.getJSONObject(l).getString("id").equals(kode)) {
                                JSONArray temp2 = temp1.getJSONObject(l).getJSONArray("duduk");
                                for (int m = 0; m < temp2.length(); m++) {
                                    JSONArray temp3 = temp2.getJSONObject(m).getJSONArray("kursi");
                                    for (int n = 0; n < temp3.length(); n++) {
                                        if (temp3.getJSONObject(n).getString("status").equals("E")) {
                                            statuss += 1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Schedule s = new Schedule(kode, date, depart, arrive, origin, destination, name, statuss);
                jadwal.add(s);
            }

        }
        return jadwal;
    }
}
