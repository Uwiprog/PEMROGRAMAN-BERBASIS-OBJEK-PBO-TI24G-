package service;


import database.DatabaseConnection;
import model.Passenger;
import model.Ticket;
import model.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private Connection conn;

    public TicketService() {
        this.conn = DatabaseConnection.getConnection();
    }

    // ============================================
    // CRUD: CREATE - Tambah Jadwal Kereta
    // ============================================
    public boolean tambahJadwal(Train train) {
        String sql = "INSERT INTO trains (nama_kereta, asal, tujuan, harga, kursi_tersedia) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, train.getNamaKereta());
            ps.setString(2, train.getAsal());
            ps.setString(3, train.getTujuan());
            ps.setDouble(4, train.getHarga());
            ps.setInt(5, train.getKursiTersedia());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error tambah jadwal: " + e.getMessage());
            return false;
        }
    }

    // ============================================
    // CRUD: READ - Lihat Jadwal (Menggunakan Stored Procedure)
    // ============================================
    public List<Train> lihatJadwal() {
        List<Train> trains = new ArrayList<>();
        
        try (CallableStatement cs = conn.prepareCall("{CALL sp_lihat_jadwal()}")) {
            ResultSet rs = cs.executeQuery();
            
            while (rs.next()) {
                Train train = new Train();
                train.setIdKereta(rs.getInt("id_kereta"));
                train.setNamaKereta(rs.getString("nama_kereta"));
                train.setAsal(rs.getString("asal"));
                train.setTujuan(rs.getString("tujuan"));
                train.setHarga(rs.getDouble("harga"));
                train.setKursiTersedia(rs.getInt("kursi_tersedia"));
                trains.add(train);
            }
        } catch (SQLException e) {
            System.err.println("Error lihat jadwal: " + e.getMessage());
        }
        return trains;
    }

    // ============================================
    // CRUD: READ - Cari Kereta berdasarkan ID
    // ============================================
    public Train cariKereta(int idKereta) {
        String sql = "SELECT * FROM trains WHERE id_kereta = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idKereta);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Train train = new Train();
                train.setIdKereta(rs.getInt("id_kereta"));
                train.setNamaKereta(rs.getString("nama_kereta"));
                train.setAsal(rs.getString("asal"));
                train.setTujuan(rs.getString("tujuan"));
                train.setHarga(rs.getDouble("harga"));
                train.setKursiTersedia(rs.getInt("kursi_tersedia"));
                return train;
            }
        } catch (SQLException e) {
            System.err.println("Error cari kereta: " + e.getMessage());
        }
        return null;
    }

    // ============================================
    // PEMESANAN TIKET (Menggunakan Function & Trigger)
    // ============================================
    public boolean pesanTiket(Passenger passenger, int idKereta, int jumlahTiket) {
        // Cek ketersediaan kursi
        Train train = cariKereta(idKereta);
        if (train == null) {
            System.out.println("✗ Kereta tidak ditemukan!");
            return false;
        }
        
        if (train.getKursiTersedia() < jumlahTiket) {
            System.out.println("✗ Kursi tidak mencukupi! Tersedia: " + train.getKursiTersedia());
            return false;
        }

        // Hitung total harga menggunakan Function MySQL
        double totalHarga = hitungTotalHarga(train.getHarga(), jumlahTiket);

        String sql = "INSERT INTO tickets (nama_penumpang, id_kereta, jumlah_tiket, total_harga) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, passenger.getNama());
            ps.setInt(2, idKereta);
            ps.setInt(3, jumlahTiket);
            ps.setDouble(4, totalHarga);
            
            boolean success = ps.executeUpdate() > 0;
            
            if (success) {
                // Trigger otomatis mengurangi kursi_tersedia
                System.out.println("✓ Pemesanan berhasil!");
                System.out.println("  Total Harga: Rp " + String.format("%,.2f", totalHarga));
                System.out.println("  Kursi tersedia berkurang otomatis (Trigger aktif)");
            }
            return success;
            
        } catch (SQLException e) {
            System.err.println("Error pesan tiket: " + e.getMessage());
            return false;
        }
    }

    // ============================================
    // FUNCTION: Memanggil function MySQL
    // ============================================
    private double hitungTotalHarga(double harga, int jumlah) {
        String sql = "SELECT fn_hitung_total(?, ?) as total";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, harga);
            ps.setInt(2, jumlah);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.err.println("Error hitung total: " + e.getMessage());
        }
        return harga * jumlah; // Fallback manual
    }

    // ============================================
    // VIEW: Lihat Daftar Pemesanan Lengkap
    // ============================================
    public void lihatPemesanan() {
        String sql = "SELECT * FROM vw_pemesanan_lengkap ORDER BY id_tiket";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                           DAFTAR PEMESANAN TIKET (VIEW)                                              ║");
            System.out.println("╠═════╦═══════════════════╦═══════════════════╦════════════╦════════════╦══════════════╦════════════════╦═════════════════════╣");
            System.out.println("║ ID  ║ Nama Penumpang    ║ Nama Kereta       ║ Asal       ║ Tujuan     ║ Jumlah       ║ Total Harga    ║ Tanggal Pesan       ║");
            System.out.println("╠═════╬═══════════════════╬═══════════════════╬════════════╬════════════╬══════════════╬════════════════╬═════════════════════╣");
            
            boolean adaData = false;
            while (rs.next()) {
                adaData = true;
                System.out.printf("║ %-3d ║ %-17s ║ %-17s ║ %-10s ║ %-10s ║ %-12d ║ Rp %-10s ║ %-19s ║%n",
                    rs.getInt("id_tiket"),
                    rs.getString("nama_penumpang"),
                    rs.getString("nama_kereta"),
                    rs.getString("asal"),
                    rs.getString("tujuan"),
                    rs.getInt("jumlah_tiket"),
                    String.format("%,.2f", rs.getDouble("total_harga")),
                    rs.getTimestamp("tanggal_pesan")
                );
            }
            
            if (!adaData) {
                System.out.println("║                              Belum ada data pemesanan                                                ║");
            }
            
            System.out.println("╚═════╩═══════════════════╩═══════════════════╩════════════╩════════════╩══════════════╩════════════════╩═════════════════════╝");
            
        } catch (SQLException e) {
            System.err.println("Error lihat pemesanan: " + e.getMessage());
        }
    }
}
