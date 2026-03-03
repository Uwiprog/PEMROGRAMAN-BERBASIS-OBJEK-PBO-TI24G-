package TUGAS.SESI_2;

public class HandPhone {
    String jenis_hp;
    int tahun_pembuatan;

    public void setDataHP(String jenis, int tahun_pembuatan){
        jenis_hp = jenis;
        this.tahun_pembuatan = tahun_pembuatan;
    }

    public String getJenisHP(){
        return jenis_hp;
    }

    public int getTahunPembuatan(){
        return tahun_pembuatan;
    }

    public static void main(String [] args){
        HandPhone hp = new HandPhone();
        hp.setDataHP("Xiaom", 2006);
        System.out.println(hp.getJenisHP());
        System.out.println(hp.getTahunPembuatan());
    }
}
