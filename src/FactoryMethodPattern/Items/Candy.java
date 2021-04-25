package FactoryMethodPattern.Items;

import FactoryMethodPattern.Item;
import FilterPattern.Filters.NutFilter;

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
    public String[] getAllergenList(){
        String[] allergens = new String[1];
        allergens[0] = NutFilter.allergen;
        return allergens;
    }
}
