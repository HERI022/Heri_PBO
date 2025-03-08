import java.util.Scanner;

public class UserInterface {

    public static void tampilkanMenu() {
        System.out.println();
        System.out.println("+================+");
        System.out.println("|   Pilih Menu:  |");
        System.out.println("+----------------+");
        System.out.println("|  [C] : Create  |");
        System.out.println("|  [R] : Read    |");
        System.out.println("|  [U] : Update  |");
        System.out.println("|  [D] : Delete  |");
        System.out.println("|  [X] : Exit    |");
        System.out.println("+================+");
    }

    public static void main(String[] args) {
        Database db = new Database();
        System.out.println(" APLIKASI SIMPLE CRUD TEXT DATABASE");
        while (true){
            tampilkanMenu();
            Scanner sc = new Scanner(System.in);
            System.out.print("Pilih   :   ");
            String pilihan = sc.nextLine();
            pilihan = pilihan.toUpperCase();

            switch (pilihan){
                case "C" :
                    System.out.println("INFO : Anda memilih menu Create ");
                    System.out.println("------------------------------------");
                    System.out.println("INPUT DATA BARU");
                    System.out.print("NIM          :");
                    String nim = sc.nextLine();
                    System.out.print("NAMA       :");
                    String nama = sc.nextLine();
                    System.out.print("ALAMAT       :");
                    String alamat = sc.nextLine();
                    System.out.print("SEMESTER     :");
                    int semester = sc.nextInt();
                    System.out.print("SKS          :");
                    int sks = sc.nextInt();
                    System.out.print("IPK          :");
                    double ipk = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("--------------------------------------");

                    boolean status = db.insert(nim, nama, alamat, semester, sks,ipk);
                    if (status == true){
                        System.out.println("DATA BARU BERHASIL DITAMBAHKAN");
                    }else {
                        System.out.println("NIM " + nim +"sudah ada di database");
                        System.err.println("GAGAL MENAMBAHKAN DATA BARU");
                    }

                    break;
                case "R" :
                    System.out.println("INFO : Anda memilih menu read ");
                    db.view();

                    break;
                case "U" :
                    System.out.println("INFO: Anda menilih menu Update");
                    db.view();
                    System.out.print("Input key (NIM Mahasiswa yang akan diupdate): ");
                    String key = sc.nextLine();
                    int index = db.search(key);
                    if (index >= 0){
                        System.out.println("Anda akan meng-update data " + db.getData().get(index));
                        System.out.println("------------------------------------");
                        System.out.println("INPUT DATA BARU");
                        System.out.print("NIM          :");
                        nim = sc.nextLine();
                        System.out.print("NAMA       :");
                        nama = sc.nextLine();
                        System.out.print("ALAMAT       :");
                        alamat = sc.nextLine();
                        System.out.print("SEMESTER     :");
                        semester = sc.nextInt();
                        System.out.print("SKS          :");
                        sks = sc.nextInt();
                        System.out.print("IPK          :");
                        ipk = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("--------------------------------------");
                        status = db.update(index,nim, nama, alamat, semester, sks,ipk);
                        if (status==true){
                            System.out.println("DATA BERHASIL DIPERBAHARUI");
                        }else {
                            System.err.println("GAGAL MEMPERBARUI DATA");
                        }
                        System.out.println("--------------------------------------");
                    }else {
                        System.err.println("Mahasiswa dengan NIM : " + key + "tidak ada di database");
                    }

                    break;
                case "D" :
                    System.out.println("INFO: Anda memilih menu DELETE");
                    db.view();
                    System.out.println("Input key(NIM Mahasiswa yang akan dihapus): ");
                    key = sc.nextLine();
                    index = db.search(key);
                    if (index >= 0){
                        System.out.println("APAKAH ANDA YAKIN AKAN MENGHAPUS DATA "+ db.getData().get(index));
                        System.out.print("Pilih :");
                        pilihan = sc.nextLine();
                        if (pilihan.equalsIgnoreCase("Y")){
                            status = db.delete(index);
                            if (status==true){
                                System.out.println("DATA BERHASIL DIHAPUS");
                            }else {
                                System.err.println("GAGAL MEMNGHAPUS DATA");
                            }
                            System.out.println("--------------------------------------");
                        }
                    }else {
                        System.err.println("Mahasiswa dengan NIM : " + key + "tidak ada di database");
                    }
                    break;
                case "X" :
                    System.out.println("INFO: Anda memilih menu EXIT");
                    System.out.println("APAKAH ANDA YAKIN AKAN KELUAR DARI APLIKASI? Y/N");
                    System.out.println("Pilih   :  ");
                    pilihan = sc.nextLine();
                    if (pilihan.equalsIgnoreCase("Y")){
                        System.exit(0);
                    }

                    break;

            }
        }

    }
}
