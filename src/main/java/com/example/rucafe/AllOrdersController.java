package com.example.rucafe;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class AllOrdersController {

    @FXML
    public ListView<MenuItem> ordersView;
    @FXML
    public ComboBox<Integer> orderNum;
    private int nextOrderNum;
    private ObservableList<Order> orders;
    private ArrayList<Integer> nums;
    private CafeController mainController;

    public void initialize() {
        orderNum.setOnAction(this::changeOrder);
    }

    public void setWindow() {
        orders = mainController.getAllordersList();
        nums = mainController.getAllordersNums();
        for (Integer num : nums) {
            orderNum.getItems().add(num);
        }
    }

    @FXML
    protected void changeOrder(ActionEvent event) {
        int num = orderNum.getSelectionModel().getSelectedItem();
        System.out.println("Viewing order #" + num);
    }

    /**
     * uhhhh im tired so im not sure what im doing rn. this adds an order to
     * the list of all orders for the store but idk how to do this >.>
     * @param content
     */
    @FXML
    protected void addToStoreOrder(ArrayList<MenuItem> content) {
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

    public ComboBox<Integer> getOrdernumBox () {
        return orderNum;
    }

    // todo remove order
}
