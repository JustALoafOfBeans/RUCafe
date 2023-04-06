package com.example.rucafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DecimalFormat;

/**
 * Controller class that handles events related to the Basket scene, launched
 * by the CafeController when the Current Basket is selected
 * @author Victoria Chen, Bridget Zhang
 */
public class BasketController {
    /**
     * List view of all items currently in the basket
     */
    @FXML
    public ListView<MenuItem> order;
    /**
     * Buttons to removing, clearing, and finalizing order
     */
    @FXML
    public Button removeButton, clearButton, finalButton;
    /**
     * Text fields that display subtotal, tax, and total cost of order
     */
    @FXML
    public TextField basketSubtotal, basketTax, basketTotal;
    /**
     * List of all MenuItems currently in the order
     */
    ObservableList<MenuItem> menuList;
    /**
     * Reference to the CafeController that calls BasketController
     */
    private CafeController mainController;
    /**
     * Use DF.format(value) to round value to two decimals places
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");
    /**
     *  Constant for tax rate in NJ, applied to find tax and total costs
     */
    private static final double taxNJ = 0.06625;
    //Get the reference to the MainController object

    /**
     * Method that stores reference to the CafeController
     * @param controller reference to CafeController
     */
    public void setMainController (CafeController controller) {
        mainController = controller;
        menuList = mainController.returnBasket();
        order.setItems(menuList);
        updateTotals();
    }

    /**
     * Method that removes item selected in GUI from the list
     */
    @FXML
    protected void onRemoveItem() {
        MenuItem selected = order.getSelectionModel().getSelectedItem();
        mainController.removeItem(selected);
        menuList.remove(selected);
        order.refresh();
        updateTotals();
    }

    /**
     * Method that clears order, removing all items
     */
    @FXML
    protected void onClearOrder() {
        mainController.clearBasket();
        menuList = mainController.returnBasket();
        order.setItems(menuList);
        updateTotals();
    }

    /**
     * Method used to finalize order
     * Adds the current order to a list of orders for "Stores Orders" window
     * Clears order and closes basket stage
     */
    @FXML
    protected void onFinalize() {
        if (menuList.isEmpty()) {
            return;
        }
        mainController.placeOrder();
        onClearOrder();
        Stage stage = (Stage) finalButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Updates the subtotal, taxed, and total costs of the current order
     */
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
