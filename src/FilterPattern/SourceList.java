package FilterPattern;

import FactoryMethodPattern.Item;

public class SourceList implements ItemFilter{
    private Item[] items;

    public SourceList(Item[] items){
        this.items=items;
    }

    @Override
    public Item[] applyFilter() {
        //Nothing to filter
        return items;
    }
}
