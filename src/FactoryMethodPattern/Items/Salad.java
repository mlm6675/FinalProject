package FactoryMethodPattern.Items;

import FactoryMethodPattern.Item;

public class Salad implements Item {

    @Override
    public String getName() {
        return "Ceasar Salad";
    }

    @Override
    public double getPrice() {
        return 4.99;
    }

    @Override
    public String[] getAllergenList() {
        return new String[0];
    }
}
