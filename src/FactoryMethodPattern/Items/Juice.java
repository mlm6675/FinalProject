package FactoryMethodPattern.Items;

import FactoryMethodPattern.Item;

public class Juice implements Item {
    @Override
    public String getName() {
        return "Grape Juice";
    }

    @Override
    public double getPrice() {
        return 1.49;
    }

    @Override
    public String[] getAllergenList() {
        return new String[0];
    }
}
