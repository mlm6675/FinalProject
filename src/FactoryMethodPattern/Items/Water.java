package FactoryMethodPattern.Items;

import FactoryMethodPattern.Item;

public class Water implements Item {
    @Override
    public String getName() {
        return "Pure Water";
    }

    @Override
    public double getPrice() {
        return 0.69;
    }

    @Override
    public String[] getAllergenList() {
        return new String[0];
    }
}
