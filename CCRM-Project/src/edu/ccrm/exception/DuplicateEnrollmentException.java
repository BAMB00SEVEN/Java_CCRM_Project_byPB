package edu.ccrm.exception;

// A custom unchecked exception [cite: 84] because this indicates a programming error
// (trying to enroll twice) rather than a recoverable condition.
public class DuplicateEnrollmentException extends RuntimeException {
    public DuplicateEnrollmentException(String message) {
        super(message);
    }
}
