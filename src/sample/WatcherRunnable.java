package sample;

import com.ternovsky.model.Message;
import com.ternovsky.model.MessageType;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 04.05.13
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
public class WatcherRunnable implements Runnable {

    MainController mainController;

    public WatcherRunnable(MainController mainController) {
        this.mainController = mainController;
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
                        }

                        WatchEvent<Path> ev = cast(event);
                        Path filePath = watchedDirectoryPath.resolve(ev.context());
                        File file = filePath.toFile();

                        Message message = new Message();
                        message.setDate(new Date());
                        message.setFile(file);
                        message.setFileName(file.getName());

                        if (kind == ENTRY_CREATE) {
                            message.setMessageType(MessageType.CREATE);
                        } else if (kind == ENTRY_MODIFY) {
                            message.setMessageType(MessageType.UPDATE);
                        } else if (kind == ENTRY_DELETE) {
                            message.setMessageType(MessageType.DELETE);
                        }

                        mainController.addMessage(message);

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

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>) event;
    }
}
