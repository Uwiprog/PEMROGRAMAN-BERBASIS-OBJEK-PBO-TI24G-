package TUGAS.SESI_3;

import java.util.ArrayList;
import java.util.Scanner;

// Class Mahasiswa untuk merepresentasikan data mahasiswa
class Mahasiswa {
    private String nim;
    private String nama;
    private int nilai;
    private String grade;
    private boolean lulus;
    
    // Constructor
    public Mahasiswa(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
        this.grade = hitungGrade();
    }
    
    // Method untuk menghitung grade berdasarkan nilai
    private String hitungGrade() {
        if (nilai >= 80 && nilai <= 100) {
            this.lulus = true;
            return "A";
        } else if (nilai >= 70 && nilai <= 79) {
            this.lulus = true;
            return "B";
        } else if (nilai >= 60 && nilai <= 69) {
            this.lulus = true;
            return "C";
        } else if (nilai >= 50 && nilai <= 59) {
            this.lulus = false;
            return "D";
        } else if (nilai >= 0 && nilai < 50) {
            this.lulus = false;
            return "E";
        } else {
            return "INVALID";
        }
    }
    
    // Getter methods
    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public int getNilai() { return nilai; }
    public String getGrade() { return grade; }
    public boolean isLulus() { return lulus; }
    
    // Method untuk menampilkan data mahasiswa
    public void tampilkanData() {
        System.out.println("NIM  : " + nim);
        System.out.println("Nama : " + nama);
        System.out.println("Nilai: " + nilai);
        System.out.println("Grade: " + grade);
        System.out.println("======================================");
    }
}

// Class utama untuk mengelola sistem penilaian
public class SistemPenilaian {
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private Scanner scanner;
    
    public SistemPenilaian() {
        this.daftarMahasiswa = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
    // Method untuk input data mahasiswa
    public void inputData() {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        
        System.out.print("Masukkan Nilai: ");
        int nilai = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer
        
        // Validasi nilai
        if (nilai < 0 || nilai > 100) {
            System.out.println("Input nilai anda salah");
            return;
        }
        
        Mahasiswa mhs = new Mahasiswa(nim, nama, nilai);
        daftarMahasiswa.add(mhs);
        System.out.println("Data berhasil ditambahkan!\n");
    }
    
    // Method untuk menampilkan semua data
    public void tampilkanSemuaData() {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
            return;
        }
        
        for (Mahasiswa mhs : daftarMahasiswa) {
            mhs.tampilkanData();
        }
    }
    
    // Method untuk menampilkan laporan statistik
    public void tampilkanLaporan() {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Belum ada data untuk dilaporkan.");
            return;
        }
        
        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;
        int jumlahA = 0, jumlahB = 0, jumlahC = 0, jumlahD = 0, jumlahE = 0;
        double totalNilai = 0;
        
        StringBuilder namaLulus = new StringBuilder();
        StringBuilder namaTidakLulus = new StringBuilder();
        StringBuilder namaA = new StringBuilder();
        StringBuilder namaB = new StringBuilder();
        StringBuilder namaC = new StringBuilder();
        StringBuilder namaD = new StringBuilder();
        StringBuilder namaE = new StringBuilder();
        
        for (Mahasiswa mhs : daftarMahasiswa) {
            totalNilai += mhs.getNilai();
            
            // Hitung lulus/tidak lulus
            if (mhs.isLulus()) {
                jumlahLulus++;
                if (namaLulus.length() > 0) namaLulus.append(", ");
                namaLulus.append(mhs.getNama());
            } else {
                jumlahTidakLulus++;
                if (namaTidakLulus.length() > 0) namaTidakLulus.append(", ");
                namaTidakLulus.append(mhs.getNama());
            }
            
            // Hitung per grade
            switch (mhs.getGrade()) {
                case "A":
                    jumlahA++;
                    if (namaA.length() > 0) namaA.append(", ");
                    namaA.append(mhs.getNama());
                    break;
                case "B":
                    jumlahB++;
                    if (namaB.length() > 0) namaB.append(", ");
                    namaB.append(mhs.getNama());
                    break;
                case "C":
                    jumlahC++;
                    if (namaC.length() > 0) namaC.append(", ");
                    namaC.append(mhs.getNama());
                    break;
                case "D":
                    jumlahD++;
                    if (namaD.length() > 0) namaD.append(", ");
                    namaD.append(mhs.getNama());
                    break;
                case "E":
                    jumlahE++;
                    if (namaE.length() > 0) namaE.append(", ");
                    namaE.append(mhs.getNama());
                    break;
            }
        }
        
        double rataRata = totalNilai / daftarMahasiswa.size();
        
        // Tampilkan laporan
        System.out.println("Jumlah Mahasiswa: " + daftarMahasiswa.size());
        
        System.out.println("Jumlah Mahasiswa yg Lulus: " + jumlahLulus + 
                          (jumlahLulus > 0 ? " yaitu " + namaLulus.toString() : ""));
        
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus: " + jumlahTidakLulus + 
                          (jumlahTidakLulus > 0 ? " yaitu " + namaTidakLulus.toString() : ""));
        
        System.out.println("Jumlah Mahasiswa dengan Nilai A = " + jumlahA + 
                          (jumlahA > 0 ? " yaitu " + namaA.toString() : ""));
        
        System.out.println("Jumlah Mahasiswa dengan Nilai B = " + jumlahB + 
                          (jumlahB > 0 ? " yaitu " + namaB.toString() : ""));
        
        System.out.println("Jumlah Mahasiswa dengan Nilai C = " + jumlahC + 
                          (jumlahC > 0 ? " yaitu " + namaC.toString() : ""));
        
        System.out.println("Jumlah Mahasiswa dengan Nilai D = " + jumlahD + 
                          (jumlahD > 0 ? " yaitu " + namaD.toString() : ""));
        
        System.out.println("Jumlah Mahasiswa dengan Nilai E = " + jumlahE + 
                          (jumlahE > 0 ? " yaitu " + namaE.toString() : ""));
        
        // Format rata-rata dengan 2 desimal
        System.out.printf("Rata-rata nilai mahasiswa adalah: %.2f%n", rataRata);
    }
    
    // Menu utama
    public void menu() {
        int pilihan;
        do {
            System.out.println("\n=== SISTEM PENILAIAN MAHASISWA ===");
            System.out.println("1. Input Data Mahasiswa");
            System.out.println("2. Tampilkan Semua Data");
            System.out.println("3. Tampilkan Laporan Statistik");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer
            
            switch (pilihan) {
                case 1:
                    inputData();
                    break;
                case 2:
                    tampilkanSemuaData();
                    break;
                case 3:
                    tampilkanLaporan();
                    break;
                case 0:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 0);
    }
    
    public static void main(String[] args) {
        SistemPenilaian sistem = new SistemPenilaian();
        sistem.menu();
    }
}