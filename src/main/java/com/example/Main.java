package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String testDir = System.getenv("testDir").replace('\'', ' ').trim();

        DirectoryScribe scribe = new DirectoryScribe( Paths.get(testDir));
        try {
            scribe.makeList();
            for(FileInformation record : scribe.getDirectoryList() ) {
                System.out.println(record);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        System.out.println("Test dir path: " + testDir);
//
//        System.out.println("Pliki/katalogi w wskazanym folderze");
//        Path pathDadDir = Paths.get(testDir);
//        System.out.println("getFileName: " + pathDadDir.getFileName());
//        System.out.println("getRoot: " + pathDadDir.getRoot());
//        System.out.println("getParent: " + pathDadDir.getParent());
//
//
//        System.out.println("getNameCount: " + pathDadDir.getNameCount());
//        for (int i = 0; i < pathDadDir.getNameCount(); i++) {
//            System.out.println("\t" + pathDadDir.getName(i));
//        }
//        System.out.println();
//
//        try(Stream<Path> subPaths = Files.walk(pathDadDir)) {
//            subPaths.forEach(path -> {
//                FileInformation fi  = new FileInformation(path);
//                System.out.println(fi);
//            });
//
//
//        }
//        catch ( IOException e) {
//            e.printStackTrace();
//        }

        try {
            System.out.println("dirWatcher - startuje");
            DirectoryWatcher dirWatcher = new DirectoryWatcher( Paths.get(testDir) );
            Thread test = new Thread( dirWatcher);
            test.start();

            System.out.println("dirWatcher - uruchomiony");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        try {
//            WatchService watchService = FileSystems.getDefault().newWatchService();
//
//            pathDadDir.register(watchService,
//                    StandardWatchEventKinds.ENTRY_CREATE,
//                    StandardWatchEventKinds.ENTRY_DELETE,
//                    StandardWatchEventKinds.ENTRY_MODIFY
//            );
//
//            System.out.println("Monitoring folderu: " + pathDadDir);
//
//            WatchKey key;
//            while ((key = watchService.take()) != null) {
//
//                for (WatchEvent<?> event : key.pollEvents()) {
//                    WatchEvent.Kind<?> kind = event.kind();
//                    Path path = (Path) event.context();
//
//                    System.out.println(kind.name() + " - " + path + "  " + LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));
//                    File file = pathDadDir.resolve(path).toFile();
//                    System.out.println("\t" + file.getAbsolutePath() + "   " + file.isDirectory() + "   " + file.isFile() + "   " );
//
//                }
//
//                key.reset();
//            }
//
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}