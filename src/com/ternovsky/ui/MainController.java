package com.ternovsky.ui;

import com.ternovsky.model.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView messagesTableView;

    private ObservableList<Message> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messagesTableView.setItems(observableList);
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
//        this.
//        Node eventSource = (Node) event.getSource();
//        stage.initOwner(eventSource.getScene().getWindow());
        stage.show();
    }
}
