package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;

// This is the Singleton class for our application's configuration. [cite: 80]
// It ensures that there is only one instance of configuration settings,
// like file paths, accessible throughout the entire app.
public class AppConfig {

    // The single, static instance of the class.
    private static AppConfig instance;

    private final Path dataDirectory;
    private final Path backupDirectory;
    private final int maxCreditsPerSemester;

    // The constructor is private to prevent direct instantiation.
    private AppConfig() {
        this.dataDirectory = Paths.get("data");
        this.backupDirectory = Paths.get("backups");
        this.maxCreditsPerSemester = 18; // Business rule [cite: 25]
    }

    /**
     * Provides the global access point to the Singleton instance.
     * This is called "lazy initialization" because the instance is only created
     * when it's first needed.
     *
     * @return The single instance of AppConfig.
     */
    public static AppConfig getInstance() {
        if (instance == null) {
            // Synchronized block to be thread-safe, just in case.
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }

    // --- Getters for configuration properties ---

    public Path getDataDirectory() {
        return dataDirectory;
    }

    public Path getBackupDirectory() {
        return backupDirectory;
    }

    public int getMaxCreditsPerSemester() {
        return maxCreditsPerSemester;
    }
}
