package FactoryMethodPattern.Items;

import FactoryMethodPattern.Item;

public class Sandwitch implements Item {
    @Override
    public String getName() {
        return "Tuna Sandwitch";
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
