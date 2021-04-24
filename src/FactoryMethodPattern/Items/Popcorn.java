package FactoryMethodPattern.Items;

import FactoryMethodPattern.Item;

public class Popcorn implements Item {
    @Override
    public String getName() {
        return "Cheesy Popcorn";
    }

    @Override
    public double getPrice() {
        return 3.49;
    }

    @Override
    public String[] getAllergenList() {
        return new String[0];
    }
}
