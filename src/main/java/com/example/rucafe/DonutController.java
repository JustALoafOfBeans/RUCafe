package com.example.rucafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Controller class for events related to the Donut tab in the RU Cafe,
 * launched by the CafeController when "Donut" option selected
 * @author Victoria Chen, Bridget Zhang
 */
public class DonutController {
    /**
     * Drop-down selection for donut types and quantities
     */
    @FXML
    public ComboBox<String> donutType, quantity;
    /**
     * GUI List of Donut flavor choices
     */
    @FXML
    public ListView<String> flavorsList;
    /**
     * GUI List of Donuts currently added to order
     */
    @FXML
    public ListView<Donut> order;
    /**
     * Text field containing total for current order
     */
    @FXML
    public TextField donutSubtotal;
    /**
     * GUI Image for donut type
     */
    @FXML
    public ImageView donutIMG;
    /**
     * GUI Button to add to basket
     */
    @FXML
    public Button basketButton;
    /**
     * List of Donut flavors as Strings
     */
    private ObservableList<String> donutFlavors;
    /**
     * List of Donuts in order
     */
    private ObservableList<Donut> itemsInOrder;
    /**
     * Reference to controller that called DonutController
     */
    private CafeController mainController;
    /**
     * Use DF.format(value) to round value to two decimals places
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * Initialization procedure for GUI Donut
     */
    public void initialize() {
        donutFlavors = FXCollections.observableArrayList("Chocolate glaze", "Strawberry Glaze",
                "Boston Cream", "Cookies & Cream", "Raspberry Glaze", "Matcha");
        flavorsList.setItems(donutFlavors);
        donutType.setOnAction(this::changeType);
        itemsInOrder = FXCollections.observableArrayList();
    }

    /**
     * Method that removes a Donut from the order
     */
    @FXML
    protected void onDonutRemoveFromOrder() {
        Donut item = makeDonut();
        if (item == null) {
            return;
        }
        Donut hasItem = containsType(item);
        int hasQuant, remQuant;
        if (hasItem != null) {
            hasQuant = hasItem.getQuantity();
            remQuant = item.getQuantity();
            if (hasQuant > remQuant) { // Removing less than added
                hasItem.setQuantity(hasQuant - remQuant);
            } else { // Removing all
                itemsInOrder.remove(hasItem);
            }
        }
        order.refresh();
        order.setItems(itemsInOrder);
        updateTotal();
    }

    /**
     * Method that updates the current subtotal of Donut order
     */
    protected void updateTotal() {
        double sum = 0;
        for (int i = 0; i < order.getItems().size(); i++) {
            sum += (order.getItems().get(i).itemPrice());
        }
        donutSubtotal.setText("$ " + Double.valueOf(DF.format(sum)));
    }

    /**
     * Method that changes the type of donut based on menu selection
     * @param event Event associated with change in Donut selected type
     */
    @FXML
    protected void changeType(ActionEvent event) {
        String type = donutType.getSelectionModel().getSelectedItem();
        if (type.equals("Yeast Donut")) {
            donutFlavors = FXCollections.observableArrayList("Chocolate glaze", "Strawberry Glaze",
                    "Boston Cream", "Cookies & Cream", "Raspberry Glaze");
            flavorsList.setItems(donutFlavors);
            Image img = new Image(new File("src/main/resources/images/YeastDonut.png").toURI().toString());
            donutIMG.setImage(img);
        } else if (type.equals("Cake Donut")) {
            donutFlavors = FXCollections.observableArrayList("Lemon", "Blueberry", "Chocolate");
            flavorsList.setItems(donutFlavors);
            Image img = new Image(new File("src/main/resources/images/CakeDonut.png").toURI().toString());
            donutIMG.setImage(img);
        } else if (type.equals("Donut Hole")) {
            donutFlavors = FXCollections.observableArrayList("Chocolate", "Glazed", "Pumpkin");
            flavorsList.setItems(donutFlavors);
            Image img = new Image(new File("src/main/resources/images/HoleDonut.png").toURI().toString());
            donutIMG.setImage(img);
        }
    }

    /**
     * Method that gets and stores a reference to the CafeController
     * @param controller reference to the CafeController
     */
    public void setMainController (CafeController controller){
        mainController = controller;
    }

    /**
     * Method that adds a Donut to an order
     */
    @FXML
    protected void onDonutAddToOrder() {
        Donut item = makeDonut();
        if (item == null) {
            return;
        }
        Donut n = containsType(item);
        if (n != null) {
            n.setQuantity(n.getQuantity() + item.getQuantity());
        } else {
            itemsInOrder.add(item);
        }
        order.refresh();
        order.setItems(itemsInOrder);
        updateTotal();
    }

    /**
     * Method that checks if a Donut of the same type is in order
     * Used to compare Donut flavor and type, not number
     * @return Donut of same type if found, or null if no match yet
     */
    private Donut containsType(Donut newItem) {
        for (Donut item : itemsInOrder) {
            if (item.getType().equals(newItem.getType())) {
                if (item.getFlavor().equals(newItem.getFlavor())) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * Method that creates a Donut object based on currently selected fields
     * @returns created Donut object
     */
    private Donut makeDonut() {
        String type = donutType.getSelectionModel().getSelectedItem();
        String flavor = flavorsList.getSelectionModel().getSelectedItem();
        int num = Integer.parseInt(quantity.getSelectionModel().getSelectedItem());
        if (flavor == null) {
            return null;
        }
        Donut item = new Donut(type, flavor, num);
        return item;
    }

    /**
     * Method that adds the current Donut order to the basket
     * and closes the Donut stage
     * @param actionEvent button click associated with adding to basket
     */
    @FXML
    public void addBasket(ActionEvent actionEvent) {
        mainController.addDonuts(itemsInOrder);
        itemsInOrder.removeAll();
        Stage stage = (Stage) basketButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Method that removes a certain donut from the basket
     * @param item Donut to remove
     */
    public void removeDonut(Donut item) {
        itemsInOrder.remove(item);
    }
}
