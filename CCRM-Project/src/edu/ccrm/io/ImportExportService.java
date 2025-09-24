package edu.ccrm.io;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

// Service to handle all file import/export operations using NIO.2 [cite: 90]
public class ImportExportService {

    private final Path dataDir = AppConfig.getInstance().getDataDirectory();

    public void importStudents(StudentService studentService, String filename) {
        Path filePath = dataDir.resolve(filename);
        try (var lines = Files.lines(filePath)) { // Using Stream to read lines [cite: 92]
            lines.forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Student student = new Student(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]);
                    studentService.addStudent(student);
                }
            });
            System.out.println("Students imported successfully from " + filename);
        } catch (IOException e) {
            System.err.println("Error reading student file: " + e.getMessage());
        }
    }
    
    // A similar method would exist for importCourses

    public void exportStudents(StudentService studentService, String filename) {
        Path filePath = dataDir.resolve(filename);
        List<String> lines = studentService.getAllStudents().stream()
            .map(s -> String.join(",", String.valueOf(s.getId()), s.getFullName(), s.getEmail(), s.getRegNo()))
            .collect(Collectors.toList());
        
        try {
            Files.createDirectories(dataDir); // Ensure directory exists
            Files.write(filePath, lines); // Using NIO.2 Files.write [cite: 92]
            System.out.println("Students exported successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Error exporting students: " + e.getMessage());
        }
    }
}
