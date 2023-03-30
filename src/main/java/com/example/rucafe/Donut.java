package com.example.rucafe;

import java.text.DecimalFormat;

public class Donut extends MenuItem{

    private String type;
    private int quantity;
    private String flavor;
    /**
     * Use DF.format(value) to round value to two decimals places. Rounding
     * up or down is by basic convention. Returns a String.
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public Donut(String type, String fla, int num) {
        this.type = type;
        this.quantity = num;
        this.flavor = fla;
    }

    public double itemPrice() {
        switch (type)  {
            case "yeast":
                return Double.valueOf(DF.format(1.59*quantity));
            case "cake":
                return Double.valueOf(DF.format(1.79*quantity));
            case "hole":
                return Double.valueOf(DF.format(0.39*quantity));
            default:
                return -1;
        }
    }
}
