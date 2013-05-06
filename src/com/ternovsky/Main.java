package com.ternovsky;

import com.ternovsky.model.WatcherRunnable;
import com.ternovsky.ui.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("main.fxml"));
        Parent root = (Parent) loader.load();
        MainController mainController = loader.getController();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.setTitle("Directory monitor");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
