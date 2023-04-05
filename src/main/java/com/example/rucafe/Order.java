package com.example.rucafe;

import java.util.ArrayList;

public class Order {

    private int orderNum;
    private ArrayList<String> items;

    public Order(int num, ArrayList<String> content) {
        this.orderNum = num;
        this.items = content;
    }
}
