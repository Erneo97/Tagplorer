package com.example.fileScribe;

import com.example.fileScribe.fileObserver.ExpandedWatchEventsKids;
import com.example.fileScribe.fileObserver.FileEvent;
import com.example.fileScribe.fileObserver.FileEventPublisher;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.concurrent.atomic.AtomicBoolean;

public class DirectoryWatcher implements Runnable {
    private final Path path;
    private final AtomicBoolean run;
    private final FileEventPublisher publisher;
    WatchService watchService;


    public DirectoryWatcher(Path path, FileEventPublisher publisher) throws IOException {
        this.path = path;
        run = new AtomicBoolean(true);
        watchService = FileSystems.getDefault().newWatchService();
        this.publisher = publisher;

        path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
        );
    }

    @Override
    public void run() {
        WatchKey key;
        while (run.get()) {
            try {
                if ((key = watchService.take()) != null) {
                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        Path path = (Path) event.context();

                        System.out.println(kind.name() + " - '" + path + "'  " + LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));
                        File file = this.path.resolve(path).toFile();
                        System.out.println("\t" + file.getAbsolutePath() + "   " + file.isDirectory() + "   " + file.isFile() + "   ");

                        FileEvent newEvent = new FileEvent(
                                ExpandedWatchEventsKids.valueOf(kind.name()),
                                path,
                                LocalTime.now()
                        );
                        publisher.publish(newEvent);

                    }
                    key.reset();
                }
            } catch (InterruptedException e) {
                stopWathing();
                throw new RuntimeException(e);
            }
        }
    }

    public void stopWathing() {
        run.set(false);
    }
}
