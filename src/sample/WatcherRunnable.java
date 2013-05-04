package sample;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 04.05.13
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
public class WatcherRunnable implements Runnable {

    Controller controller;

    public WatcherRunnable(Controller controller) {
        this.controller = controller;

    }

    @Override
    public void run() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path watchedDirectoryPath = Paths.get("C:\\Users\\ternovsky\\Desktop");
            watchedDirectoryPath.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

            while (true) {
                try {
                    WatchKey key = watchService.take();
                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind kind = event.kind();
                        if (kind == OVERFLOW) {
                            continue;
                        } else if (kind == ENTRY_CREATE) {
                            controller.addMessage("ENTRY_CREATE");
                        } else if (kind == ENTRY_MODIFY) {
                            controller.addMessage("ENTRY_MODIFY");
                        } else if (kind == ENTRY_DELETE) {
                            controller.addMessage("ENTRY_DELETE");
                        }

                        key.reset();
                    }
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
