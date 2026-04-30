package project_pbo.model;

public class Student extends Person{
    private int numCourses = 0;
    private String[] courses = new String[30];
    private int[] grades = new int[30];

    public Student(String name, String address) {
        super(name, address);
    }

    @Override
    public String toString() {
        return "Student: " + getName() + "(" + getAddress() + ")";
    }

    public void addCourseGrade(String course, int grade) {
        if (numCourses < 30) {
            courses[numCourses] = course;
            grades[numCourses] = grade;
            numCourses++;
        }
    }

    public void printGrades() {
        for (int i = 0; i < numCourses; i++) {
            System.out.println("  " + courses[i] + ": " + grades[i]);
        }
    }

    public double getAverageGrade() {
        if (numCourses == 0) return 0;
        int sum = 0;
        for (int i = 0; i < numCourses; i++) sum += grades[i];
        return (double) sum / numCourses;
    }
}
