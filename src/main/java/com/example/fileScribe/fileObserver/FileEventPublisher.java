package com.example.fileScribe.fileObserver;

import java.util.ArrayList;
import java.util.List;

public class FileEventPublisher {
    private final List<FileEventListener> listeners = new ArrayList<>();

    public void subscribers(FileEventListener lisner) {
        listeners.add(lisner);
    }

    public void unsubscribe(FileEventListener listener) {
        listeners.remove(listener);
    }

    public void publish(FileEvent event) {
        for (FileEventListener listener : listeners) {
            listener.onFileEvent(event);
        }
    }
}
