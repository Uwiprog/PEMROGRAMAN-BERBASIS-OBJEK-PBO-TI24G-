package model;

public class Train {
    private int idkereta;
    private String namaKereta;
    private String asal;
    private String tujuan;
    private double harga;
    private int kursiTersedia;

    public Train(){}

    public Train(int idkereta, String namaKereta, String asal, String tujuan, double harga, int kursiTersedia) {
        this.idkereta = idkereta;
        this.namaKereta = namaKereta;
        this.asal = asal;
        this.tujuan = tujuan;
        this.harga = harga;
        this.kursiTersedia = kursiTersedia;
    }

    public int getIdKereta() {
        return idkereta;
    }
    public void setIdKereta(int idkereta) {
        this.idkereta = idkereta;
    }
    public String getNamaKereta() {
        return namaKereta;
    }
    public void setNamaKereta(String namaKereta) {
        this.namaKereta = namaKereta;
    }
    public String getAsal() {
        return asal;
    }
    public void setAsal(String asal) {
        this.asal = asal;
    }
    public String getTujuan() {
        return tujuan;
    }
    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
    public double getHarga() {
        return harga;
    }
    public void setHarga(double harga) {
        this.harga = harga;
    }
    public int getKursiTersedia() {
        return kursiTersedia;
    }
    public void setKursiTersedia(int kursiTersedia) {
        this.kursiTersedia = kursiTersedia;
    }

    @Override
    public String toString() {
        return String.format("| %-3d | %-15s | %-10s | %-10s | %,12.2f | %-5d |", 
        idkereta, namaKereta, asal, tujuan, harga, kursiTersedia); 
    }
}
