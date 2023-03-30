package com.example.rucafe;

public enum prices {
    SHORT(1.89),
    TALL(2.29),
    GRANDE(2.69),
    VENTI(3.09),
    SYRUP(0.3),
    YEAST(1.59),
    CAKE(1.79),
    HOLE(0.39);

    public final double val;
    prices(double val) {
        this.val = val;
    }
}
