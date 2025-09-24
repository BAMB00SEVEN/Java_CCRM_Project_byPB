package edu.ccrm.domain;

import java.time.LocalDate;

// A simple data class to link a Student to a Course with a Grade.
public class Enrollment {
    private final Student student;
    private final Course course;
    private final LocalDate enrollmentDate;
    private Grade grade; // Can be null until assigned

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = LocalDate.now();
        this.grade = null; // No grade initially
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "student=" + student.getFullName() +
                ", course=" + course.getCode() +
                ", grade=" + (grade != null ? grade : "Not Graded") +
                '}';
    }
}
