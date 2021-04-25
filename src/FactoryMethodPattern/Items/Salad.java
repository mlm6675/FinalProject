package FactoryMethodPattern.Items;

import FactoryMethodPattern.Item;
import FilterPattern.Filters.LactoseFilter;
import FilterPattern.Filters.SeafoodFilter;

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
    public String[] getAllergenList(){
        String[] allergens = new String[2];
        allergens[0] = LactoseFilter.allergen;
        allergens[1] = SeafoodFilter.allergen;
        return allergens;
    }
}
