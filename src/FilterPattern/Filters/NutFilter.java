package FilterPattern.Filters;

import FactoryMethodPattern.Item;
import FilterPattern.FilteredItems;
import FilterPattern.ItemFilter;

public class NutFilter extends FilteredItems {
    public static final String allergen = "NUTS";
    public NutFilter(ItemFilter instance) {
        super(instance);
    }

    @Override
    public Item[] applyFilter() {
        Item[] items = super.applyFilter();
        return super.filterByAllergenName(allergen, items);
    }
}
