package MATERI.SESI_11;

import java.io.*;
public class InfoFile {
    public static void main(String[] args){
        File f = new File("file.txt");

        if (f.exists()) {
            System.out.println("File name:" + f.getName());
            System.out.println("Absolute Path:" + f.getAbsolutePath())
            System.out.println("Writable: ");
        }
    }
}
