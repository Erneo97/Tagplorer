package com.example.fileSubscriber;

import java.nio.file.Path;

public class FileEvent {
    private final ExpandedWatchEventsKids type;
    private final Path path;

    public FileEvent(ExpandedWatchEventsKids type, Path path) {
        this.type = type;
        this.path = path;
    }

    public ExpandedWatchEventsKids getType() {
        return type;
    }

    public Path getPath() {
        return path;
    }
}
