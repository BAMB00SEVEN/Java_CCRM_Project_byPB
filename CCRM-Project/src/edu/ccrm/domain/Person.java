package edu.ccrm.domain;

// An abstract class demonstrating Abstraction. [cite: 61]
// It defines the common structure for any "person" in our system,
// but cannot be instantiated itself. Subclasses must provide details.
public abstract class Person {

    // 'protected' access allows subclasses to access these fields directly. [cite: 63]
    protected int id;
    protected String fullName;
    protected String email;

    public Person(int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    // An abstract method that forces subclasses to implement their own version.
    // This is a key part of achieving Polymorphism.
    public abstract String getProfileDetails();

    // Standard getters and setters, demonstrating Encapsulation. [cite: 59]
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
