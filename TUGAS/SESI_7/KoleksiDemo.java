package TUGAS.SESI_7;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class KoleksiDemo {
    public static void main(String[] args) {
        // ===== ARRAYLIST =====
        System.out.println("=== ARRAYLIST ===");
        ArrayList<String> mahasiswa = new ArrayList<>();
        
        mahasiswa.add("Budi");
        mahasiswa.add("Ani");
        mahasiswa.add("Citra");
        
        System.out.println("Data Mahasiswa: " + mahasiswa);
        System.out.println("Index 0: " + mahasiswa.get(0));
        mahasiswa.remove(1);
        System.out.println("Setelah remove index 1: " + mahasiswa);
        
        // ===== ARRAYDEQUE =====
        System.out.println("\n=== ARRAYDEQUE ===");
        ArrayDeque<String> antrian = new ArrayDeque<>();
        
        antrian.addFirst("Pelanggan 1");
        antrian.addLast("Pelanggan 2");
        antrian.addFirst("Pelanggan 3"); // masuk paling depan
        
        System.out.println("Antrian: " + antrian);
        System.out.println("Poll First: " + antrian.pollFirst()); // ambil dari depan
        System.out.println("Poll Last: " + antrian.pollLast());   // ambil dari belakang
        System.out.println("Sisa Antrian: " + antrian);
    }
}
