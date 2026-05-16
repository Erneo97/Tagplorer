package com.example;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FileInformation implements Serializable {
    Boolean directory, file;
    String path, name;

    long size;
    FileTime modifedTime, createdTime;

    public FileInformation(Path path) {
        this.directory = Files.isDirectory(path);
        this.file = Files.isRegularFile(path);

        try {
            BasicFileAttributes atributes = Files.readAttributes(path, BasicFileAttributes.class);

            size = atributes.size();
            modifedTime = atributes.lastModifiedTime();
            createdTime = atributes.creationTime();
        } catch (IOException e) {
            this.size = 0L;
            createdTime = modifedTime = FileTime.fromMillis(0);
        }

        this.path = path.toString();
        this.name = path.getFileName().toString();

    }

    public Boolean isDirectory() { return directory; }
    public Boolean isFile() { return file; }
    public Path getPath() { return Paths.get(path); }
    public String getName() {return name; }

    public long getSize() {return size; }
    public FileTime getModifedTime() {return modifedTime; }
    public FileTime getCreatedTime() {return createdTime; }


    @Override
    public String toString() {
        int deep = this.countPathSlash();
        return "  ".repeat(deep) +
                "path='" + path + '\'' +
                "directory=" + directory +
                ", file=" + file +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", modifedTime=" + modifedTime +
                ", createdTime=" + createdTime;
    }

    private int countPathSlash( ) {
        int count = 0;
        for (int i = 0; i < this.path.length(); i++) {
            if (this.path.charAt(i) == '\\') {
                count++;
            }
        }
        return count;
    }

}
