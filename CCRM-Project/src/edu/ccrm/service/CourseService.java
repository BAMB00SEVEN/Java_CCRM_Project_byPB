package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        if (findByCode(course.getCode()).isPresent()) {
            System.out.println("Error: Course with code " + course.getCode() + " already exists.");
            return;
        }
        courses.add(course);
    }

    public Optional<Course> findByCode(String code) {
        return courses.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst();
    }
    
    public List<Course> getAllCourses() {
        return courses;
    }
    
    // Method demonstrating Stream API for filtering [cite: 23]
    public List<Course> findCoursesByDepartment(String department) {
        return courses.stream()
                .filter(course -> course.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    public List<Course> findCoursesBySemester(Semester semester) {
        return courses.stream()
                .filter(course -> course.getSemester() == semester)
                .collect(Collectors.toList());
    }
}
