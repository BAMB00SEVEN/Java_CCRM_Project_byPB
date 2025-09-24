package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        // Logic to prevent adding a student with a duplicate ID
        if (findById(student.getId()).isPresent()) {
            System.out.println("Error: Student with ID " + student.getId() + " already exists.");
            return;
        }
        students.add(student);
    }

    public Optional<Student> findById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }
    
    public List<Student> getAllStudents() {
        // Using a lambda expression for sorting [cite: 72]
        students.sort(Comparator.comparing(Student::getFullName));
        return students;
    }
    
    public void updateStudentEmail(int studentId, String newEmail) {
        findById(studentId).ifPresent(student -> {
            student.setEmail(newEmail);
            System.out.println("Student email updated.");
        });
    }

    public void deactivateStudent(int studentId) {
        findById(studentId).ifPresent(student -> student.setActive(false));
    }
}
