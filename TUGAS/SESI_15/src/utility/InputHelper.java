package utility;

import java.util.Scanner;

public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Method untuk input String
     */
    public static String inputString(String label) {
        System.out.print(label);
        return scanner.nextLine().trim();
    }

    /**
     * Method untuk input Integer dengan exception handling
     */
    public static int inputInt(String label) {
        while (true) {
            try {
                System.out.print(label);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("✗ Input harus berupa angka! Coba lagi.");
            }
        }
    }

    /**
     * Method untuk input Double dengan exception handling
     */
    public static double inputDouble(String label) {
        while (true) {
            try {
                System.out.print(label);
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("✗ Input harus berupa angka desimal! Coba lagi.");
            }
        }
    }

    /**
     * Method untuk menunggu enter
     */
    public static void tekanEnter() {
        System.out.print("\nTekan ENTER untuk melanjutkan...");
        scanner.nextLine();
    }
}
