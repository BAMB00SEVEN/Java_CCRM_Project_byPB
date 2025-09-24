package edu.ccrm.domain;

// Another example of Inheritance.
public class Instructor extends Person {
    private String department;
    private String title;

    public Instructor(int id, String fullName, String email, String department, String title) {
        super(id, fullName, email);
        this.department = department;
        this.title = title;
    }

    @Override
    public String getProfileDetails() {
        return String.format("Instructor Profile:\n  ID: %d\n  Name: %s (%s)\n  Department: %s",
                id, fullName, title, department);
    }
    
    @Override
    public String toString() {
        return String.format("Instructor[ID=%d, Name=%s, Dept=%s]", id, fullName, department);
    }

    public String getDepartment() {
        return department;
    }
}
