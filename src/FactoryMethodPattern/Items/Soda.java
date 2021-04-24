package FactoryMethodPattern.Items;

import FactoryMethodPattern.Item;

public class Soda implements Item {
    @Override
    public String getName() {
        return "Nuka-Cola";
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
