package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// The Student class demonstrates Inheritance by extending Person. [cite: 60]
public class Student extends Person {

    private String regNo;
    private LocalDate registrationDate;
    private boolean active;
    private List<Enrollment> enrollments;

    public Student(int id, String fullName, String email, String regNo) {
        // The 'super' keyword calls the constructor of the parent class (Person). [cite: 64]
        super(id, fullName, email);
        this.regNo = regNo;
        this.registrationDate = LocalDate.now(); // Using the Date/Time API. [cite: 94]
        this.active = true;
        this.enrollments = new ArrayList<>();
    }

    // This is the implementation of the abstract method from the Person class.
    @Override
    public String getProfileDetails() {
        return String.format("Student Profile:\n  ID: %d\n  RegNo: %s\n  Name: %s\n  Email: %s\n  Status: %s",
                id, regNo, fullName, email, active ? "Active" : "Inactive");
    }

    // Overriding toString() for a clean, readable representation of the object. [cite: 77]
    @Override
    public String toString() {
        return String.format("Student[ID=%d, RegNo=%s, Name=%s]", id, regNo, fullName);
    }
    
    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }
    
    // --- Getters and Setters ---
    public String getRegNo() { return regNo; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public List<Enrollment> getEnrollments() { return enrollments; }
}
