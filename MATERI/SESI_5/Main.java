package MATERI.SESI_5;

public class Main {
    public static void main(String[] args) {
        BankAccount Wiwiw = new BankAccount(1000000, 
        "8790094922",
        "Wiwiw");
        Wiwiw.deposit (50000);
        Wiwiw.withdraw (5000);
        System.out.println(Wiwiw);
    }
    
}
