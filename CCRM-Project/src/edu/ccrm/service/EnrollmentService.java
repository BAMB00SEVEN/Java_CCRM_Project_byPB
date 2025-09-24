package edu.ccrm.service;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.exception.MaxCreditLimitExceededException;

public class EnrollmentService {

    public void enrollStudent(Student student, Course course) 
        throws MaxCreditLimitExceededException { // Declaring that it throws a checked exception [cite: 85]

        // Rule 1: Check for duplicate enrollment
        boolean alreadyEnrolled = student.getEnrollments().stream()
            .anyMatch(e -> e.getCourse().getCode().equals(course.getCode()));
        if (alreadyEnrolled) {
            throw new DuplicateEnrollmentException("Student already enrolled in course " + course.getCode());
        }

        // Rule 2: Check for max credit limit
        int currentCredits = student.getEnrollments().stream()
            .filter(e -> e.getCourse().getSemester() == course.getSemester())
            .mapToInt(e -> e.getCourse().getCredits())
            .sum();
        
        int maxCredits = AppConfig.getInstance().getMaxCreditsPerSemester();

        if (currentCredits + course.getCredits() > maxCredits) {
            throw new MaxCreditLimitExceededException(
                "Cannot enroll. Exceeds max credit limit of " + maxCredits + " for the semester."
            );
        }

        // If all rules pass, create the enrollment
        Enrollment newEnrollment = new Enrollment(student, course);
        student.addEnrollment(newEnrollment);
        System.out.printf("Success: %s enrolled in %s.\n", student.getFullName(), course.getCode());
    }

    public void recordGrade(Student student, Course course, Grade grade) {
        student.getEnrollments().stream()
            .filter(e -> e.getCourse().getCode().equals(course.getCode()))
            .findFirst()
            .ifPresent(enrollment -> enrollment.setGrade(grade));
    }
}
