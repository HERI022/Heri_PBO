public class Mahasiswa {
    private String Nim;
    private String Nama;
    private String Alamat;
    private int Semester;
    private int Sks;
    private double Ipk;

    public Mahasiswa(String nim, String nama, String alamat, int semester, int sks, double ipk) {
       this. Nim = nim;
       this. Nama = nama;
       this. Alamat = alamat;
       this.Semester = semester;
       this. Sks = sks;
       this. Ipk = ipk;


    }

    public String getNim() {
        return Nim;
    }

    public String getNama() {
        return Nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public int getSemester() {
        return Semester;
    }

    public int getSks() {
        return Sks;
    }

    public double getIpk() {
        return Ipk;
    }

    @Override
    public String toString() {
        return "Mahasiswa{" +
                "Nim='" + Nim + '\'' +
                ", Nama='" + Nama + '\'' +
                ", Alamat='" + Alamat + '\'' +
                ", Semester=" + Semester +
                ", Sks=" + Sks +
                ", Ipk=" + Ipk +
                '}';
    }
}
