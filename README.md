# CCRM - Campus Course & Records Manager

This repository contains the source code for the Campus Course & Records Manager (CCRM), a console-based application developed for a Programming in Java course. The project is designed to help an educational institute manage its student records, course catalog, and enrollment processes through a command-line interface.

The application is built entirely with Java SE and demonstrates a range of core Java concepts, from object-oriented programming principles to modern APIs for file handling and date/time manipulation.

---

### Core Features

* **Student Management**: Functionality to add, update, list, and deactivate student records. Users can also view detailed student profiles and generate academic transcripts.
* **Course Management**: Allows for the creation, updating, and listing of courses. It includes advanced search and filter capabilities by instructor, department, or semester, implemented using the Java Stream API.
* **Enrollment & Grading**: Manages the process of enrolling and unenrolling students in courses, including business rule enforcement such as maximum credit limits per semester. It also provides a system for recording marks and calculating GPA.
* **File Operations**: Utilizes Java NIO.2 for robust file handling. This includes importing and exporting application data (students, courses) from and to CSV-like text files, as well as creating timestamped backups of all records.
* **Interactive CLI**: A user-friendly, menu-driven console interface allows for intuitive access to all the application's features and operations.

---

### Tech Stack

* **Language**: Java (JDK 11 or newer recommended)
* **Core APIs**: Java SE, NIO.2, Streams API, Date/Time API
* **Design Patterns**: Singleton, Builder
* **IDE**: Developed using Eclipse IDE

---

### How to Run the Project

1.  **Clone the repository:**
    ```bash
    git clone <your-repo-link>
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd CCRM-Project
    ```
3.  **Compile the code:**
    From inside the `src` directory, compile all Java files. A simple way to do this is to compile the main entry point, and the compiler will resolve its dependencies.
    ```bash
    javac edu/ccrm/cli/CliManager.java
    ```
4.  **Run the application:**
    From the `src` folder, execute the main class to start the program.
    ```bash
    java edu.ccrm.cli.CliManager
    ```

---

### Project Documentation

This section provides background information on the Java platform as required by the project statement.

#### A Brief Evolution of Java

* **1995**: Sun Microsystems releases the first public version of Java (Java 1.0), introducing the "Write Once, Run Anywhere" philosophy.
* **1998**: The release of J2SE 1.2 marks a significant expansion of the platform, introducing the Swing GUI toolkit and the Collections framework.
* **2004**: J2SE 5.0 (code-named Tiger) is a milestone release that adds fundamental language features like generics, annotations, enums, and the enhanced `for-each` loop.
* **2014**: Java SE 8 revolutionizes the language with the introduction of Lambda expressions, the Stream API, and a new Date/Time API, all of which are used in this project.
* **2018-Present**: Java adopts a faster, 6-month release cycle. Long-Term Support (LTS) versions, such as Java 11, 17, and 21, are released to provide stability for enterprise applications.

#### Java ME vs. SE vs. EE

| Feature           | Java ME (Micro Edition)          | Java SE (Standard Edition)             | Java EE (Enterprise Edition)       |
| ----------------- | -------------------------------- | -------------------------------------- | ---------------------------------- |
| **Target** | Mobile phones, embedded devices  | Desktop and server applications        | Large-scale, web applications      |
| **Core API** | A subset of the Java SE API      | The core Java programming platform     | Extends Java SE with web APIs      |
| **Typical Use** | Older mobile apps, smart cards   | Desktop apps, console apps (this project) | Enterprise software, web services  |
| **Key Tech** | MIDlets, CLDC, CDC               | JDK, JRE, JVM, Swing, JavaFX           | Servlets, JSP, EJB, JPA            |


#### Java Architecture: JDK, JRE, JVM

* **JVM (Java Virtual Machine)**: An abstract computing machine that enables a computer to run a Java program. The JVM interprets the compiled Java bytecode, making it possible for Java applications to be platform-independent.
* **JRE (Java Runtime Environment)**: The software package required to *run* Java applications. It contains the JVM, the Java Class Library, and other supporting files. A user who only wants to run Java programs needs only the JRE.
* **JDK (Java Development Kit)**: A superset of the JRE, the JDK contains everything needed to *develop* Java applications. It includes development tools such as the Java compiler (`javac`), the archiver (`jar`), and the debugger (`jdb`).

#### Enabling Assertions

This project uses assertions (`assert`) to validate internal program invariants, such as checking for non-null values where they are not expected. By default, assertions are disabled at runtime.

To run the application with assertions enabled, use the `-ea` (enable assertions) flag:

```bash
java -ea edu.ccrm.cli.CliManager
