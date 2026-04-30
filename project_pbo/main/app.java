package project_pbo.main;

import project_pbo.model.Student;
import project_pbo.model.Teacher;
import project_pbo.model.Person;
import project_pbo.service.AkademikService;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ===== GENERIC COLLECTION =====
        AkademikService<Student> studentService = new AkademikService<>();
        AkademikService<Teacher> teacherService = new AkademikService<>();

        // Input Student
        System.out.println("=== INPUT MAHASISWA ===");
        System.out.print("Nama: ");
        String nama = sc.nextLine();
        System.out.print("Alamat: ");
        String alamat = sc.nextLine();

        Student mhs = new Student(nama, alamat);
        mhs.addCourseGrade("PBO", 85);
        mhs.addCourseGrade("Basis Data", 90);
        studentService.add(mhs);

        // Input Teacher
        System.out.println("\n=== INPUT DOSEN ===");
        System.out.print("Nama: ");
        String namaDosen = sc.nextLine();
        System.out.print("Alamat: ");
        String alamatDosen = sc.nextLine();

        Teacher dosen = new Teacher(namaDosen, alamatDosen);
        dosen.addCourse("PBO");
        dosen.addCourse("Struktur Data");
        teacherService.add(dosen);

        // Display
        System.out.println("\n=== DATA MAHASISWA ===");
        studentService.displayAll();
        System.out.println("Rata-rata: " + mhs.getAverageGrade());

        System.out.println("\n=== DATA DOSEN ===");
        teacherService.displayAll();

        // Polymorphism
        System.out.println("\n=== POLYMORPHISM ===");
        Person p1 = new Student("Budi", "Jakarta");
        Person p2 = new Teacher("Pak Dosen", "Bandung");
        System.out.println(p1);
        System.out.println(p2);

        sc.close();
    }
}
