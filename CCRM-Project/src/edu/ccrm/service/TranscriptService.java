package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import java.util.List;

public class TranscriptService {

    // This method demonstrates polymorphism. It takes a Student object
    // and uses its specific data to generate a transcript. The `toString()`
    // overrides in Course and Grade are called implicitly. [cite: 28, 62]
    public String generateTranscript(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- OFFICIAL TRANSCRIPT ---\n");
        sb.append(student.getProfileDetails()).append("\n");
        sb.append("---------------------------\n");

        if (student.getEnrollments().isEmpty()) {
            sb.append("No courses enrolled.\n");
        } else {
            for (Enrollment e : student.getEnrollments()) {
                String gradeStr = e.getGrade() != null ? e.getGrade().toString() : "IP"; // IP = In Progress
                sb.append(String.format("  %-10s %-30s Credits: %d, Grade: %s\n",
                    e.getCourse().getCode(),
                    e.getCourse().getTitle(),
                    e.getCourse().getCredits(),
                    gradeStr));
            }
        }
        
        sb.append("\nGPA: ").append(calculateGpa(student)).append("\n");
        sb.append("--- END OF TRANSCRIPT ---\n");

        return sb.toString();
    }

    private double calculateGpa(Student student) {
        List<Enrollment> gradedEnrollments = student.getEnrollments().stream()
            .filter(e -> e.getGrade() != null).toList();

        if (gradedEnrollments.isEmpty()) {
            return 0.0;
        }

        double totalPoints = gradedEnrollments.stream()
            .mapToDouble(e -> e.getGrade().getGradePoint() * e.getCourse().getCredits())
            .sum();
        
        int totalCredits = gradedEnrollments.stream()
            .mapToInt(e -> e.getCourse().getCredits())
            .sum();
            
        return totalCredits > 0 ? (totalPoints / totalCredits) : 0.0;
    }
}
