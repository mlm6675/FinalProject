package FactoryMethodPattern.Items;

import FactoryMethodPattern.Item;

public class Candy implements Item {

    @Override
    public String getName() {
        return "NotSnickers";
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
