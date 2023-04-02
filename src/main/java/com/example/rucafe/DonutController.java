package com.example.rucafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.List;

public class DonutController {
    @FXML
    public ComboBox<String> donutType, quantity;
    @FXML
    public ListView<String> flavorsList;
    @FXML
    public ListView<Donut> order;
    @FXML
    public TextField donutSubtotal;
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

    protected void updateTotal() {
        double sum = 0;
        for (int i = 0; i < order.getItems().size(); i++) {
            System.out.println(order.getItems().get(i).itemPrice());
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
        } else if (type.equals("Cake Donut")) {
            donutFlavors = FXCollections.observableArrayList("Lemon", "Blueberry", "Chocolate");
            flavorsList.setItems(donutFlavors);
        } else if (type.equals("Donut Hole")) {
            donutFlavors = FXCollections.observableArrayList("Chocolate", "Glazed", "Pumpkin");
            flavorsList.setItems(donutFlavors);
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
        itemsInOrder.add(item);
        order.setItems(itemsInOrder);
        updateTotal();
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

}
