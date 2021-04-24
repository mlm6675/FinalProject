package FactoryMethodPattern.Items;

import FactoryMethodPattern.Item;

public class Chips implements Item {

    @Override
    public String getName() {
        return "PotatoChips";
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
