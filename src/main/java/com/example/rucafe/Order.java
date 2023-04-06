package com.example.rucafe;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Order {

    private int orderNum;
    private ObservableList<MenuItem> items;

    public Order(int num, ObservableList<MenuItem> content) {
        this.orderNum = num;
        this.items = content;
    }

    public int getNum() {
        return orderNum;
    }

    public ObservableList<MenuItem> getItems() {
        return items;
    }
}
