package FilterPattern;

import FactoryMethodPattern.Item;

import java.util.ArrayList;

public abstract class FilteredItems implements ItemFilter{
    protected ItemFilter composite;

    public FilteredItems(ItemFilter instance){
        composite = instance;
    }

    @Override
    public Item[] applyFilter() {
        return composite.applyFilter();
    }

    protected Item[] filterByAllergenName(String allergenType, Item[] items){
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : items) {
            boolean isAllowed = true;
            String[] allergens = item.getAllergenList();
            for (String allergen : allergens) {
                if(allergen.equals(allergenType)){
                    isAllowed = false;
                    break;
                }
            }
            if(isAllowed)
                result.add(item);
        }
        return result.toArray(new Item[result.size()]);
    }
}
