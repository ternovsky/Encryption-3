package com.ternovsky.ui;

import com.ternovsky.model.Message;
import com.ternovsky.model.WatcherRunnable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainController implements Initializable {

    @FXML
    private TableView messagesTableView;

    private ObservableList<Message> observableList = FXCollections.observableArrayList();
    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messagesTableView.setItems(observableList);
        executor.execute(new WatcherRunnable(this));
    }

    public void addMessage(final Message message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                observableList.add(message);
            }
        });
    }
}
