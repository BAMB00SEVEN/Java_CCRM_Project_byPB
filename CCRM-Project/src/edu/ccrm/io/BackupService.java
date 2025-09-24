package edu.ccrm.io;

import edu.ccrm.config.AppConfig;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

// Handles creating timestamped backups of the data directory.
public class BackupService {

    private final Path sourceDir = AppConfig.getInstance().getDataDirectory();
    private final Path backupBaseDir = AppConfig.getInstance().getBackupDirectory();

    public void createBackup() {
        // Create a unique, timestamped folder name [cite: 94]
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        Path backupTargetDir = backupBaseDir.resolve("backup_" + timestamp);

        try {
            Files.createDirectories(backupTargetDir); // Create the new directory

            // Using Files.walk to go through all files in the source directory [cite: 32]
            try (Stream<Path> stream = Files.walk(sourceDir)) {
                stream.filter(Files::isRegularFile).forEach(sourceFile -> {
                    try {
                        Path destFile = backupTargetDir.resolve(sourceDir.relativize(sourceFile));
                        Files.copy(sourceFile, destFile, StandardCopyOption.REPLACE_EXISTING); // Copy file
                    } catch (IOException e) {
                        System.err.println("Failed to copy file: " + sourceFile);
                    }
                });
            }
            System.out.println("Backup created successfully at: " + backupTargetDir);

        } catch (IOException e) {
            System.err.println("Could not create backup: " + e.getMessage());
        }
    }

    // A recursive utility method to calculate directory size. [cite: 33]
    public long calculateDirectorySize(Path path) throws IOException {
        long size = 0;
        // The walk method is inherently recursive.
        try (Stream<Path> walk = Files.walk(path)) {
            size = walk
                .filter(Files::isRegularFile)
                .mapToLong(p -> {
                    try {
                        return Files.size(p);
                    } catch (IOException e) {
                        return 0L;
                    }
                })
                .sum();
        }
        return size;
    }
}
