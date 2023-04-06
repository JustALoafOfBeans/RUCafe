package com.example.rucafe;

import java.util.ArrayList;

public class Order {

    private int orderNum;
    private ArrayList<MenuItem> items;

    public Order(int num, ArrayList<MenuItem> content) {
        this.orderNum = num;
        this.items = content;
    }
}
