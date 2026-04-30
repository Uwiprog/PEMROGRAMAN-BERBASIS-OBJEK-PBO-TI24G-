package project_pbo.model;

public class Teacher extends Person{
    private int numCourses = 0;
    private String[] courses = new String[5];

    public Teacher(String name, String address) {
        super(name, address);
    }

    @Override
    public String toString() {
        return "Teacher: " + getName() + "(" + getAddress() + ")";
    }

    public boolean addCourse(String course) {
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) return false;
        }
        if (numCourses < 5) {
            courses[numCourses++] = course;
            return true;
        }
        return false;
    }

    public boolean removeCourse(String course) {
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                for (int j = i; j < numCourses - 1; j++) {
                    courses[j] = courses[j + 1];
                }
                numCourses--;
                return true;
            }
        }
        return false;
    }
}
