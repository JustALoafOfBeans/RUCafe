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

public class DonutController {
    @FXML
    public ComboBox<String> donutType, quantity;
    @FXML
    public ListView<String> flavorsList;
    @FXML
    public ListView<Donut> order;
    @FXML
    public TextField donutSubtotal;
    @FXML
    public ImageView donutIMG;
    @FXML
    public Button basketButton;
    private ObservableList<String> donutFlavors;
    private ObservableList<Donut> itemsInOrder;
    private CafeController mainController;
    /**
     * Use DF.format(value) to round value to two decimals places. Rounding
     * up or down is by basic convention. Returns a String.
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public void initialize() {
        donutFlavors = FXCollections.observableArrayList("Chocolate glaze", "Strawberry Glaze",
                "Boston Cream", "Cookies & Cream", "Raspberry Glaze", "Matcha");
        flavorsList.setItems(donutFlavors);
        donutType.setOnAction(this::changeType);
        itemsInOrder = FXCollections.observableArrayList();
    }

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
        /*else if (containsType(item)) {
            System.out.println("removing " + item);
            itemsInOrder.remove(item);
        }*/
        order.refresh();
        order.setItems(itemsInOrder);
        updateTotal();
    }

    protected void updateTotal() {
        double sum = 0;
        for (int i = 0; i < order.getItems().size(); i++) {
            sum += (order.getItems().get(i).itemPrice());
        }
        donutSubtotal.setText("$ " + Double.valueOf(DF.format(sum)));
    }

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

    //Get the reference to the MainController object
    public void setMainController (CafeController controller){
        mainController = controller;
    }

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

    // Returns null if no item of that type+flavor yet
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

    @FXML
    public void addBasket(ActionEvent actionEvent) {
        mainController.addDonuts(itemsInOrder);
        itemsInOrder.removeAll();
        Stage stage = (Stage) basketButton.getScene().getWindow();
        stage.close();
    }
}
