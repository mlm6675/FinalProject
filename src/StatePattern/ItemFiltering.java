package StatePattern;
import FactoryMethodPattern.Item;
import FilterPattern.Filters.*;
import FilterPattern.ItemFilter;
import FilterPattern.SourceList;
import Sources.*;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;

public class ItemFiltering extends State {
    private ArrayList<AbstractMap.SimpleEntry<String, Boolean>> filters;
    private int currentPage, numberOfPages;
    private static final int ItemsPerPage = 3;

    public ItemFiltering()
    {
        currentPage = 0;
        filters = new ArrayList<>();
        filters.add(new AbstractMap.SimpleEntry<String, Boolean>("EggFilter", false));
        filters.add(new AbstractMap.SimpleEntry<String, Boolean>("LactoseFilter", false));
        filters.add(new AbstractMap.SimpleEntry<String, Boolean>("NutFilter", false));
        filters.add(new AbstractMap.SimpleEntry<String, Boolean>("SeafoodFilter", false));
        filters.add(new AbstractMap.SimpleEntry<String, Boolean>("WheatFilter", false));
        numberOfPages = filters.size()/3 + ((filters.size()%3 != 0)?1:0);
    }

    @Override
    public State processEvent(int event) {
        switch(event)
        {
            case cancelPressEvent:
                vendingMachine.getInventory().resetMasks(true);
                break;
            case confirmPressEvent:
                applyFilters();
                break;
            case arrowUpEvent:
                if(currentPage < numberOfPages){
                    currentPage++;
                    displayCurrentPage();
                }
                break;
            case arrowDownEvent:
                if(currentPage>0){
                    currentPage--;
                    displayCurrentPage();
                }
                break;
        }
        return nextState(event);
    }

    private void applyFilters() {
        Inventory inv = vendingMachine.getInventory();
        inv.resetMasks(false);
        ItemFilter sourceList = new SourceList(inv.getAllItems());
        for (AbstractMap.SimpleEntry entry : filters) {
            if((Boolean) entry.getValue()){
                sourceList = wrap((String)entry.getKey(), sourceList);
            }
        }
        Item[] validItems = sourceList.applyFilter();
        for (Item i : validItems) {
            inv.setMask(i, true);
        }
    }

    private void displayCurrentPage() {
        StringBuilder display = new StringBuilder();
        display.append("1. " + ((String) filters.get(currentPage*3).getKey()) + "Status: " + (Boolean) filters.get(currentPage*3).getValue());
        display.append("2. " + ((String) filters.get(currentPage*3+1).getKey()) + "Status: " + (Boolean) filters.get(currentPage*3 + 1).getValue());
        display.append("3. " + ((String) filters.get(currentPage*3+2).getKey()) + "Status: " + (Boolean) filters.get(currentPage*3 + 2).getValue());
        vendingMachine.setDisplay(display.toString());
    }

    private ItemFilter wrap(String key, ItemFilter sourceList) {
        switch (key){
            case "EggFilter": return new EggFilter(sourceList);
            case "LactoseFilter": return new LactoseFilter(sourceList);
            case "NutFilter": return new NutFilter(sourceList);
            case "SeafoodFilter": return new SeafoodFilter(sourceList);
            case "WheatFilter": return new WheatFilter(sourceList);
        }
        return sourceList;
    }

    @Override
    public State processEvent(int event, int key) {
        if(event == State.digitPressEvent){
            switch (key){
                case 1,2,3:
                    AbstractMap.SimpleEntry value = filters.get(currentPage*3 + key - 1);
                    value.setValue(!((Boolean) value.getValue()));
            }
        }
        return nextState(event);
    }

    @Override
    protected State nextState(int event) {
        switch(event)
        {
            case confirmPressEvent, cancelPressEvent:
                return super.ItemFiltering;
            default:
                return this;
        }
    }

    protected void enter()
    {
        System.out.println("ENTERED: ItemFiltering");
        System.out.println("Select button 1-5 corresponding to your dietary restriction:\nwill add this later");
        //note: this is going to have to go through the gui and NOT through the console
    }
}
