package FilterPattern.Filters;

import FactoryMethodPattern.Item;
import FilterPattern.FilteredItems;
import FilterPattern.ItemFilter;

public class LactoseFilter extends FilteredItems {
    public static final String allergen = "LACTOSE";

    public LactoseFilter(ItemFilter instance) {
        super(instance);
    }

    @Override
    public Item[] applyFilter() {
        Item[] items = super.applyFilter();
        return super.filterByAllergenName(allergen, items);
    }
}
