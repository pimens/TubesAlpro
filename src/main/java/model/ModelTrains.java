package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import controller.Train;
import controller.Schedule;

public class ModelTrains extends ModelJSON {
    private ArrayList<Train> trains;

    public ModelTrains() {
        trains = new ArrayList<>();
    }

    public String convertRute(String orig, String dest) throws FileNotFoundException {
        JSONArray skedul = readJson("DataJson/schedules.json");

        String temp = "";

        for (int i=0; i<skedul.length(); i++) {
            if ((skedul.getJSONObject(i).getString("dep").equals(orig)) && (skedul.getJSONObject(i).getString("arr").equals(dest))) {
                temp = skedul.getJSONObject(i).getString("kodeRute");
            }
        }

        return temp;
    }

    public ArrayList<Schedule> readSearch(String rut, String tgl) throws FileNotFoundException, IOException {
        JSONArray arr = readJson("DataJson/searchSchedule.json");
        JSONArray skedul = readJson("DataJson/schedules.json");
        JSONArray waktu = readJson("DataJson/times.json");
        JSONArray sepur = readJson("DataJson/train.json");
        JSONArray status = readJson("DataJson/trainStatus.json");

        ArrayList<Schedule> jadwal = new ArrayList<>();

        for (int i=0; i<arr.length(); i++) {
            if (tgl.equals(arr.getJSONObject(i).getString("tanggal"))) {
                JSONArray jd = arr.getJSONObject(i).getJSONArray("jadwal");
                for (int j=0; j<jd.length(); j++) {
                    if (jd.getJSONObject(j).getString("rute").equals(rut)) {
                        String kode = jd.getJSONObject(j).getString("kode");
                        String date = tgl;
                        String depart = "";
                        String arrive = "";
                        String origin = "";
                        String destination = "";
                        String name = "";
                        int statuss = 0;
                        
                        for (int k=0; k<waktu.length(); k++) {
                            if (waktu.getJSONObject(k).getString("kodeWaktu").equals(jd.getJSONObject(j).getString("dep"))) {
                                depart = waktu.getJSONObject(j).getString("waktu");
                            }
                            if (waktu.getJSONObject(k).getString("kodeWaktu").equals(jd.getJSONObject(j).getString("arr"))) {
                                arrive = waktu.getJSONObject(j).getString("waktu");
                            }
                        }

                        String rute = jd.getJSONObject(j).getString("rute");
                        for (int k=0; k<skedul.length(); k++) {
                            if (skedul.getJSONObject(k).getString("kodeRute").equals(rute)) {
                                origin = skedul.getJSONObject(k).getString("dep");
                                destination = skedul.getJSONObject(k).getString("arr");
                                break;
                            }
                        }

                        for (int k=0; k<sepur.length(); k++) {
                            if (sepur.getJSONObject(k).getString("id").equals(jd.getJSONObject(j).getString("kereta"))) {
                                name = sepur.getJSONObject(k).getString("kodeKAI");
                                break;
                            }
                        }

                        for (int k=0; k<status.length(); k++) {
                            if (tgl.equals(status.getJSONObject(k).getString("tanggal"))) {
                                JSONArray temp1 = status.getJSONObject(k).getJSONArray("kereta");
                                for (int l=0; l<temp1.length(); l++) {
                                    if (temp1.getJSONObject(l).getString("id").equals(jd.getJSONObject(j).getString("kereta"))) {
                                        JSONArray temp2 = temp1.getJSONObject(l).getJSONArray("duduk");
                                        for (int m=0; m<temp2.length(); m++) {
                                            JSONArray temp3 = temp2.getJSONObject(m).getJSONArray("kursi");
                                            for (int n=0; n<temp3.length(); n++) {
                                                if (temp3.getJSONObject(n).getString("status").equals("E")) {
                                                    statuss += 1;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Schedule s = new Schedule(kode,date,depart,arrive,origin,destination,name,statuss);

                        jadwal.add(s);
                    }
                }
            }
        }
        return jadwal;
    }
}