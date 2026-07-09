import java.sql.*;
import java.util.Scanner;

public class TokoRetailCLI {
    
    // Konfigurasi database - sesuaikan dengan setting MySQL Anda
    private static final String DB_URL = "jdbc:mysql://localhost:3306/toko_retail";
    private static final String DB_USER = "root";      // ganti sesuai user MySQL Anda
    private static final String DB_PASS = "";          // ganti sesuai password MySQL Anda
    
    private Connection conn;
    private Scanner scanner;
    
    public TokoRetailCLI() {
        scanner = new Scanner(System.in);
        try {
            // Load driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL tidak ditemukan!");
            System.out.println("Pastikan mysql-connector-java.jar sudah ditambahkan ke classpath.");
            System.exit(1);
        } catch (SQLException e) {
            System.out.println("Gagal koneksi ke database: " + e.getMessage());
            System.exit(1);
        }
    }
    
    // Method untuk menampilkan garis pembatas
    private void garis(int panjang) {
        for (int i = 0; i < panjang; i++) {
            System.out.print("=");
        }
        System.out.println();
    }
    
    // Method untuk menampilkan menu utama
    private void tampilMenu() {
        System.out.println();
        garis(40);
        System.out.println("           MENU TOKO RETAIL");
        garis(40);
        System.out.println("  1. Tampil Semua Data");
        System.out.println("  2. Tambah Data");
        System.out.println("  3. Cari Data");
        System.out.println("  4. Ubah Data");
        System.out.println("  5. Hapus Data");
        System.out.println("  0. Keluar");
        garis(40);
    }
    
    // ============================================
    // MENU 1: TAMPIL SEMUA DATA
    // ============================================
    private void tampilSemuaData() {
        try {
            String sql = "SELECT * FROM barang ORDER BY kode";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.println();
            garis(60);
            System.out.println("           DAFTAR BARANG TOKO RETAIL");
            garis(60);
            System.out.printf(" %-3s | %-8s | %-20s | %-10s | %-5s%n", 
                              "#", "Kode", "Nama Barang", "Harga", "Stok");
            garis(60);
            
            int no = 1;
            int total = 0;
            
            while (rs.next()) {
                System.out.printf(" %-3d | %-8s | %-20s | %-10d | %-5d%n",
                    no++,
                    rs.getString("kode"),
                    rs.getString("nama_barang"),
                    rs.getInt("harga"),
                    rs.getInt("stok")
                );
                total++;
            }
            
            garis(60);
            System.out.println("Total: " + total + " barang");
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // ============================================
    // MENU 2: TAMBAH DATA
    // ============================================
    private void tambahData() {
        System.out.println();
        garis(40);
        System.out.println("           TAMBAH DATA BARANG");
        garis(40);
        
        System.out.print("Masukkan Kode Barang    : ");
        String kode = scanner.nextLine().trim().toUpperCase();
        
        System.out.print("Masukkan Nama Barang    : ");
        String nama = scanner.nextLine().trim();
        
        System.out.print("Masukkan Harga Barang   : ");
        int harga = 0;
        try {
            harga = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Harga harus berupa angka!");
            return;
        }
        
        System.out.print("Masukkan Stok Barang    : ");
        int stok = 0;
        try {
            stok = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Stok harus berupa angka!");
            return;
        }
        
        try {
            String sql = "INSERT INTO barang (kode, nama_barang, harga, stok) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kode);
            pstmt.setString(2, nama);
            pstmt.setInt(3, harga);
            pstmt.setInt(4, stok);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("\nData berhasil ditambahkan!");
            }
            pstmt.close();
            
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate")) {
                System.out.println("\nError: Kode barang sudah ada!");
            } else {
                System.out.println("\nError: " + e.getMessage());
            }
        }
    }
    
