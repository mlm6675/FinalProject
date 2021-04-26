package StatePattern;
import AdapterPattern.Display;
import FactoryMethodPattern.Item;
import FilterPattern.Filters.*;
import FilterPattern.ItemFilter;
import FilterPattern.SourceList;
import Sources.*;

import java.util.AbstractMap;
import java.util.ArrayList;

public class ItemFiltering extends State {
    private ArrayList<AbstractMap.SimpleEntry<String, Boolean>> filters;
    private int currentPage, numberOfPages;
    private static final int ItemsPerPage = 3;

    public ItemFiltering()
    {
        currentPage = 1;
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
                if(currentPage>1){
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
        for (AbstractMap.SimpleEntry<String, Boolean> entry : filters) {
            if(entry.getValue()){
                sourceList = wrap(entry.getKey(), sourceList);
            }
        }
        Item[] validItems = sourceList.applyFilter();
        for (Item i : validItems) {
            inv.setMask(i, true);
        }
    }

    private void displayCurrentPage() {
        Display display = vendingMachine.getDisplay();
        StringBuilder screen = new StringBuilder();
        screen.append("Press 1-3 to toggle a filter or use arrows key to scroll.\n");
        try{
            screen.append("1. " + ((String) filters.get((currentPage-1)*3).getKey()) + "\tStatus: " + (Boolean) filters.get((currentPage-1)*3).getValue() + '\n');
            screen.append("2. " + ((String) filters.get((currentPage-1)*3+1).getKey()) + "\tStatus: " + (Boolean) filters.get((currentPage-1)*3 + 1).getValue()+ '\n');
            screen.append("3. " + ((String) filters.get((currentPage-1)*3+2).getKey()) + "\tStatus: " + (Boolean) filters.get((currentPage-1)*3 + 2).getValue()+ '\n');
        }catch (IndexOutOfBoundsException ex){
            //Ignore non-existing elements
        }
        display.setDispalyText(screen.toString());
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
                    try {
                        AbstractMap.SimpleEntry<String, Boolean> value = filters.get((currentPage-1)*3 + key - 1);
                        value.setValue(!value.getValue());
                    }catch (IndexOutOfBoundsException ex){
                        //Ignore updates for elements that don't exist
                    }
                    displayCurrentPage();
                    break;
            }
        }
        return nextState(event);
    }

    @Override
    protected State nextState(int event) {
        switch(event)
        {
            case confirmPressEvent, cancelPressEvent:
                vendingMachine.getCurrentState().leave();
                State.ItemSelection.enter();
                return State.ItemSelection;
            default:
                return this;
        }
    }

    @Override
    protected void leave() {
        super.leave();
        currentPage = 1;
        filters.forEach(entry -> entry.setValue(false));
    }

    @Override
    protected void enter()
    {
        super.enter();
        displayCurrentPage();
    }
}
