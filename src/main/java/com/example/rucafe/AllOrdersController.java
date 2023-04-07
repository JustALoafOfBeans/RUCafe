package com.example.rucafe;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Controller class that handles events related to the Store Orders scene,
 * launched by the CafeController when the Store Orders is selected
 * @author Victoria Chen, Bridget Zhang
 */
public class AllOrdersController {
    /**
     * List view of the items in the currently selected order
     */
    @FXML
    public ListView<MenuItem> ordersView;
    /**
     * Combobox containing the order numbers of all the orders placed
     */
    @FXML
    public ComboBox<Integer> orderNum;
    /**
     * Buttons to cancel and order and export the orders to an external file
     */
    @FXML
    public Button cancelButton, exportButton;
    /**
     * Text field that displays the total cost of the displayed order
     */
    @FXML
    public TextField storeOrdersTotal;
    /**
     * List of all placed orders
     */
    private ObservableList<Order> orders;
    /**
     * List of items in the displayed order
     */
    private ObservableList<MenuItem> selectedOrder;
    /**
     * Reference to the CafeController that calls AllOrdersController
     */
    private CafeController mainController;
    /**
     * Use DF.format(value) to round value to two decimals places
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");
    /**
     *  Constant for tax rate in NJ, applied to find tax and total costs
     */
    private static final double TAXNJ = 0.06625;

    /**
     * Method runs on window initialization. Sets up change order action
     * event and initializes the list view to null.
     */
    public void initialize() {
        orderNum.setOnAction(this::changeOrder);
        ordersView.setItems(null);
    }

    /**
     * Sets up the window. Pulls all placed orders and populates the
     * order selection combobox.
     */
    public void setWindow() {
        orders = mainController.getAllordersList();
        if (orders != null) {
            for (Order ord : orders) {
                orderNum.getItems().add(ord.getNum());
            }
        }
    }

    /**
     * Updates the order's total value when a new order number is selected.
     * Value includes state tax.
     */
    private void updateTotal() {
        double sum = 0;
        int viewNum = orderNum.getSelectionModel().getSelectedItem();
        orders = mainController.getAllordersList();
        for (Order ord : orders) {
            if (ord.getNum() == viewNum) {
                selectedOrder = ord.getItems();
            }
        }
        if (selectedOrder != null) {
            for (MenuItem item : selectedOrder) {
                sum += item.itemPrice();
            }
        }
        sum += sum*TAXNJ; //add tax
        storeOrdersTotal.setText("$ " + Double.valueOf(DF.format(sum)));
    }

    /**
     * Action event. Changes the contents of the listview to match the items
     * in the selected order.
     * @param event method triggers when order number combobox value is changed
     */
    @FXML
    protected void changeOrder(ActionEvent event) {
        int viewNum = orderNum.getSelectionModel().getSelectedItem();
        // Find list of items associated w/ that number and return
        for (Order ord : orders) {
            if (ord.getNum() == viewNum) {
                selectedOrder = ord.getItems();
            }
        }
        if (selectedOrder != null) {
            ordersView.setItems(selectedOrder);
        }
        ordersView.refresh();
        updateTotal();
    }

    /**
     * On pressing the Export Orders button. Exports all orders in Store
     * Order to an external .txt file named "exportedOrders.txt". The file is
     * location in the RUCafe folder. If the file already exists, overwrite
     * its contents.
     */
    @FXML
    protected void exportOrders() {
        try {
            // file will be created in the RUCafe folder
            File target = new File("exportedOrders.txt");
            if (target.createNewFile()) {
                writeOrdersToFile(target.getName());
            } else {
                writeOrdersToFile(target.getName());
            }
        } catch (IOException e) {
            // System.out.println("An error occurred. Did not output to a file");
        }
    }

    /**
     * Helper method to exportOrders(). Write orders to the given file name.
     * @param fileName String, name of the file to write to
     */
    private void writeOrdersToFile(String fileName) {
        try {
            int num = 0;
            orders = mainController.getAllordersList();
            FileWriter myWriter = new FileWriter(fileName);
            if (orders != null) {
                for (Order ord : orders) {
                    num = ord.getNum();
                    myWriter.write("Order #" + num + "\n");
                    selectedOrder = ord.getItems();
                    if (selectedOrder != null) {
                        for (MenuItem item : selectedOrder) {
                            myWriter.write(item.toString() + "\n");
                        }
                    }
                    myWriter.write("Order total: $" + calculateTotalString(num) + "\n");
                    myWriter.write("\n");
                    Stage stage = (Stage) exportButton.getScene().getWindow();
                    stage.close();
                }
            } else {
                myWriter.write("No orders to export.");
            }
            myWriter.close();
        } catch (IOException e) {
            // System.out.println("An error occurred. Unable to write.");
        }
    }

    /**
     * Helper method to writeOrdersToFile(). Calculates the total amount of 1
     * order (includes tax).
     * @param OrderNumber integer value of the order's number to process
     * @return String value of the total order's cost
     */
    private String calculateTotalString(int OrderNumber) {
        double sum = 0;
        orders = mainController.getAllordersList();
        for (Order ord : orders) {
            if (ord.getNum() == OrderNumber) {
                selectedOrder = ord.getItems();
            }
        }
        if (selectedOrder != null) {
            for (MenuItem item : selectedOrder) {
                sum += item.itemPrice();
            }
        }
        sum += sum*TAXNJ; //add tax
        return DF.format(sum);
    }

    /**
     * On Cancel Order click. Deletes the order from the list of Store Orders.
     */
    @FXML
    protected void onCancelOrderButton() {
        if (orderNum.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        int viewNum = orderNum.getSelectionModel().getSelectedItem();
        // Find list of items associated w/ that number and return
        for (Order ord : orders) {
            if (ord.getNum() == viewNum) {
                orders.remove(ord);
            }
        }
        ordersView.refresh();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Method that stores reference to the CafeController
     * @param controller reference to CafeController
     */
    public void setMainController (CafeController controller){
        mainController = controller;
        setWindow();
    }
}
