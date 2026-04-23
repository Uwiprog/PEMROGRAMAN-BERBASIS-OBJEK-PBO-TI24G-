package TUGAS.SESI_5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Mahasiswa mhs = new Mahasiswa();
        Dosen dsn = new Dosen();
        MataKuliah mk = new MataKuliah();

        System.out.print("Masukkan Nama Mahasiswa: ");
        mhs.setNama(input.nextLine());

        System.out.print("Masukkan NIM: ");
        mhs.setNim(input.nextLine());

        System.out.print("Masukkan Nama Dosen: ");
        dsn.setNamaDosen(input.nextLine());

        System.out.print("Masukkan Mata Kuliah: ");
        mk.setNamaMK(input.nextLine());

        System.out.println("\n=== DATA ===");
        System.out.println("Mahasiswa: " + mhs.getNama());
        System.out.println("NIM: " + mhs.getNim());
        System.out.println("Dosen: " + dsn.getNamaDosen());
        System.out.println("Mata Kuliah: " + mk.getNamaMK());
    }
}
