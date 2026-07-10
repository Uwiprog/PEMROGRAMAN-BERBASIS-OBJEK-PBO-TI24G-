package model;

public class Ticket {
    private int idTicket;
    private String namaPenumpang;
    private int idKereta;
    private int jumlahTicket;
    private double totalHarga;
    private String tanggalPesan;

    public Ticket(){}

    public Ticket(int idTicket, String namaPenumpang, int idKereta, int jumlahTicket, 
        double totalHarga, String tanggalPesan) {
        this.idTicket = idTicket;
        this.namaPenumpang = namaPenumpang;
        this.idKereta = idKereta;
        this.jumlahTicket = jumlahTicket;
        this.totalHarga = totalHarga;
        this.tanggalPesan = tanggalPesan;
    }

    public int getIdTicket() {
        return idTicket;
    }
    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }
    public String getNamaPenumpang() {
        return namaPenumpang;
    }
    public void setNamaPenumpang(String namaPenumpang) {
        this.namaPenumpang = namaPenumpang;
    }
    public int getIdKereta() {
        return idKereta;
    }
    public void setIdKereta(int idKereta) {
        this.idKereta = idKereta;
    }
    public int getJumlahTicket() {
        return jumlahTicket;
    }
    public void setJumlahTicket(int jumlahTicket) {
        this.jumlahTicket = jumlahTicket;
    }
    public double getTotalHarga() {
        return totalHarga;
    }
    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }
    public String getTanggalPesan() {
        return tanggalPesan;
    }
    public void setTanggalPesan(String tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }

    public void displayInfo(){
        System.out.println("=== Informasi Ticket ===");
        System.out.println("ID Ticket :" + idTicket);
        System.out.println("Nama Penumpang :" + namaPenumpang);
        System.out.println("ID Kereta :" + idKereta);
        System.out.println("Jumlah Ticket :" + jumlahTicket);
        System.out.println("Total Harga :" + totalHarga);
        System.out.println("Tanggal Pesan :" + tanggalPesan);
    }
}