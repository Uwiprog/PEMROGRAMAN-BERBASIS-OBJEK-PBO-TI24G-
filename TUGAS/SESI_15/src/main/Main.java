package main;

import database.DatabaseConnection;
import model.Passenger;
import model.Train;
import service.TicketService;
import utility.InputHelper;

import java.util.List;

public class Main {
    private static TicketService service;

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           SISTEM PEMESANAN TIKET KERETA API                  ║");
        System.out.println("║                  Berbasis Command Line                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        // Inisialisasi service (akan membuat koneksi database)
        service = new TicketService();

        boolean running = true;
        
        while (running) {
            tampilMenuUtama();
            int pilihan = InputHelper.inputInt("Pilih menu: ");
            
            switch (pilihan) {
                case 1:
                    menuJadwalKereta();
                    break;
                case 2:
                    menuPemesananTiket();
                    break;
                case 3:
                    running = false;
                    System.out.println("\nTerima kasih telah menggunakan Sistem Pemesanan Tiket Kereta!");
                    break;
                default:
                    System.out.println("✗ Pilihan tidak valid!");
            }
        }

        // Tutup koneksi database
        DatabaseConnection.closeConnection();
    }

    // ============================================
    // MENU UTAMA
    // ============================================
    private static void tampilMenuUtama() {
        System.out.println("\n┌──────────────────────────────┐");
        System.out.println("│         MENU UTAMA           │");
        System.out.println("├──────────────────────────────┤");
        System.out.println("│ 1. Jadwal Kereta             │");
        System.out.println("│ 2. Pemesanan Tiket           │");
        System.out.println("│ 3. Keluar                    │");
        System.out.println("└──────────────────────────────┘");
    }

    // ============================================
    // SUBMENU: JADWAL KERETA
    // ============================================
    private static void menuJadwalKereta() {
        boolean kembali = false;
        
        while (!kembali) {
            System.out.println("\n┌──────────────────────────────┐");
            System.out.println("│      JADWAL KERETA           │");
            System.out.println("├──────────────────────────────┤");
            System.out.println("│ 1. Tambah Jadwal Kereta      │");
            System.out.println("│ 2. Lihat Jadwal Kereta       │");
            System.out.println("│ 3. Kembali                   │");
            System.out.println("└──────────────────────────────┘");
            
            int pilihan = InputHelper.inputInt("Pilih menu: ");
            
            switch (pilihan) {
                case 1:
                    tambahJadwal();
                    break;
                case 2:
                    lihatJadwal();
                    break;
                case 3:
                    kembali = true;
                    break;
                default:
                    System.out.println("✗ Pilihan tidak valid!");
            }
        }
    }

    private static void tambahJadwal() {
        System.out.println("\n=== TAMBAH JADWAL KERETA ===");
        
        Train train = new Train();
        train.setNamaKereta(InputHelper.inputString("Nama Kereta: "));
        train.setAsal(InputHelper.inputString("Asal: "));
        train.setTujuan(InputHelper.inputString("Tujuan: "));
        train.setHarga(InputHelper.inputDouble("Harga Tiket: "));
        train.setKursiTersedia(InputHelper.inputInt("Jumlah Kursi Tersedia: "));
        
        if (service.tambahJadwal(train)) {
            System.out.println("✓ Jadwal kereta berhasil ditambahkan!");
        } else {
            System.out.println("✗ Gagal menambahkan jadwal!");
        }
        
        InputHelper.tekanEnter();
    }

    private static void lihatJadwal() {
        System.out.println("\n=== LIHAT JADWAL KERETA (Stored Procedure) ===");
        
        List<Train> trains = service.lihatJadwal();
        
        if (trains.isEmpty()) {
            System.out.println("Belum ada jadwal kereta.");
        } else {
            System.out.println("\n╔═════╦═════════════════╦════════════╦════════════╦══════════════╦═════════════════╗");
            System.out.println("║ ID  ║ Nama Kereta     ║ Asal       ║ Tujuan     ║ Harga        ║ Kursi Tersedia  ║");
            System.out.println("╠═════╬═════════════════╬════════════╬════════════╬══════════════╬═════════════════╣");
            
            for (Train t : trains) {
                System.out.println(t);
            }
            
            System.out.println("╚═════╩═════════════════╩════════════╩════════════╩══════════════╩═════════════════╝");
        }
        
        InputHelper.tekanEnter();
    }

    // ============================================
    // SUBMENU: PEMESANAN TIKET
    // ============================================
    private static void menuPemesananTiket() {
        boolean kembali = false;
        
        while (!kembali) {
            System.out.println("\n┌──────────────────────────────┐");
            System.out.println("│     PEMESANAN TIKET          │");
            System.out.println("├──────────────────────────────┤");
            System.out.println("│ 1. Pesan Tiket               │");
            System.out.println("│ 2. Lihat Daftar Pemesanan    │");
            System.out.println("│ 3. Kembali                   │");
            System.out.println("└──────────────────────────────┘");
            
            int pilihan = InputHelper.inputInt("Pilih menu: ");
            
            switch (pilihan) {
                case 1:
                    pesanTiket();
                    break;
                case 2:
                    service.lihatPemesanan();
                    InputHelper.tekanEnter();
                    break;
                case 3:
                    kembali = true;
                    break;
                default:
                    System.out.println("✗ Pilihan tidak valid!");
            }
        }
    }

    private static void pesanTiket() {
        System.out.println("\n=== PESAN TIKET (Function & Trigger) ===");
        
        // Tampilkan jadwal terlebih dahulu
        List<Train> trains = service.lihatJadwal();
        if (trains.isEmpty()) {
            System.out.println("Belum ada jadwal kereta. Tambahkan terlebih dahulu.");
            InputHelper.tekanEnter();
            return;
        }
        
        System.out.println("\nDaftar Kereta Tersedia:");
        for (Train t : trains) {
            System.out.printf("  [%d] %s (%s → %s) | Rp %,.2f | Kursi: %d%n",
                t.getIdKereta(), t.getNamaKereta(), t.getAsal(), 
                t.getTujuan(), t.getHarga(), t.getKursiTersedia());
        }
        
        // Input data penumpang (Inheritance & Polymorphism)
        System.out.println("\n--- Data Penumpang ---");
        Passenger passenger = new Passenger();
        passenger.setNama(InputHelper.inputString("Nama Penumpang: "));
        passenger.setNoHp(InputHelper.inputString("No HP: "));
        passenger.setEmail(InputHelper.inputString("Email: "));
        
        // Demonstrasi Polymorphism
        System.out.println("\n--- Info Penumpang ---");
        passenger.displayInfo();
        
        int idKereta = InputHelper.inputInt("\nPilih ID Kereta: ");
        int jumlah = InputHelper.inputInt("Jumlah Tiket: ");
        
        service.pesanTiket(passenger, idKereta, jumlah);
        
        InputHelper.tekanEnter();
    }
}
