# CCRM - Campus Course & Records Manager 🎓

This is my project for the Programming in Java course. It's a console-based application called CCRM, designed to help a small institute manage students, courses, enrollments, and grades. The whole thing is built with Java SE and runs right from the command line.

---

### ✨ Core Features

* [cite_start]**👤 Student Management:** Add new students, update their info, view their profiles, and generate transcripts[cite: 16, 17].
* [cite_start]**📚 Course Management:** Create and manage courses, assign instructors, and search/filter them using the Streams API[cite: 20, 21, 23].
* [cite_start]**✍️ Enrollment & Grading:** Handle student enrollment in courses, record marks, and automatically compute GPAs[cite: 24, 25, 26].
* [cite_start]**💾 File Operations:** Import and export data using simple CSV-like files and create timestamped backups of all records using Java NIO.2[cite: 29, 30, 31, 32].
* [cite_start]**🖥️ Interactive CLI:** A user-friendly, menu-driven interface to access all the app's features[cite: 35].

---

### 🛠️ Tech Stack

* **Language:** Java (JDK 11 or newer recommended)
* **Core APIs:** Java SE, NIO.2, Streams API, Date/Time API
* [cite_start]**Design Patterns:** Singleton, Builder [cite: 78, 80, 81]
* [cite_start]**IDE:** Developed using Eclipse IDE [cite: 46]

---

### 🚀 How to Run the Project

1.  **Clone the repository:**
    ```bash
    git clone <your-repo-link>
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd CCRM-Project
    ```
3.  **Compile the code:**
    Make sure you're in the `src` directory.
    ```bash
    javac edu/ccrm/cli/CliManager.java
    ```
4.  **Run the application:**
    From the `src` folder, run the main class.
    ```bash
    java edu.ccrm.cli.CliManager
    ```

---

### 📝 Project Documentation

As required, here's some background information on the Java platform.

#### A Brief Evolution of Java

* **1995:** Sun Microsystems releases the first public version of Java (Java 1.0). "Write Once, Run Anywhere" is the goal.
* **1998:** J2SE 1.2 is released. A big step, it introduced the Swing GUI toolkit and the Collections framework.
* **2004:** J2SE 5.0 (code-named Tiger) is a major update, bringing generics, annotations, enums, and the `for-each` loop.
* **2014:** Java SE 8 changes everything with Lambdas, the Stream API, and a new Date/Time API. This project uses these features heavily.
* **2018-Present:** Java moves to a faster, 6-month release cycle. Long-Term Support (LTS) versions like Java 11, 17, and 21 are released, ensuring stability for enterprises.

#### Java ME vs. SE vs. EE

| Feature           | Java ME (Micro Edition)          | Java SE (Standard Edition)             | Java EE (Enterprise Edition)       |
| ----------------- | -------------------------------- | -------------------------------------- | ---------------------------------- |
| **Target** | Mobile, embedded devices         | Desktop & server applications          | Large-scale, web applications      |
| **Core API** | A small subset of the SE API     | The core Java programming language     | Extends Java SE with more APIs     |
| **Typical Use** | Old feature phones, smart cards  | This project, desktop apps (like Eclipse) | Web servers, enterprise software |
| **Key Tech** | MIDlets, CLDC, CDC               | JDK, JRE, JVM, Swing, JavaFX           | Servlets, JSP, EJB, JPA            |

#### Java Architecture: JDK, JRE, JVM

* **JVM (Java Virtual Machine):** The "magic" part. It's an abstract machine that provides a runtime environment to execute Java bytecode. This is what makes Java platform-independent. You run your code *on* the JVM, not the OS directly.
* **JRE (Java Runtime Environment):** This is what you need to *run* a Java application. It contains the JVM and the core Java libraries (like `java.lang`, `java.util`, etc.). If you're just a user, this is all you need.
* **JDK (Java Development Kit):** This is for developers. It includes everything the JRE has, plus the tools needed to *write* and *compile* Java code, like the compiler (`javac`) and debugger (`jdb`).


#### Enabling Assertions

The project uses assertions (`assert`) to check for internal invariants (e.g., making sure a student ID is never null after being set). By default, assertions are disabled.

To run the program with assertions enabled, use the `-ea` flag:

```bash
java -ea edu.ccrm.cli.CliManager
```
---
### 🗺️ Syllabus Topic Mapping

[cite_start]This table shows where each required concept from the syllabus is demonstrated in the code[cite: 136].

| Topic | File / Class / Method |
| --- | --- |
| **Packages & `main` class** | `edu.ccrm.cli`, `edu.ccrm.domain`, etc. Main in `CliManager.java` |
| **OOP (All Pillars)** | `Person.java` (Abstraction), `Student.java` (Inheritance), `Course.java` (Encapsulation), `TranscriptService.java` (Polymorphism) |
| **Interfaces & `default` methods** | `Persistable.java`, `Searchable.java` |
| **Enums with constructors** | `Grade.java`, `Semester.java` |
| **Nested & Inner Classes** | `Course.java` (static `Builder` class), `CliManager.java` (anonymous inner class for a simple action) |
| **Lambdas & Functional Interfaces** | `CourseService.java` (used with Streams for filtering), `StudentService.java` (sorting students with a Comparator lambda) |
| **Custom Exceptions** | `DuplicateEnrollmentException.java`, `MaxCreditLimitExceededException.java` |
| **NIO.2 File I/O & Streams** | `ImportExportService.java`, `BackupService.java` |
| **Date/Time API** | `Student.java` (for `enrollmentDate`), `BackupService.java` (for timestamped folders) |
| **Recursion** | `BackupService.java` (in `calculateDirectorySize` method) |
| **Singleton Pattern** | `AppConfig.java` |
| **Builder Pattern** | `Course.java` (has an inner `CourseBuilder` class) |
| **Loops, Switch, Jumps** | `CliManager.java` (in the main menu loop, using `switch`, `break`, `continue`) |
