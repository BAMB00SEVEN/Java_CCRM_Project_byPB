
package edu.ccrm.io;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

// Service to handle all file import/export operations using NIO.2
public class ImportExportService {

    private final Path dataDir = AppConfig.getInstance().getDataDirectory();

    public void importStudents(StudentService studentService, String filename) {
        Path filePath = dataDir.resolve(filename);
        if (!Files.exists(filePath)) {
            System.err.println("Error: Student file not found at " + filePath);
            return;
        }
        try (var lines = Files.lines(filePath)) { // Using Stream to read lines
            lines.forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Student student = new Student(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]);
                    studentService.addStudent(student);
                }
            });
            System.out.println("Students imported successfully from " + filename);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading student file: " + e.getMessage());
        }
    }

    public void exportStudents(StudentService studentService, String filename) {
        Path filePath = dataDir.resolve(filename);
        List<String> lines = studentService.getAllStudents().stream()
            .map(s -> String.join(",", String.valueOf(s.getId()), s.getFullName(), s.getEmail(), s.getRegNo()))
            .collect(Collectors.toList());
        
        try {
            Files.createDirectories(dataDir); // Ensure directory exists
            Files.write(filePath, lines); // Using NIO.2 Files.write
            System.out.println("Students exported successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Error exporting students: " + e.getMessage());
        }
    }

    /**
     * Imports courses from a CSV file.
     * The expected format is: code,title,credits,department,semester
     */
    public void importCourses(CourseService courseService, String filename) {
        Path filePath = dataDir.resolve(filename);
        if (!Files.exists(filePath)) {
            System.err.println("Error: Course file not found at " + filePath);
            return;
        }
        try (var lines = Files.lines(filePath)) {
            lines.forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    try {
                        // Parse each part of the CSV line
                        String code = parts[0].trim();
                        String title = parts[1].trim();
                        int credits = Integer.parseInt(parts[2].trim());
                        String department = parts[3].trim();
                        Semester semester = Semester.valueOf(parts[4].trim().toUpperCase()); // Convert string to enum

                        // Use the builder to create the course object
                        Course course = new Course.CourseBuilder(code, title)
                                            .credits(credits)
                                            .department(department)
                                            .inSemester(semester)
                                            .build();
                        courseService.addCourse(course);
                    } catch (IllegalArgumentException e) {
                        // This catches errors from parsing numbers or invalid enum values
                        System.err.println("Skipping invalid line in course file: " + line);
                    }
                }
            });
            System.out.println("Courses imported successfully from " + filename);
        } catch (IOException e) {
            System.err.println("Error reading course file: " + e.getMessage());
        }
    }

    /**
     * Exports the current list of courses to a CSV file.
     */
    public void exportCourses(CourseService courseService, String filename) {
        Path filePath = dataDir.resolve(filename);
        List<String> lines = courseService.getAllCourses().stream()
            .map(c -> String.join(",",
                    c.getCode(),
                    c.getTitle(),
                    String.valueOf(c.getCredits()),
                    c.getDepartment(),
                    c.getSemester().toString()))
            .collect(Collectors.toList());
    
        try {
            Files.createDirectories(dataDir);
            Files.write(filePath, lines);
            System.out.println("Courses exported successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Error exporting courses: " + e.getMessage());
        }
    }
}
