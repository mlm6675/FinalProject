package FactoryMethodPattern.Items;

import FactoryMethodPattern.Item;

public class Soup implements Item {
    @Override
    public String getName() {
        return "Soup De Loop";
    }

    @Override
    public double getPrice() {
        return 1.99;
    }

    @Override
    public String[] getAllergenList() {
        return new String[0];
    }
}
