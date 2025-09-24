package edu.ccrm.util;

// This interface defines a contract for any domain object that needs to be
// converted to and from a simple string format, like a CSV line.
// It's a great example of defining expected behavior without specifying
// the implementation.
public interface Persistable {

    /**
     * Converts the object's state into a comma-separated string.
     * @return A string representing the object in CSV format.
     */
    String toCsvString();

}
