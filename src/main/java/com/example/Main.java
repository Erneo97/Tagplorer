package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Main {
    public static void main(String[] args) {
        String testDir = System.getenv("testDir").replace('\'', ' ').trim();
        System.out.println("Test dir path: " + testDir);

        Path pathDadDir = Paths.get(testDir);
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();

            pathDadDir.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY
            );

            System.out.println("Monitoring folderu: " + pathDadDir);

            WatchKey key;
            while ((key = watchService.take()) != null) {

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path path = (Path) event.context();

                    System.out.println(kind.name() + " - " + path + "  " + LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));
                    File file = pathDadDir.resolve(path).toFile();
                    System.out.println("\t" + file.getAbsolutePath() + "   " + file.isDirectory() + "   " + file.isFile() + "   " );

                }

                key.reset();
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}