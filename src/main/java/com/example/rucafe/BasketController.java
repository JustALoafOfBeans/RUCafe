package com.example.rucafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class BasketController {

    @FXML
    public ListView<MenuItem> order;
    @FXML
    public Button removeButton, clearButton, finalButton;
    ObservableList<MenuItem> menuList;
    private CafeController mainController;
    //Get the reference to the MainController object

    public void setMainController (CafeController controller) {
        mainController = controller;
        menuList = mainController.returnBasket();
        order.setItems(menuList);
    }

    @FXML
    protected void onRemoveItem() {
        MenuItem selected = order.getSelectionModel().getSelectedItem();
        mainController.removeItem(selected);
        menuList.remove(selected);
        order.refresh();
        for (MenuItem item : menuList) {
            System.out.println(item);
        }
        System.out.println();
    }

    @FXML
    protected void onClearOrder() {
        // todo
    }
}
