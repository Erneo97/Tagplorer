package com.example;

import com.example.fileScribe.fileObserver.FileEventPublisher;
import com.example.fileScribe.DirectoryScribe;
import com.example.fileScribe.DirectoryWatcher;
import com.example.fileScribe.FileInformation;

import java.io.IOException;
import java.nio.file.*;


public class Main {
    Character sd;

    public static void main(String[] args) {
        String testDir = System.getenv("testDir").replace('\'', ' ').trim();
        Path testPath = Paths.get(testDir);

        DirectoryScribe scribe = new DirectoryScribe(testPath);
        try {
            scribe.makeList();
            for (FileInformation record : scribe.getDirectoryList()) {
                System.out.println(record);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileEventPublisher filePublisher = new FileEventPublisher();


        try {
            System.out.println("dirWatcher - startuje");
            DirectoryWatcher dirWatcher = new DirectoryWatcher(testPath, filePublisher);
            Thread test = new Thread(dirWatcher);
            test.start();
            System.out.println("dirWatcher - uruchomiony");

//            test.interrupt();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}