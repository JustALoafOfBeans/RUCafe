package com.example.rucafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CafeController {
    private ObservableList<Donut> orderDonut;
    private ObservableList<MenuItem> orderCoffee;
    DonutController donutPage;
    @FXML
    protected void displayDonutsViewer() {
        Stage stage = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donut-view.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 500, 500);
            stage.setTitle("Donut Select");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            donutPage = loader.getController();
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
        Stage stage = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 500, 500);
            stage.setTitle("Coffee Select");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
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
        Stage stage = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("basket-view.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 500, 500);
            stage.setTitle("Basket");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
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
        Stage stage = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allorders-view.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 500, 500);
            stage.setTitle("All Store Orders");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
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

    public void addDonuts(ObservableList<Donut> newOrder) {
        if (orderDonut == null) {
            orderDonut = newOrder;
        } else {
            for (Donut newDonut : newOrder) {
                Donut hasDonut = containsType(newDonut);
                if (hasDonut != null) {
                    hasDonut.setQuantity(hasDonut.getQuantity() + newDonut.getQuantity());
                } else {
                    orderDonut.add(newDonut);
                }
            }
        }
        for (Donut nut : orderDonut) { // todo remove test print
            System.out.println(nut + " for " + nut.itemPrice());
        }
        System.out.println();
    }

    // Returns null if no item of that type+flavor yet
    private Donut containsType(Donut newItem) { // todo copy pasted from DonutController... maybe move to donut
        for (Donut item : orderDonut) {
            if (item.getType().equals(newItem.getType())) {
                if (item.getFlavor().equals(newItem.getFlavor())) {
                    return item;
                }
            }
        }
        return null;
    }

    public void addCoffee (MenuItem newCoffee) {
        if (orderCoffee == null) {
            orderCoffee = FXCollections.observableArrayList();
        }
        orderCoffee.add(newCoffee);
        for (MenuItem item : orderCoffee) {
            System.out.println(item + " for " + item.itemPrice());
        }
        System.out.println(); // todo remove test print
    }

    public ObservableList<MenuItem> returnBasket () {
        ObservableList<MenuItem> basket = FXCollections.observableArrayList();
        if (orderCoffee != null) {
            basket.addAll(orderCoffee);
        }
        if (orderDonut != null) {
            basket.addAll(orderDonut);
        }

        System.out.println("BASKET:");
        for (MenuItem item : basket) {
            System.out.println(item);
        }
        System.out.println();

        return basket;
    }

    public void removeItem(MenuItem item) {
        if (item instanceof Donut) {
            donutPage.removeDonut((Donut) item);
            orderDonut.remove(item);
        }
        if (item instanceof Coffee) {
            orderCoffee.remove(item);
        }
    }
}