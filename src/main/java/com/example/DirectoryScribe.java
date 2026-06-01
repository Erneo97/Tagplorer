package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Create complete list of directories and files, including subdirectories.
 *
 */
public class DirectoryScribe {
    private final Path path;
    private List<FileInformation> directoryList;

    public DirectoryScribe(Path path) {
        this.path = path;
        directoryList = new ArrayList<>();
    }

    public void makeList() throws IOException{
        Stream<Path> subPaths = Files.walk(this.path);
        subPaths.forEach(path -> {
            FileInformation fi  = new FileInformation(path);
            directoryList.add(fi);
        });
    }

    public List<FileInformation> getDirectoryList() {
        return directoryList;
    }
}
