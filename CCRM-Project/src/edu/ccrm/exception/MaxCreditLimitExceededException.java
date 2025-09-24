package edu.ccrm.exception;

// A custom checked exception [cite: 84] because it's a predictable business rule violation
// that the calling code should handle gracefully (e.g., by informing the user).
public class MaxCreditLimitExceededException extends Exception {
    public MaxCreditLimitExceededException(String message) {
        super(message);
    }
}
