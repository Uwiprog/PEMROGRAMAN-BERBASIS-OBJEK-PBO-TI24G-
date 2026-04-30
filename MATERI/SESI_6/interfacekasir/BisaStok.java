package MATERI.SESI_6.interfacekasir;

public interface BisaStok {
    int STOK_MINIMUM = 5;

    boolean cekStok();
    void kurangiStok();
}
