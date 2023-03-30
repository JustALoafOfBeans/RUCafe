package com.example.rucafe;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Coffee extends MenuItem{

    private String cupSize;
    private ArrayList<String> addIns;
    private int quantity;
    /**
     * Use DF.format(value) to round value to two decimals places. Rounding
     * up or down is by basic convention. Returns a String.
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public Coffee(String size, ArrayList<String> addIns, int num) {
        this.cupSize = size;
        this.addIns = addIns;
        this.quantity = num;
    }

    public double itemPrice() {
        double price = 0;
        if (cupSize.equals("small")) {
            price += 1.89;
        } else if (cupSize.equals("tall")) {
            price += 2.29;
        } else if (cupSize.equals("grande")) {
            price += 2.69;
        } else if (cupSize.equals("venti")) {
            price += 3.09;
        }
        price += 0.3*addIns.size();
        price = price*quantity;
        return Double.valueOf(DF.format(price));
    }
}
