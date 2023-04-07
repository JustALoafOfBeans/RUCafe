package com.example.rucafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CafeController {
    /**
     * List of donuts ordered
     */
    private ObservableList<Donut> orderDonut;
    /**
     * List of coffee ordered
     */
    private ObservableList<MenuItem> orderCoffee;
    /**
     * Static integer for initialization
     */
    private static int INIT = 0;
    /**
     * Static integer for starting
     */
    private static int START = 1;
    /**
     * Default size of windows
     */
    private static int WINDOWSIZE = 500;
    /**
     * Reference to Donut page's Controller
     */
    DonutController donutPage;
    /**
     * Reference to Store Order page's Controller
     */
    AllOrdersController allordersPage;
    /**
     * List containing all the current orders
     */
    ObservableList<Order> allordersList;
    /**
     * Integer for the order number of the next order to create
     */
    int allordersNext;

    /**
     * Method displays the select donuts page
     */
    @FXML
    protected void displayDonutsViewer() {
        Stage stage = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donut-view.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, WINDOWSIZE, WINDOWSIZE);
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

    /**
     * Method displays the select coffee page
     */
    @FXML
    protected void displayCoffeeViewer() {
        Stage stage = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, WINDOWSIZE, WINDOWSIZE);
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

    /**
     * Method displays the basket page (current order)
     */
    @FXML
    protected void displayBasketViewer() {
        Stage stage = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("basket-view.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, WINDOWSIZE, WINDOWSIZE);
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

    /**
     * Method displays the Store Orders page (all orders which have been placed)
     */
    @FXML
    protected void displayAllOrdersViewer() {
        Stage stage = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allorders-view.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, WINDOWSIZE, WINDOWSIZE);
            stage.setTitle("All Store Orders");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            allordersPage = loader.getController();
            allordersPage.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading allorders-view.fxml.");
            alert.setContentText("Couldn't load allorders-view.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * Method adds a new donut to the order
     * @param newOrder list of donuts to add to the order
     */
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
    }

    /**
     * Helper function to addDonuts(). Returns null if there is no item of that
     * type and flavor yet.
     * @param newItem donut to compare
     * @return item if this donut is the same as parameter, null otherwise
     */
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

    /**
     * Method adds a new Coffee to the order
     * @param newCoffee coffee item to add to the order
     */
    public void addCoffee (MenuItem newCoffee) {
        if (orderCoffee == null) {
            orderCoffee = FXCollections.observableArrayList();
        }
        orderCoffee.add(newCoffee);
    }

    /**
     * Method returns the current basket comprised of all current Donut and
     * Coffee objects.
     * @return List of menu items
     */
    public ObservableList<MenuItem> returnBasket () {
        ObservableList<MenuItem> basket = FXCollections.observableArrayList();
        if (orderCoffee != null) {
            basket.addAll(orderCoffee);
        }
        if (orderDonut != null) {
            basket.addAll(orderDonut);
        }
        return basket;
    }

    /**
     * Method removes an item from the current order
     * @param item Coffee or Donut item to be removed
     */
    public void removeItem(MenuItem item) {
        if (item instanceof Donut) {
            donutPage.removeDonut((Donut) item);
            orderDonut.remove(item);
        }
        if (item instanceof Coffee) {
            orderCoffee.remove(item);
        }
    }

    /**
     * Method clears all items from the basket
     */
    public void clearBasket() {
        if (orderDonut != null) {
            orderDonut.clear();
        }
        if (orderCoffee != null) {
            orderCoffee.clear();
        }
        orderDonut = null;
        orderCoffee = null;
    }

    /**
     * Method places the current order. Adds the current basket to the list
     * of Store Orders.
     */
    public void placeOrder() {
        if (orderDonut == null && orderCoffee == null) {
            return;
        }
        // Initialize orders list if needed
        if (allordersNext == INIT) {
            allordersNext = START; // first order
        }
        ObservableList<MenuItem> newOrder = returnBasket();
        if (allordersList == null) {
            allordersList = FXCollections.observableArrayList();
        }
        allordersList.add(new Order(allordersNext, newOrder));

        allordersNext += START;
    }

    /**
     * Method returns a list of all the orders currently placed.
     * @return list of all placed orders
     */
    public ObservableList<Order> getAllordersList() {
        return allordersList;
    }

    /**
     * Method that removes a specified order from the list
     * @param remNum number associated with order to remove
     */
    public void removeAllordersList(int remNum) {
        for (Order ord : allordersList) {
            if (ord.getNum() == remNum) {
                allordersList.remove(ord);
            }
        }
    }
}