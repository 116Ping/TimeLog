package timeLogTry1;


public class Person {
    private String name;
    private String department;
    private String course;
    private String yrlvl;
    private String imagePath;

    public Person(String name, String department, String course, String yrlvl, String imagePath) {
        this.name = name;
        this.department = department;
        this.course = course;
        this.yrlvl = yrlvl;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getCourse() {
        return course;
    }

    public String getYrlvl() {
        return yrlvl;
    }

    public String getImagePath() {
        return imagePath;
    }
}