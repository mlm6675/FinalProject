package FactoryMethodPattern.Items;

import FactoryMethodPattern.Item;
import FilterPattern.Filters.EggFilter;
import FilterPattern.Filters.LactoseFilter;
import FilterPattern.Filters.SeafoodFilter;
import FilterPattern.Filters.WheatFilter;

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
        String[] allergens = new String[3];
        allergens[0] = SeafoodFilter.allergen;
        allergens[1] = EggFilter.allergen;
        allergens[2] = WheatFilter.allergen;
        return allergens;
    }
}
