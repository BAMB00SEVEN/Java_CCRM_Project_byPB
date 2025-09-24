package edu.ccrm.domain;

// A class designed using the Builder Pattern [cite: 81] for flexible object creation.
public final class Course { // 'final' to prevent inheritance

    // private final fields to promote immutability after creation.
    private final String code;
    private final String title;
    private final int credits;
    private final String department;
    private Instructor instructor;
    private final Semester semester;

    // The constructor is private, forcing use of the Builder.
    private Course(CourseBuilder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.department = builder.department;
        this.instructor = builder.instructor;
        this.semester = builder.semester;
    }

    // Overriding toString() provides a developer-friendly representation.
    @Override
    public String toString() {
        String instructorName = (instructor != null) ? instructor.getFullName() : "TBD";
        return String.format("Course{Code=%s, Title='%s', Credits=%d, Instructor=%s}",
                code, title, credits, instructorName);
    }

    // Getters for all fields
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public Instructor getInstructor() { return instructor; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }
    
    // Setter for instructor, as they can be assigned later.
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // This is a static nested class[cite: 67], a key part of the Builder pattern.
    public static class CourseBuilder {
        // Required parameters
        private final String code;
        private final String title;

        // Optional parameters with default values
        private int credits = 3;
        private String department = "General";
        private Instructor instructor = null;
        private Semester semester = Semester.FALL;

        public CourseBuilder(String code, String title) {
            // Using an assertion to check for a valid state.
            // Remember to run with -ea flag to enable them! [cite: 89]
            assert code != null && !code.trim().isEmpty() : "Course code cannot be null or empty";
            this.code = code;
            this.title = title;
        }

        public CourseBuilder credits(int credits) {
            this.credits = credits;
            return this; // Returning the builder for method chaining
        }

        public CourseBuilder department(String department) {
            this.department = department;
            return this;
        }

        public CourseBuilder taughtBy(Instructor instructor) {
            this.instructor = instructor;
            return this;
        }
        
        public CourseBuilder inSemester(Semester semester) {
            this.semester = semester;
            return this;
        }

        // The final build step creates the Course instance.
        public Course build() {
            return new Course(this);
        }
    }
}
