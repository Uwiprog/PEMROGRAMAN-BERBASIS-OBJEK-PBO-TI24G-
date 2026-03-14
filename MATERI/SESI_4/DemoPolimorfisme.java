package MATERI.SESI_4;
    //Super Class
    class Produk {
        protected String nama;
        protected int harga;

        public Produk(String nama, int harga) {
            this.nama = nama;
            this.harga = harga;
        }

        public String tampilInfo() {
            return "[UMUM]    " + nama + " | Harga: " + harga;
        }
    }

    //Sub class 1
    class ProdukFisik extends Produk {
        private double beratKg;

        public ProdukFisik(String nama, int harga, double beratKg) {
            super(nama, harga);
            this.beratKg = beratKg;
        }

        @Override //Overriding
        public String tampilInfo() {
            return "[FISIK]   " + nama + " | Harga: " + harga + " | Berat: " + beratKg + "KG";
        }
    }

    //Sub class 2
    class ProdukDigital extends Produk {
        private String masaAktif;

        public ProdukDigital(String nama, int harga, String masaAktif) {
            super(nama, harga);
            this.masaAktif = masaAktif;
        }

        @Override //Overriding
        public String tampilInfo() {
            return "[DIGITAL] " + nama + " | Harga: " + harga + " | Masa Aktif: " + masaAktif;
        }
    }

    //Sub class 3
    class Kasir {
        public int hitungBayar(int harga, int qty) {
            return harga = qty;
        }

        public int hitungBayar(int harga, int qty, double diskonPersen) {
            int total = harga * qty;
            return (int) (total - (total * diskonPersen / 100));
        }
    }

public class DemoPolimorfisme{
    public static void main(String[] harga) {

        System.out.println("=== DEMO OVERRIDING ===");
        System.out.println("Memanggil tampilInfo() dari masing-masing objek:\n");

        Produk p1 = new Produk("Kaos Polos", 85000);
        ProdukFisik p2 = new ProdukFisik("Sepatu lari", 350000, 0.0);
        ProdukDigital p3 = new ProdukDigital("Miscrosoft 365", 600000, "1 Tahun");

        System.out.println(p1.tampilInfo());
        System.out.println(p2.tampilInfo());
        System.out.println(p3.tampilInfo());

        System.out.println("\n === DEMO OVERLOADING ===");
        System.out.println("memanggil hitungBayar() dengan argumen berbeda:\n");

        Kasir kasir = new Kasir();

        int total1 = kasir.hitungBayar(50000, 2);
        int total2 = kasir.hitungBayar(50000, 2, 15);

        System.out.println("hitungBayar(50000, 2): " + total1);
        System.out.println("hitungBayar(50000, 2, 15)" + total2);
    }
}
