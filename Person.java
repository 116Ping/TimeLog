package timeLogTry1;

public class Person{
	private String name;
	private String department;
	private String course;
	private String yrlvl;
	
	
	public Person(String name, String department, String course, String yrlvl) {
		
		this.name = name;
		this.department = department;
		this.course = course;
		this.yrlvl = yrlvl;
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
	
	
}
