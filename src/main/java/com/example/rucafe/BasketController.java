package com.example.rucafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

public class BasketController {

    @FXML
    public ListView<MenuItem> order;
    @FXML
    public Button removeButton, clearButton, finalButton;
    @FXML
    public TextField basketSubtotal, basketTax, basketTotal;
    ObservableList<MenuItem> menuList;
    private CafeController mainController;
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    private static final double taxNJ = 0.06625;
    //Get the reference to the MainController object

    public void setMainController (CafeController controller) {
        mainController = controller;
        menuList = mainController.returnBasket();
        order.setItems(menuList);
        updateTotals();
    }

    @FXML
    protected void onRemoveItem() {
        MenuItem selected = order.getSelectionModel().getSelectedItem();
        mainController.removeItem(selected);
        menuList.remove(selected);
        order.refresh();
        updateTotals();
    }

    @FXML
    protected void onClearOrder() {
        mainController.clearBasket();
        menuList = mainController.returnBasket();
        order.setItems(menuList);
        updateTotals();
    }

    @FXML
    protected void onFinalize() {
        mainController.placeOrder();
    }

    protected void updateTotals () {
        double subtotal = 0;
        for (int i = 0; i < order.getItems().size(); i++) {
            subtotal += (order.getItems().get(i).itemPrice());
        }
        basketSubtotal.setText("$ " + Double.valueOf(DF.format(subtotal)));

        double tax = subtotal * taxNJ;
        basketTax.setText("$ " + Double.valueOf(DF.format(tax)));

        double total = subtotal + tax;
        basketTotal.setText("$ " + Double.valueOf(DF.format(total)));
    }
}
