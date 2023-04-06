package com.example.rucafe;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Controller class for events related to the Coffee tab in the RU Cafe, launched
 * by the CafeController when the "Coffee" button is selected.
 * @author Victoria Chen, Bridget Zhang
 */
public class CoffeeController {
    /**
     * Drop-down selection for quantity of coffee
     */
    @FXML
    public ComboBox<String> quantity;
    /**
     * Buttons that allow to select for coffee size
     */
    @FXML
    public RadioButton shortCup, tallCup, grandeCup, ventiCup;
    /**
     * Checkboxes that mark of selected add-ins
     */
    @FXML
    public CheckBox sCream, fVanilla, iCream, caramel, mocha;
    /**
     * Label for quantity selection
     */
    @FXML
    public Label quantityLabel;
    /**
     * Group for selecting coffee size
     */
    @FXML
    public ToggleGroup CupSizes;
    /**
     * Text field describing current subtotal cost
     */
    @FXML
    public TextField coffeeSubtotal;
    /**
     * Button to add coffee order to basket
     */
    @FXML
    public Button basketButton;
    /**
     * Reference to caller controller
     */
    private CafeController mainController;

    /**
     * Gets reference to the CafeController object that called CoffeeController
     * @param controller reference to CafeController
     */
    public void setMainController (CafeController controller){
        mainController = controller;
    }

    /**
     * Method that updates subtotal in text field
     */
    @FXML
    protected void updateTotal() {
        coffeeSubtotal.setText("$ " + Double.toString(makeCoffee().itemPrice()));
    }

    /**
     * Initialization procedure for GUI Coffee
     */
    public void initialize() {
        updateTotal();
    }

    /**
     * Method that adds current coffee order to basket
     * and closes Coffee stage
     */
    @FXML
    protected void onCoffeeAddToBasket() {
        MenuItem item = makeCoffee();
        mainController.addCoffee(item);
        item = null;
        Stage stage = (Stage) basketButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Method that constructs a Coffee object from currently selected fields
     * @return constructed Coffee object
     */
    private Coffee makeCoffee() {
        String size = getSize();
        ArrayList<String> addIns = getAddIns();
        int numberOf = Integer.parseInt(quantity.getSelectionModel().getSelectedItem());
        Coffee item = new Coffee(size, addIns, numberOf);
        return item;
    }

    /**
     * Method that returns a list of the selected add-ins
     * @return ArrayList of add-ins as Strings
     */
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

    /**
     * Method that returns the size of the selected coffee
     * @return size selection as a String
     */
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
