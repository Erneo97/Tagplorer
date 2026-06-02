package com.example.fileObserver;

import java.nio.file.Path;
import java.time.LocalTime;
import java.util.Objects;

public class FileEvent {
    private final ExpandedWatchEventsKids type;
    private final Path path;
    private final LocalTime time;

    public FileEvent(ExpandedWatchEventsKids type, Path path, LocalTime time) {
        this.type = type;
        this.path = path;
        this.time = time;
    }

    public ExpandedWatchEventsKids getType() {
        return type;
    }

    public Path getPath() {
        return path;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FileEvent fileEvent = (FileEvent) o;
        return type == fileEvent.type && Objects.equals(path, fileEvent.path) && Objects.equals(time, fileEvent.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, path, time);
    }
}
