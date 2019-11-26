package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import controller.Train;
import controller.Wagon;
import controller.Schedule;
import controller.User;

public class ModelTrains extends ModelJSON {
    private ArrayList<Status1> status;

    public ModelTrains() throws FileNotFoundException {
        status = new ArrayList<>();

        read();
    }

    public String convertRute(String orig, String dest) throws FileNotFoundException {
        JSONArray rute = readJson("DataJson/routes.json");
        String temp = "";
        for (int i = 0; i < rute.length(); i++) {
            if ((rute.getJSONObject(i).getString("src").equals(orig))
                    && (rute.getJSONObject(i).getString("dst").equals(dest))) {
                temp = rute.getJSONObject(i).getString("kodeRute");
            }
        }
        return temp;
    }    
    public String getCityNameById(String id) throws FileNotFoundException{
        JSONArray rute = readJson("DataJson/cities.json");
        String temp = "";
        for (int i = 0; i < rute.length(); i++) {
            if ((rute.getJSONObject(i).getString("id").equals(id))) {
                temp = rute.getJSONObject(i).getString("nama");
            }
        }
        return temp;
    }
    public ArrayList<Schedule> readSearch(String rut, String tgl) throws FileNotFoundException, IOException {
        JSONArray arr = readJson("DataJson/searchSchedule.json");
        JSONArray ruteArr = readJson("DataJson/routes.json");
        JSONArray waktu = readJson("DataJson/times.json");
        JSONArray sepur = readJson("DataJson/train.json");
        JSONArray status = readJson("DataJson/trainStatus.json");

        ArrayList<Schedule> jadwal = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            if (tgl.equals(arr.getJSONObject(i).getString("tanggal"))) {
                JSONArray jd = arr.getJSONObject(i).getJSONArray("jadwal");
                for (int j = 0; j < jd.length(); j++) {
                    if (jd.getJSONObject(j).getString("rute").equals(rut)) {
                        String kode = jd.getJSONObject(j).getString("kode");
                        String date = tgl;
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
                                origin = getCityNameById(tmporigin);
                                destination = getCityNameById(tmpdestination);
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
                            if (tgl.equals(status.getJSONObject(k).getString("tanggal"))) {
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
            }
        }
        return jadwal;
    }

    public Train getTrain(String jadwal, String tgl) throws FileNotFoundException {
        JSONArray sepur = readJson("DataJson/trainStatus.json");

        Train t = new Train();

        for (int i = 0; i < sepur.length(); i++) {
            if (sepur.getJSONObject(i).getString("tanggal").equals(tgl)) {
                JSONArray kereta = sepur.getJSONObject(i).getJSONArray("kereta");
                for (int j = 0; j < kereta.length(); j++) {
                    if (jadwal.equals(kereta.getJSONObject(j).getString("id"))) {
                        JSONArray duduk = kereta.getJSONObject(j).getJSONArray("duduk");
                        for (int k = 0; k < duduk.length(); k++) {
                            int jenis = 1;
                            if (duduk.getJSONObject(k).getString("jenis").equals("P")) {
                                jenis = 0;
                            }
                            Wagon temp = new Wagon(jenis,
                                    Integer.parseInt(duduk.getJSONObject(k).getString("gerbong")));

                            JSONArray kursi = duduk.getJSONObject(k).getJSONArray("kursi");
                            for (int l = 0; l < kursi.length(); l++) {
                                if (kursi.getJSONObject(l).getString("status").equals("E")) {
                                    temp.addSeat(l + 1, 0);
                                } else {
                                    temp.addSeat(l + 1, 1);
                                }
                            }

                            t.addWagon(temp);
                        }
                    }
                }
            }
        }

        return t;
    }

    public void read() throws FileNotFoundException {
        JSONArray sepur = readJson("DataJson/trainStatus.json");

        for (int i = 0; i < sepur.length(); i++) {
            Status1 s1 = new Status1();
            s1.tanggal = sepur.getJSONObject(i).getString("tanggal");

            JSONArray kereta = sepur.getJSONObject(i).getJSONArray("kereta");
            for (int j = 0; j < kereta.length(); j++) {
                Status2 s2 = new Status2();
                s2.id = kereta.getJSONObject(j).getString("id");

                JSONArray duduk = kereta.getJSONObject(j).getJSONArray("duduk");
                for (int k = 0; k < duduk.length(); k++) {
                    Status3 s3 = new Status3();
                    s3.gerbong = duduk.getJSONObject(k).getString("gerbong");
                    s3.jenis = duduk.getJSONObject(k).getString("jenis");

                    JSONArray kursi = duduk.getJSONObject(k).getJSONArray("kursi");
                    for (int l = 0; l < kursi.length(); l++) {
                        s3.kursi.add(kursi.getJSONObject(l).getString("status"));
                    }

                    s2.duduk.add(s3);
                }

                s1.kereta.add(s2);
            }

            status.add(s1);
        }
    }

    public void write() throws JSONException, IOException {
        JSONArray arr = new JSONArray();
        for (int i=0; i<status.size(); i++) {
            JSONObject object = new JSONObject();
            JSONArray kereta = new JSONArray();

            for (int j=0; j<status.get(i).kereta.size(); j++) {
                JSONObject o2 = new JSONObject();
                JSONArray duduk = new JSONArray();

                for (int k=0; k<status.get(i).kereta.get(j).duduk.size(); k++) {
                    JSONObject gerbong = new JSONObject();

                    JSONArray kursi = new JSONArray();
                    for (int l=0; l<status.get(i).kereta.get(j).duduk.get(k).kursi.size(); l++) {
                        JSONObject o4 = new JSONObject();
                        o4.put("status", status.get(i).kereta.get(j).duduk.get(k).kursi.get(l));
                        kursi.put(o4);
                    }

                    gerbong.put("kursi", kursi);
                    gerbong.put("jenis", status.get(i).kereta.get(j).duduk.get(k).jenis);
                    gerbong.put("gerbong", status.get(i).kereta.get(j).duduk.get(k).gerbong);
                    duduk.put(gerbong);
                }

                o2.put("id", status.get(i).kereta.get(j).id);
                o2.put("duduk", duduk);
                kereta.put(o2);
            }

            object.put("kereta", kereta);
            object.put("tanggal", status.get(i).tanggal);
            arr.put(object);
        }

        this.writeToJson(arr.toString(2), "DataJson/trainStatus.json");
    }

    public void booking(String tgl, String kode, ArrayList<String> kursi) throws FileNotFoundException {
        for (String seat : kursi) {
            String gerbong = "" + seat.charAt(0);
            String urutan = "" + seat.charAt(1);
            int nomor = Integer.parseInt(seat.substring(3)) - 1;

            for (int i=0; i<status.size(); i++) {
                if (status.get(i).tanggal.equals(tgl)) {
                    for (int j=0; j<status.get(i).kereta.size(); j++) {
                        if (status.get(i).kereta.get(j).id.equals(kode)) {
                            for (int k=0; k<status.get(i).kereta.get(j).duduk.size(); k++) {
                                if (gerbong.equals(status.get(i).kereta.get(j).duduk.get(k).jenis)) {
                                    if (urutan.equals(status.get(i).kereta.get(j).duduk.get(k).gerbong)) {
                                        status.get(i).kereta.get(j).duduk.get(k).kursi.set(nomor, "F");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public String convertJadwal(String tgl, String jadwal) throws FileNotFoundException {
        JSONArray arr = readJson("DataJson/searchSchedule.json");

        String r = "";

        for (int i = 0; i < arr.length(); i++) {
            if (tgl.equals(arr.getJSONObject(i).getString("tanggal"))) {
                int flag = 0;
                JSONArray jd = arr.getJSONObject(i).getJSONArray("jadwal");
                for (int j = 0; j < jd.length(); j++) {
                    if (jd.getJSONObject(j).getString("kode").equals(jadwal)) {
                        r = jd.getJSONObject(j).getString("rute");
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) {
                    break;
                }
            }
        }

        return r;
    }

    public double getHarga(String tgl, String jadwal, String jenis) throws FileNotFoundException {
        JSONArray sepur = readJson("DataJson/routes.json");

        String rute = convertJadwal(tgl, jadwal);

        double d = 0;
        for (int i=0; i<sepur.length(); i++) {
            if (sepur.getJSONObject(i).getString("kodeRute").equals(rute)) {
                if (jenis.equals("B")) {
                    d = Double.parseDouble(sepur.getJSONObject(i).getString("premium"));
                } else {
                    d = Double.parseDouble(sepur.getJSONObject(i).getString("bisnis"));
                }
                break;
            }
        }

        return d;
    }

    public ArrayList<String> payment(String kode) throws JSONException, IOException {
        JSONArray book = readJson("DataJson/booking.json");

        ArrayList<Pesan> list = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();

        for (int i=0; i<book.length(); i++) {
            Pesan p = new Pesan();

            p.kode = book.getJSONObject(i).getString("kode");
            p.jadwal = book.getJSONObject(i).getString("jadwal");
            p.tanggal = book.getJSONObject(i).getString("tanggal");
            p.harga = book.getJSONObject(i).getString("harga");
            p.user = book.getJSONObject(i).getString("user");
            JSONArray penumpang = book.getJSONObject(i).getJSONArray("penumpang");
            for (int j=0; j<penumpang.length(); j++) {
                p.nama.add(penumpang.getJSONObject(j).getString("nama"));
                p.kursi.add(penumpang.getJSONObject(j).getString("kursi"));
            }

            if (kode.equals(book.getJSONObject(i).getString("kode"))) {
                p.status = "1";
                temp = p.nama;
            } else {
                p.status = book.getJSONObject(i).getString("status");;
            }
            list.add(p);
        }

        JSONArray arr = new JSONArray();
        for (int i=0; i<list.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("kode", list.get(i).kode);
            object.put("tanggal", list.get(i).tanggal);
            object.put("jadwal", list.get(i).jadwal);
            object.put("status", list.get(i).status);
            object.put("harga", list.get(i).harga);
            //add ktp for history
            object.put("user",list.get(i).user);
            JSONArray penumpang = new JSONArray();
            for (int j=0; j<list.get(i).nama.size(); j++) {
                JSONObject o = new JSONObject();

                o.put("nama", list.get(i).nama.get(j));
                o.put("kursi", list.get(i).kursi.get(j));

                penumpang.put(o);
            }
            object.put("penumpang", penumpang);
            arr.put(object);
        }

        this.writeToJson(arr.toString(2), "DataJson/booking.json");

        return temp;
    }

    public String addBooking(String kode, String tgl, ArrayList<String> penumpangnew, ArrayList<String> kursi,long jumlah) throws JSONException, IOException {
        JSONArray book = readJson("DataJson/booking.json");
        
        String tempp = String.valueOf(book.length());
        String rekening = "";
        while (rekening.length() < 10-tempp.length()) {
            rekening += "0";
        }
        rekening += tempp;

        ArrayList<Pesan> list = new ArrayList<>();

        for (int i=0; i<book.length(); i++) {
            Pesan p = new Pesan();

            p.kode = book.getJSONObject(i).getString("kode");
            p.jadwal = book.getJSONObject(i).getString("jadwal");
            p.tanggal = book.getJSONObject(i).getString("tanggal");
            p.harga = book.getJSONObject(i).getString("harga");
            p.user = book.getJSONObject(i).getString("user");
            JSONArray penumpang = book.getJSONObject(i).getJSONArray("penumpang");
            for (int j=0; j<penumpang.length(); j++) {
                p.nama.add(penumpang.getJSONObject(j).getString("nama"));
                p.kursi.add(penumpang.getJSONObject(j).getString("kursi"));
            }

            p.status = book.getJSONObject(i).getString("status");
            list.add(p);
        }
        Pesan p = new Pesan();

        p.kode = rekening;
        p.jadwal = kode;
        p.tanggal = tgl;
        for (int j=0; j<penumpangnew.size(); j++) {
            p.nama.add(penumpangnew.get(j));
            p.kursi.add(kursi.get(j));
        }
        p.harga=String.valueOf(jumlah);
        p.status = "0";
        p.user = User.session.getString("KTP");
        list.add(p);

        JSONArray arr = new JSONArray();
        for (int i=0; i<list.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("kode", list.get(i).kode);
            object.put("tanggal", list.get(i).tanggal);
            object.put("jadwal", list.get(i).jadwal);
            object.put("status", list.get(i).status);
            object.put("harga", list.get(i).harga);
            object.put("user", list.get(i).user);
            JSONArray penumpang = new JSONArray();
            for (int j=0; j<list.get(i).nama.size(); j++) {
                JSONObject o = new JSONObject();

                o.put("nama", list.get(i).nama.get(j));
                o.put("kursi", list.get(i).kursi.get(j));

                penumpang.put(o);
            }
            object.put("penumpang", penumpang);
            arr.put(object);
        }
//        System.out.println(arr.toString(2));
        this.writeToJson(arr.toString(2), "DataJson/booking.json");

        return rekening;
    }
    
    
    public ArrayList getDataByDate(String date) throws FileNotFoundException{
    	JSONArray invoice = readJson("DataJson/booking.json");
    
	    JSONObject object = null;
	    ArrayList array = new ArrayList();
	    HashMap map = new HashMap();
	    
	    int i = 0;
	    int cek = 0;
	    for (i = 0; i < invoice.length(); i++) {
	        object = new JSONObject(invoice.get(i).toString());
	        if (object.getString("tanggal").equals(date) && object.getString("status").equals("1")) {
	
	    		map = new HashMap();
	    		map.put("tanggal",object.getString("tanggal"));
	    		map.put("kodeJadwal",object.getString("jadwal"));
	    		map.put("harga",object.getString("harga"));
	    		array.add(map);
	        }
	    }
	    return array;
	}
	public ArrayList getDataByMonth(String month) throws FileNotFoundException{
    	JSONArray invoice = readJson("DataJson/booking.json");
        
	    JSONObject object = null;
	    ArrayList array = new ArrayList();
	    HashMap map = new HashMap();
	    String dateString;
	    Date date;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
	    
	    for (int i = 0; i < invoice.length(); i++) {
	        object = new JSONObject(invoice.get(i).toString());
	
	        try {
	            date = (Date) sdf.parse(object.getString("tanggal"));
	
	            dateString =  dateFormat.format(date);  
	        } catch (ParseException e) {
				dateString = "01-1970";
	        }
	        
	        if (dateString.equals(month)  && object.getString("status").equals("1")) {
	
	    		map = new HashMap();
	    		map.put("tanggal",object.getString("tanggal"));
	    		map.put("kodeJadwal",object.getString("jadwal"));
	    		map.put("harga",object.getString("harga"));
	    		array.add(map);
	        }
	    }
	    return array;
	}
	public ArrayList getDataByYear(String year) throws FileNotFoundException{
    	JSONArray invoice = readJson("DataJson/booking.json");
        
	    JSONObject object = null;
	    ArrayList array = new ArrayList();
	    HashMap map = new HashMap();
	    String dateString;
	    Date date;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
	    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
	    
	    for (int i = 0; i < invoice.length(); i++) {
	        object = new JSONObject(invoice.get(i).toString());
	        
	        try {
	            date = (Date) sdf.parse(object.getString("tanggal"));
	            
	            dateString =  dateFormat.format(date);  
	        } catch (ParseException e) {
	            date = new Date(1970,01,01);
	            
				dateString = "1970";
	        }
	        
	        if (dateString.equals(year) && object.getString("status").equals("1")) {
	
	    		map = new HashMap();
	
	            dateString =  monthFormat.format(date);  
	                
	    		map.put("tanggal",dateString);
	    		map.put("kodeJadwal",object.getString("jadwal"));
	    		map.put("harga",object.getString("harga"));
	    		array.add(map);
	        }
	    }
	    return array;
	}
}

class Pesan {
    public String kode;
    public String tanggal;
    public String jadwal;
    public String status;
    public String harga;    
    public String user;
    
    public ArrayList<String> nama = new ArrayList<>();
    public ArrayList<String> kursi = new ArrayList<>();
}

class Status1 {
    public String tanggal;
    public ArrayList<Status2> kereta = new ArrayList<>();
}

class Status2 {
    public String id;
    public ArrayList<Status3> duduk = new ArrayList<>();
}

class Status3 {
    public String gerbong;
    public String jenis;
    public ArrayList<String> kursi = new ArrayList<>();
}