class Mahasiswa {
    // Atribut Properti
    // "Private hanya bisa diakses dari dalam class ini sendiri"
    private String nama;
    private int umur;

    // Constructor
    //Constructor akan otomatis dipanggil saat subject baru dibuat denga "new"
    //Parameter di dalam constructor digunakan untuk mengisi nilai awal atribut
    Mahasiswa(String nama, int umur) {
        // kata kunci "this" dipakai untuk membedakan
        // antara atribut class (this.nama) dengan parameter method (nama)

        this.nama = nama;
        this.umur = umur;
    }

    // Getter
    // Getter dipakai untuk "membaca" / mengambil nilai atribut
    public String getNama() {
        return this.nama; // "this.nama" Merujuk ke atribut di class
    }

    public int getUmur() {
        return this.umur;
    }

    //Setter
    //Setter dipakai untuk mengubah nilai atribut dari luar class
    public void setNama(String nama) {
        //Gunakan "this" agar jelas bahwa yang dikiri adalah atribut yang di kanan parameter
        this.nama = nama;
    }

    public void setUmur(int umur) {
        if (umur > 0){
            this.umur = umur;
        }
    }

    //method tambahan untuk menampilkan info mahasiswa
    public void tampilkanInfo() {
        System.out.println("Nama : " + this.nama);
        System.out.println("Umur : " + this.umur + " Tahun");
    }
}

public class DemoMahasiswa{
    public static void main(String[] args) {
        //membuat object
        // memanggil constructor: Mahasiswa(String nama, int Umur)
        Mahasiswa mhs1 = new Mahasiswa("Budi", 20);
        Mahasiswa mhs2 = new Mahasiswa("Siti", 19);

        //Menggunakan getter untuk membaca nilai
        System.out.println("Data awal:");
        System.out.println("Mahasiswa 1: " + mhs1.getNama() + ", " + mhs1.getUmur() + " tahun");
        System.out.println("Mahasiswa 2: " + mhs2.getNama() + ", " + mhs2.getUmur() + " tahun");

        //Menggunakan setter untuk mengubah nilai
        mhs1.setNama("Budi Santoso");
        mhs1.setUmur(21);

        mhs2.setNama("Siti rahmawati");
        mhs2.setUmur(20);

        //Memanggil Method lain di class
        System.out.println("\nSetelah diubah menggunakan setter: ");
        mhs1.tampilkanInfo();
        mhs2.tampilkanInfo();
    }
} 