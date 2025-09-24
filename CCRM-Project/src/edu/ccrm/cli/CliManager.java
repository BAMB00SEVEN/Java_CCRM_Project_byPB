package edu.ccrm.cli;

import edu.ccrm.domain.*;
import edu.ccrm.exception.MaxCreditLimitExceededException;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.*;

import java.util.InputMismatchException;
import java.util.Scanner;

// The main class that drives the console application.
public class CliManager {

    // Services that contain the business logic
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();
    private static final TranscriptService transcriptService = new TranscriptService();
    private static final ImportExportService ioService = new ImportExportService();
    private static final BackupService backupService = new BackupService();

    public static void main(String[] args) {
        // Initialize with some data
        initializeData();

        System.out.println("Welcome to the Campus Course & Records Manager (CCRM)!");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // The main application loop [cite: 54]
        mainLoop: // A label for a jump statement [cite: 37]
        while (running) {
            printMainMenu();
            int choice = getIntInput(scanner);

            // Enhanced switch statement [cite: 36]
            switch (choice) {
                case 1 -> manageStudents(scanner);
                case 2 -> manageCourses(scanner);
                case 3 -> manageEnrollment(scanner);
                case 4 -> printTranscript(scanner);
                case 8 -> runBackup();
                case 9 -> ioMenu(scanner);
                case 0 -> {
                    System.out.println("Exiting application. Goodbye! 👋");
                    break mainLoop; // Demonstrating a labeled break
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    
    private static void printMainMenu() {
        System.out.println("\n--- CCRM Main Menu ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Enroll Student in Course");
        System.out.println("4. Print Student Transcript");
        System.out.println("8. Create Data Backup");
        System.out.println("9. Import/Export Data");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
    
    // --- Sub-menu handlers ---

    private static void manageStudents(Scanner scanner) {
        System.out.println("\n-- Student Management --");
        System.out.println("1. Add New Student");
        System.out.println("2. List All Students");
        System.out.print("Choose an option: ");
        int choice = getIntInput(scanner);
        if (choice == 1) {
            System.out.print("Enter Student ID: ");
            int id = getIntInput(scanner);
            System.out.print("Enter Full Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Registration Number: ");
            String regNo = scanner.nextLine();
            studentService.addStudent(new Student(id, name, email, regNo));
        } else if (choice == 2) {
            System.out.println("\n-- All Students --");
            studentService.getAllStudents().forEach(System.out::println);
        }
    }

    private static void manageCourses(Scanner scanner) {
        System.out.println("\n-- Course Management --");
        System.out.println("1. Add New Course");
        System.out.println("2. List All Courses");
        System.out.println("3. Search Courses by Department");
        System.out.print("Choose an option: ");
        int choice = getIntInput(scanner);
        
        switch (choice) {
            case 1 -> { // Using Course.Builder pattern here
                System.out.print("Enter Course Code (e.g., CS101): ");
                String code = scanner.nextLine();
                System.out.print("Enter Course Title: ");
                String title = scanner.nextLine();
                System.out.print("Enter Credits: ");
                int credits = getIntInput(scanner);
                System.out.print("Enter Department: ");
                String dept = scanner.nextLine();
                Course newCourse = new Course.CourseBuilder(code, title)
                                       .credits(credits)
                                       .department(dept)
                                       .build();
                courseService.addCourse(newCourse);
                System.out.println("Course added: " + newCourse.getTitle());
            }
            case 2 -> {
                System.out.println("\n-- All Courses --");
                courseService.getAllCourses().forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("Enter department to search for: ");
                String dept = scanner.nextLine();
                System.out.println("\n-- Courses in " + dept + " --");
                courseService.findCoursesByDepartment(dept).forEach(System.out::println);
            }
        }
    }
    
    private static void manageEnrollment(Scanner scanner) {
        System.out.print("Enter Student ID to enroll: ");
        int studentId = getIntInput(scanner);
        System.out.print("Enter Course Code to enroll in: ");
        String courseCode = scanner.nextLine();

        var studentOpt = studentService.findById(studentId);
        var courseOpt = courseService.findByCode(courseCode);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            try {
                // Using try-catch to handle our custom checked exception [cite: 85]
                enrollmentService.enrollStudent(studentOpt.get(), courseOpt.get());
            } catch (MaxCreditLimitExceededException e) {
                System.err.println("Enrollment failed: " + e.getMessage());
            } catch (RuntimeException e) { // Catches the unchecked exception
                System.err.println("Enrollment failed: " + e.getMessage());
            }
        } else {
            System.out.println("Student or Course not found.");
        }
    }
    
    private static void printTranscript(Scanner scanner) {
        System.out.print("Enter Student ID to generate transcript: ");
        int studentId = getIntInput(scanner);
        studentService.findById(studentId)
            .ifPresentOrElse(
                student -> System.out.println(transcriptService.generateTranscript(student)),
                () -> System.out.println("Student not found.")
            );
    }
    
    private static void ioMenu(Scanner scanner) {
        System.out.println("\n-- Import/Export --");
        System.out.println("1. Import Students from CSV");
        System.out.println("2. Export Students to CSV");
        System.out.print("Choose an option: ");
        int choice = getIntInput(scanner);

        if (choice == 1) {
            ioService.importStudents(studentService, "students.csv");
        } else if (choice == 2) {
            ioService.exportStudents(studentService, "students_export.csv");
        }
    }
    
    private static void runBackup() {
        backupService.createBackup();
    }
    
    // A helper utility to handle integer input gracefully
    private static int getIntInput(Scanner scanner) {
        int input = -1;
        try {
            input = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        } finally {
            scanner.nextLine(); // Consume the rest of the line
        }
        return input;
    }
    
    // Initialize with some dummy data for easy testing
    private static void initializeData() {
        studentService.addStudent(new Student(1, "Alice Johnson", "alice@example.com", "S001"));
        studentService.addStudent(new Student(2, "Bob Williams", "bob@example.com", "S002"));
        courseService.addCourse(new Course.CourseBuilder("CS101", "Intro to Java")
                                    .credits(4)
                                    .department("Computer Science")
                                    .inSemester(Semester.FALL)
                                    .build());
        courseService.addCourse(new Course.CourseBuilder("MA201", "Linear Algebra")
                                    .credits(3)
                                    .department("Mathematics")
                                    .inSemester(Semester.FALL)
                                    .build());
    }
}
