package com.example.rucafe;

public class BasketController {

    private CafeController mainController;
    //Get the reference to the MainController object
    public void setMainController (CafeController controller){
        mainController = controller;
    }
}
