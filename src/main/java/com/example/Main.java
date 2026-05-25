package com.example;

import java.io.IOException;
import java.nio.file.*;


public class Main {
    public static void main(String[] args) {
        String testDir = System.getenv("testDir").replace('\'', ' ').trim();
        Path testPath = Paths.get(testDir);

        DirectoryScribe scribe = new DirectoryScribe( testPath );
        try {
            scribe.makeList();
            for(FileInformation record : scribe.getDirectoryList() ) {
                System.out.println(record);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            System.out.println("dirWatcher - startuje");
            DirectoryWatcher dirWatcher = new DirectoryWatcher( testPath );
            Thread test = new Thread( dirWatcher);
            test.start();
            System.out.println("dirWatcher - uruchomiony");

//            test.interrupt();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}