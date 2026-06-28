package MATERI.SESI_11;

import java.io.*;
public class BuatFile {
    public static void main(String[] args){
        try{
            File f = new File("File.txt");
            if (f.createNewFile()) {
                System.out.println("File created" + f.getName());
            } else {
                System.out.println("File Already exist");
            }
        } catch 
    }
}
