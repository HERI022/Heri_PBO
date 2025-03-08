
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private ArrayList<Mahasiswa> data = new ArrayList<>();
    private String filename = "src/Data.csv";
    private Path path = Path.of(filename);

    public Database() {
        open();
    }

    public ArrayList<Mahasiswa> getData() {
        return data;
    }

    public void open() {
        try {
            List<String> lines = Files.readAllLines(path);
            data = new ArrayList<>();
            if (lines.size() > 1) { // Pastikan ada setidaknya satu baris data selain header
                for (int i = 1; i < lines.size(); i++) {
                    String line = lines.get(i);
                    String[] element = line.split(",");
                    if (element.length == 6) { // Pastikan ada 6 kolom
                        String Nim = element[0];
                        String Nama = element[1];
                        String Alamat = element[2];
                        int Semester = Integer.parseInt(element[3]);
                        int Sks = Integer.parseInt(element[4]);
                        double Ipk = Double.parseDouble(element[5]);
                        Mahasiswa mhs = new Mahasiswa(Nim, Nama, Alamat, Semester, Sks, Ipk);
                        data.add(mhs);
                    } else {
                        System.err.println("Baris data tidak valid: " + line);
                    }
                }
            } else {
                System.err.println("File CSV kosong atau hanya memiliki header.");
            }
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void save() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nim,Nama,Alamat (kota),semster,sks,ipk\n");
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                Mahasiswa mhs = data.get(i);
                String line = mhs.getNim() + "," + mhs.getNama() + "," + mhs.getAlamat() + "," + mhs.getSemester() + "," + mhs.getSks() + "," + mhs.getIpk() + "\n";
                sb.append(line);
            }
        }
        try {
            Files.writeString(path, sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    viewnya

    public boolean insert(String nim, String nama, String alamat, int semester, int sks, double ipk) {
        boolean status = true;
        //cek primary key
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getNim().equalsIgnoreCase(nim)) {
                    status = false;
                    break;
                }
            }
        }
        //cek primary key
        if (status == true) {
            Mahasiswa mhs = new Mahasiswa(nim, nama, alamat, semester, sks, ipk);
            data.add(mhs);
            save();
        }
        return status;
    }

    public int search(String nim) {
        int index = -1;
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getNim().equalsIgnoreCase(nim)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    public boolean update(int index, String nim, String nama, String alamat, int semester, int sks, double ipk) {
        boolean status = false;
        if (!data.isEmpty()) {
            //update
            if (index >= 0 && index < data.size()) {
                Mahasiswa mhs = new Mahasiswa(nim, nama, alamat, semester, sks, ipk);
                data.set(index, mhs);
                save();
                status = true;
            }
        }
        return status;
    }

    public boolean delete(int index) {
        boolean status = false;
        if (!data.isEmpty()) {
            data.remove(index);
            status = true;
        }
        return status;
    }
}
