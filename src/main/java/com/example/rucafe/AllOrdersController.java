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

public class AllOrdersController {

    @FXML
    public ListView<MenuItem> ordersView;
    @FXML
    public ComboBox<Integer> orderNum;
    @FXML
    public Button cancelButton, exportButton;
    @FXML
    public TextField costText;
    private int nextOrderNum;
    private ObservableList<Order> orders;
    private ObservableList<MenuItem> selectedOrder;
    private CafeController mainController;
    private static final double TAXNJ = 0.06625;
    /**
     * Use DF.format(value) to round value to two decimals places
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public void initialize() {
        orderNum.setOnAction(this::changeOrder);
        ordersView.setItems(null);
    }

    public void setWindow() {
        orders = mainController.getAllordersList();
        if (orders != null) {
            for (Order ord : orders) {
                orderNum.getItems().add(ord.getNum());
            }
        }
    }

    public void updateTotal() {
        double price = 0;
        for (int i = 0; i < ordersView.getItems().size(); i++) {
            price += (ordersView.getItems().get(i).itemPrice());
        }
        price = price * (1 + TAXNJ);
        costText.setText("$ "+ Double.valueOf(DF.format(price)));
    }

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

    //Get the reference to the MainController object
    public void setMainController (CafeController controller){
        mainController = controller;
        setWindow();
    }

    // todo remove order
}
