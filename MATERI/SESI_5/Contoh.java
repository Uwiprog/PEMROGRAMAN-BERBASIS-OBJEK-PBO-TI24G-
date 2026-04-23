package MATERI.SESI_5;

class Tabungan {
    private double saldo;

    void tambah(double jumlah){
        saldo += jumlah;
    }

    void ambil(double jumlah){
        saldo -= jumlah;
    }

    void InfoSaldo(){
        System.out.println("saldo :" + saldo);
    }
}

public class Contoh {
    public static void main(String[] args){
        Tabungan yulhan = new Tabungan();
        //yulhan.saldo = -10000;
        yulhan.tambah(100000);
        yulhan.ambil(5000);
        yulhan.InfoSaldo();
    }
}
