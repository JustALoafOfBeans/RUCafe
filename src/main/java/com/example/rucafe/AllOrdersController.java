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

import java.util.ArrayList;

public class AllOrdersController {

    @FXML
    public ListView<MenuItem> ordersView;
    @FXML
    public ComboBox<Integer> orderNum;
    @FXML
    public Button cancelButton, exportButton;
    private int nextOrderNum;
    private ObservableList<Order> orders;
    private ObservableList<MenuItem> selectedOrder;
    private CafeController mainController;

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
    }

    /**
     * uhhhh im tired so im not sure what im doing rn. this adds an order to
     * the list of all orders for the store but idk how to do this >.>
     * @param content
     */
    @FXML
    protected void addToStoreOrder(ObservableList<MenuItem> content) {
        orders.add(new Order(nextOrderNum, content));
        nextOrderNum++;
    }

    @FXML
    protected void exportOrders() {
        //todo export orders to a txt
    }

    //Get the reference to the MainController object
    public void setMainController (CafeController controller){
        mainController = controller;
        setWindow();
    }

    // todo remove order
}
