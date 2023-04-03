package com.example.rucafe;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class CoffeeController {
    @FXML
    public ComboBox<String> quantity;
    @FXML
    public RadioButton shortCup, tallCup, grandeCup, ventiCup;
    @FXML
    public CheckBox sCream, fVanilla, iCream, caramel, mocha;
    @FXML
    public Label quantityLabel;
    @FXML
    public ToggleGroup CupSizes;
    @FXML
    public TextField coffeeSubtotal;
    private CafeController mainController;
    //Get the reference to the MainController object
    public void setMainController (CafeController controller){
        mainController = controller;
    }

    @FXML
    protected void updateTotal() {
        coffeeSubtotal.setText("$ " + Double.toString(makeCoffee().itemPrice()));
    }

    @FXML
    protected void onCoffeeAddToBasket() {
        MenuItem item = makeCoffee();
    }

    private Coffee makeCoffee() {
        String size = getSize();
        ArrayList<String> addIns = getAddIns();
        int numberOf = Integer.parseInt(quantity.getSelectionModel().getSelectedItem());
        Coffee item = new Coffee(size, addIns, numberOf);
        return item;
    }

    private ArrayList<String> getAddIns() {
        ArrayList<String> ans = new ArrayList<>();
        if (sCream.isSelected()) {
            ans.add("sweet cream");
        }
        if (fVanilla.isSelected()) {
            ans.add("french vanilla");
        }
        if (iCream.isSelected()) {
            ans.add("irish cream");
        }
        if (caramel.isSelected()) {
            ans.add("caramel");
        }
        if (mocha.isSelected()) {
            ans.add("mocha");
        }
        return ans;
    }

    private String getSize() {
        if (shortCup.isSelected()) {
            return "short";
        } else if (tallCup.isSelected()) {
            return "tall";
        } else if (grandeCup.isSelected()) {
            return "grande";
        } else if (ventiCup.isSelected()) {
            return "venti";
        }
        return null;
    }

}
