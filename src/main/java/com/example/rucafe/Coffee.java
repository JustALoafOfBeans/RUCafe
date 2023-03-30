package com.example.rucafe;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Coffee extends MenuItem{

    private static int INIT = 0;
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
        double price = INIT;
        if (cupSize.equals("short")) {
            price += prices.SHORT.val;
        } else if (cupSize.equals("tall")) {
            price += prices.TALL.val;
        } else if (cupSize.equals("grande")) {
            price += prices.GRANDE.val;
        } else if (cupSize.equals("venti")) {
            price += prices.VENTI.val;
        }
        price += prices.SYRUP.val*addIns.size();
        price = price*quantity;
        return Double.valueOf(DF.format(price));
    }

    @Override
    public String toString() {
        String item = quantity + " " + cupSize;
        if (addIns.size() != INIT) {
            item +=  " (";
            for (int i = 0; i < addIns.size(); i++) {
                item += addIns.get(i) + ", ";
            }
            item = item.substring(0,item.length()-2); //crop last ", "
            item += ")";
        }
        return item;
    }
}
