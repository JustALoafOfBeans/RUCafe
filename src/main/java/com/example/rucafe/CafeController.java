package com.example.rucafe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CafeController {
    @FXML
    protected void displayDonutsViewer() {
        Stage view1 = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donut-view.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 500, 500);
            view1.setScene(scene);
            view1.show();
            DonutController donutPage = loader.getController();
            donutPage.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading donut-view.fxml.");
            alert.setContentText("Couldn't load donut-view.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displayCoffeeViewer() {
        Stage view1 = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 500, 500);
            view1.setScene(scene);
            view1.show();
            CoffeeController coffeePage = loader.getController();
            coffeePage.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading coffee-view.fxml.");
            alert.setContentText("Couldn't load coffee-view.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displayBasketViewer() {
        Stage view1 = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("basket-view.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 500, 500);
            view1.setScene(scene);
            view1.show();
            BasketController basketPage = loader.getController();
            basketPage.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading basket-view.fxml.");
            alert.setContentText("Couldn't load basket-view.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displayAllOrdersViewer() {
        Stage view1 = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allorders-view.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 500, 500);
            view1.setScene(scene);
            view1.show();
            AllOrdersController allordersPage = loader.getController();
            allordersPage.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading allorders-view.fxml.");
            alert.setContentText("Couldn't load allorders-view.fxml.");
            alert.showAndWait();
        }
    }
}