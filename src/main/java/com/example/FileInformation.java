package com.example;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileInformation implements Serializable {
    Boolean directory, file;
    String path, typeChange;


    public FileInformation(Boolean directory, Boolean file, Path path, String typeChange) {
        this.directory = directory;
        this.file = file;
        this.path = path.toString();
        this.typeChange = typeChange;
    }

    public Boolean isDirectory() { return directory; }
    public Boolean isFile() { return file; }
    public Path getPath() { return Paths.get(path); }
    public String getTypeChange() { return typeChange; }

}