    // ============================================
    // MENU 3: CARI DATA
    // ============================================
    private void cariData() {
        System.out.println();
        garis(40);
        System.out.println("           CARI DATA BARANG");
        garis(40);
        
        System.out.print("Masukkan Kode/Nama Barang: ");
        String keyword = scanner.nextLine().trim();
        
        try {
            String sql = "SELECT * FROM barang WHERE kode LIKE ? OR nama_barang LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println();
            garis(60);
            System.out.println("           HASIL PENCARIAN");
            garis(60);
            System.out.printf(" %-3s | %-8s | %-20s | %-10s | %-5s%n", 
                              "#", "Kode", "Nama Barang", "Harga", "Stok");
            garis(60);
            
            int no = 1;
            boolean found = false;
            
            while (rs.next()) {
                found = true;
                System.out.printf(" %-3d | %-8s | %-20s | %-10d | %-5d%n",
                    no++,
                    rs.getString("kode"),
                    rs.getString("nama_barang"),
                    rs.getInt("harga"),
                    rs.getInt("stok")
                );
            }
            
            garis(60);
            
            if (!found) {
                System.out.println("Data tidak ditemukan!");
            } else {
                System.out.println("Ditemukan: " + (no - 1) + " barang");
            }
            
            rs.close();
            pstmt.close();
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // ============================================
    // MENU 4: UBAH DATA
    // ============================================
    private void ubahData() {
        System.out.println();
        garis(40);
        System.out.println("           UBAH DATA BARANG");
        garis(40);
        
        System.out.print("Masukkan Kode Barang yang akan diubah: ");
        String kode = scanner.nextLine().trim().toUpperCase();
        
        try {
            // Cek apakah data ada
            String cekSql = "SELECT * FROM barang WHERE kode = ?";
            PreparedStatement cekStmt = conn.prepareStatement(cekSql);
            cekStmt.setString(1, kode);
            ResultSet rs = cekStmt.executeQuery();
            
            if (!rs.next()) {
                System.out.println("\nData tidak ditemukan!");
                rs.close();
                cekStmt.close();
                return;
            }
            
            // Tampilkan data lama
            System.out.println("\nData saat ini:");
            System.out.println("Nama Barang : " + rs.getString("nama_barang"));
            System.out.println("Harga       : " + rs.getInt("harga"));
            System.out.println("Stok        : " + rs.getInt("stok"));
            rs.close();
            cekStmt.close();
            
            System.out.println("\nMasukkan data baru (kosongkan jika tidak diubah):");
            
            System.out.print("Nama Barang Baru  : ");
            String namaBaru = scanner.nextLine().trim();
            
            System.out.print("Harga Baru        : ");
            String hargaInput = scanner.nextLine().trim();
            
            System.out.print("Stok Baru         : ");
            String stokInput = scanner.nextLine().trim();
            
            // Build update query
            StringBuilder sqlBuilder = new StringBuilder("UPDATE barang SET ");
            boolean first = true;
            
            if (!namaBaru.isEmpty()) {
                sqlBuilder.append("nama_barang = ?");
                first = false;
            }
            if (!hargaInput.isEmpty()) {
                if (!first) sqlBuilder.append(", ");
                sqlBuilder.append("harga = ?");
                first = false;
            }
            if (!stokInput.isEmpty()) {
                if (!first) sqlBuilder.append(", ");
                sqlBuilder.append("stok = ?");
            }
            
            sqlBuilder.append(" WHERE kode = ?");
            
            PreparedStatement pstmt = conn.prepareStatement(sqlBuilder.toString());
            
            int paramIndex = 1;
            if (!namaBaru.isEmpty()) {
                pstmt.setString(paramIndex++, namaBaru);
            }
            if (!hargaInput.isEmpty()) {
                pstmt.setInt(paramIndex++, Integer.parseInt(hargaInput));
            }
            if (!stokInput.isEmpty()) {
                pstmt.setInt(paramIndex++, Integer.parseInt(stokInput));
            }
            pstmt.setString(paramIndex, kode);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("\nData berhasil diubah!");
            }
            pstmt.close();
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Harga/Stok harus berupa angka!");
        }
    }
    
    // ============================================
    // MENU 5: HAPUS DATA
    // ============================================
    private void hapusData() {
        System.out.println();
        garis(40);
        System.out.println("           HAPUS DATA BARANG");
        garis(40);
        
        System.out.print("Masukkan Kode Barang yang akan dihapus: ");
        String kode = scanner.nextLine().trim().toUpperCase();
        
        try {
            // Cek apakah data ada
            String cekSql = "SELECT * FROM barang WHERE kode = ?";
            PreparedStatement cekStmt = conn.prepareStatement(cekSql);
            cekStmt.setString(1, kode);
            ResultSet rs = cekStmt.executeQuery();
            
            if (!rs.next()) {
                System.out.println("\nData tidak ditemukan!");
                rs.close();
                cekStmt.close();
                return;
            }
            
            // Tampilkan data yang akan dihapus
            System.out.println("\nData yang akan dihapus:");
            System.out.println("Kode        : " + rs.getString("kode"));
            System.out.println("Nama Barang : " + rs.getString("nama_barang"));
            System.out.println("Harga       : " + rs.getInt("harga"));
            System.out.println("Stok        : " + rs.getInt("stok"));
            rs.close();
            cekStmt.close();
            
            System.out.print("\nYakin ingin menghapus? (y/n): ");
            String konfirmasi = scanner.nextLine().trim().toLowerCase();
            
            if (konfirmasi.equals("y") || konfirmasi.equals("ya")) {
                String sql = "DELETE FROM barang WHERE kode = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, kode);
                
                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("\nData berhasil dihapus!");
                }
                pstmt.close();
            } else {
                System.out.println("\nPenghapusan dibatalkan.");
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // ============================================
    // MAIN PROGRAM
    // ============================================
    public void jalankan() {
        boolean running = true;
        
        while (running) {
            tampilMenu();
            System.out.print("Pilihan : ");
            String pilihan = scanner.nextLine().trim();
            
            switch (pilihan) {
                case "1":
                    tampilSemuaData();
                    break;
                case "2":
                    tambahData();
                    break;
                case "3":
                    cariData();
                    break;
                case "4":
                    ubahData();
                    break;
                case "5":
                    hapusData();
                    break;
                case "0":
                    running = false;
                    System.out.println("\nTerima kasih! Program selesai.");
                    break;
                default:
                    System.out.println("\nPilihan tidak valid! Silakan pilih 0-5.");
            }
        }
        
        // Tutup koneksi database
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error menutup koneksi: " + e.getMessage());
        }
        
        scanner.close();
    }
    
    public static void main(String[] args) {
        TokoRetailCLI app = new TokoRetailCLI();
        app.jalankan();
    }
}