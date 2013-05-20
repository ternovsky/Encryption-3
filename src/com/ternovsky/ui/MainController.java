package com.ternovsky.ui;

import com.ternovsky.model.Message;
import com.ternovsky.model.WatcherRunnable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final String USER_DIR = "user.dir";

    @FXML
    private TableView messagesTableView;

    @FXML
    private Label directoryAbsolutePathLabel;

    private ObservableList<Message> observableList = FXCollections.observableArrayList();
    private File initialDirectory;
    private Thread thread;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messagesTableView.setItems(observableList);

        initialDirectory = new File(System.getProperty(USER_DIR, ""));
        changeDirectory(initialDirectory.getAbsolutePath());
    }

    public void addMessage(final Message message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                observableList.add(message);
            }
        });
    }

    @FXML
    private void exitAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void helpAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("help.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("О программе");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void changeDirectoryAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выбор папки");
        directoryChooser.setInitialDirectory(initialDirectory);
        File selectedDirectory = directoryChooser.showDialog(null);
        String absolutePath = selectedDirectory.getAbsolutePath();
        changeDirectory(absolutePath);
    }

    private void changeDirectory(String absolutePath) {
        if (thread != null) {
            thread.interrupt();
        }
        observableList.clear();
        directoryAbsolutePathLabel.setText(absolutePath);
        thread = new Thread(new WatcherRunnable(this, absolutePath));
        thread.setDaemon(true);
        thread.start();
    }
}
