package FactoryMethodPattern.Items;

import FactoryMethodPattern.Item;

public class Null_Item implements Item {
    @Override
    public String getName() {
        return "Null Item";
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String[] getAllergenList() {
        return new String[0];
    }
}
