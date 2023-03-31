package com.example.rucafe;

import javafx.fxml.FXML;

public class DonutController {
    private CafeController mainController;
    //Get the reference to the MainController object
    public void setMainController (CafeController controller){
        mainController = controller;
    }

    @FXML
    protected void onDonutAddToOrder() {
        //MenuItem item = makeDonut();
    }

}
