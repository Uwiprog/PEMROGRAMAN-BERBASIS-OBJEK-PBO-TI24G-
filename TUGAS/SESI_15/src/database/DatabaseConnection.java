package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/kereta_db";
    private static final String USER = "root";      // Sesuaikan dengan MySQL Anda
    private static final String PASSWORD = "";      // Sesuaikan dengan MySQL Anda
    
    private static Connection connection = null;

    // Private constructor untuk mencegah instansiasi langsung
    private DatabaseConnection() {}

    /**
     * Method untuk mendapatkan koneksi database
     * Menggunakan Singleton pattern
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Load driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Buat koneksi
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✓ Koneksi database berhasil!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("✗ Driver MySQL tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("✗ Gagal koneksi ke database: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Method untuk menutup koneksi
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Koneksi database ditutup.");
            }
        } catch (SQLException e) {
            System.err.println("✗ Gagal menutup koneksi: " + e.getMessage());
        }
    }
}
