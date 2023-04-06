package com.example.rucafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class that runs the RU Cafe application
 * @author Victoria Chen
 */
public class CafeApplication extends Application {
    /**
     * Method that sets stage and starts GUI
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CafeApplication.class.getResource("cafe-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("RU Cafe");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main function that launches Cafe GUI
     * @param args String array of input arguments
     */
    public static void main(String[] args) {
        launch();
    }
}