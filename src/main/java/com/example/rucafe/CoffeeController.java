package com.example.rucafe;

public class CoffeeController {
    private CafeController mainController;
    //Get the reference to the MainController object
    public void setMainController (CafeController controller){
        mainController = controller;
    }
}
