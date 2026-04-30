

import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Laptop dynabook = new Toshiba();
        laptopUser budi = new laptopUser(dynabook);

        System.out.println("=== SIMULASI KONTROL LAPTOP ===");
        System.out.println("Ketik perintah berikut untuk mengontrol laptop:");
        System.out.println("[ON]  -> Menyalakan laptop");
        System.out.println("[OFF] -> Mematikan laptop");
        System.out.println("[UP]  -> Menambah volume");
        System.out.println("[DOWN]-> Mengurangi volume");
        System.out.println("[EXIT]-> Keluar dari program");

        while (true) {
            System.out.print("Perintah: ");
            String perintah = sc.nextLine().toUpperCase();

            if (perintah.equals("ON")){
                budi.turnOnLaptop();
            } 
            else if (perintah.equals("OFF")){
                budi.turnOffLaptop();
            }
            else if (perintah.equals("UP")){
                budi.makeLaptopLouder();
            }
            else if (perintah.equals("DOWN")){
                budi.makeLaptopSilence();
            }
            else if (perintah.equals("EXIT")){
                System.out.println("Mengakhiri Program...");
                break;
            }
            else{
                System.out.println("Perintah tidak dikenali! ketik ulang!");
            }
        }
        sc.close();
    }
}
