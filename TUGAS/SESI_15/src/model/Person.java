package model;

public class Person {
    private String nama;
    private String noHp;

    public Person(){}

    public Person(String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNoHp() {
        return noHp;
    }
    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public void displayInfo(){
        System.out.println("Nama :" + nama);
        System.out.println("No HP :" + noHp);
    }
}
