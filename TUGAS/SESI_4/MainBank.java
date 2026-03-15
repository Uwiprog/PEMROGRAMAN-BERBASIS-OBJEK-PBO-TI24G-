package TUGAS.SESI_4;

class Bank {
    void transferUang(int jumlah, String rekeningTujuan){
        System.out.print("Transfer sebesar " + jumlah + " ke rekening " + rekeningTujuan);
    } 

    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan){
        System.out.println("Transfer sebesar " + jumlah + "ke rekening " + rekeningTujuan + " di bank " + bankTujuan);
    }

    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita){
        System.out.println("Transfer sebesar " + jumlah + "ke rekening " + rekeningTujuan + "di bank " + bankTujuan + "dengan berita: " + berita);
    }

    void sukuBunga(){
        System.out.println("Suku bunga standar adalah 3%");
    }
}

class BankBNI extends Bank{
    @Override
    void sukuBunga(){
        System.out.println("Suku Bunga BNI adalah 4%");
    }

    @Override
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan){
        bankTujuan = "BNI";
        System.out.println("Transfer sebesar " + jumlah + "ke rekening " + rekeningTujuan + "di bank " + bankTujuan);
    }
}

class BankBCA extends Bank{
    @Override
    void sukuBunga(){
        System.out.println("Suku bunga BCA adalah 4.5%");
    }

    @Override
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan){
        bankTujuan = "BCA";
        System.out.println("Transfer sebesar " + jumlah + "ke rekening " + rekeningTujuan + "di bank " + bankTujuan);
    }
}

public class MainBank {
    public static void main(String[] args) {

        Bank bank = new Bank();
        BankBNI bni = new BankBNI();
        BankBCA bca = new BankBCA();

        System.out.println("=== Method Overloading ===");
        bank.transferUang(500000, "123456789");
        bank.transferUang(750000, "987654321", "Mandiri");
        bank.transferUang(1000000, "456789123", "BRI", "Bayar Hutang");

        bank.sukuBunga();

        System.out.println("\n=== Method Overriding ===");
        bni.sukuBunga();
        bni.transferUang(200000, "111222333", "Apa saja");

        bca.sukuBunga();
        bca.transferUang(300000, "444555666", "Apa saja");
    }
}
