package com.example.rucafe;

import javafx.fxml.FXML;

import java.util.ArrayList;

public class AllOrdersController {

    private int nextOrderNum;
    private ArrayList<Order> orders;
    private CafeController mainController;

    public void initialize() {
        nextOrderNum = 1;
        orders = new ArrayList<>();
    }

    /**
     * uhhhh im tired so im not sure what im doing rn. this adds an order to
     * the list of all orders for the store but idk how to do this >.>
     * @param content
     */
    @FXML
    protected void addToStoreOrder(ArrayList<String> content) {
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
    }
}
