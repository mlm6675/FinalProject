package Sources;

import FactoryMethodPattern.Item;
import FactoryMethodPattern.ItemFactory;
import FactoryMethodPattern.ItemFactoryImp;
import FactoryMethodPattern.Items.Null_Item;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    private ArrayList<Item> itemTypes;
    private ArrayList<Integer> itemQuantity;
    private ArrayList<Boolean> itemMasks;

    public Inventory(int size){
        itemTypes = new ArrayList<>();
        itemQuantity = new ArrayList<>();
        itemMasks = new ArrayList<>();
        ItemFactory factory = new ItemFactoryImp();
        Item nItem = new Null_Item();
        int count = 0;
        JFileChooser fc = new JFileChooser("./src/Settings");
        fc.showDialog(null, "Select inventory file");
        //Since this a protype and not a real system, we assume that this will always be successful.
        //In a real system, we'd keep asking until user selects a valid file
        File inventoryFile = fc.getSelectedFile();
        if(inventoryFile.exists()){
            try(Scanner fileReader = new Scanner(inventoryFile)){
                while(fileReader.hasNext()){
                    String[] record = fileReader.nextLine().split(","); //Reads lines from an inventory record file. PRODUCT_CLASS_NAME, QUANTITY
                    Item item = factory.getItemByName(record[0]);
                    if(item instanceof Null_Item)
                        continue;
                    int quantity = Integer.parseInt(record[1].trim());
                    itemTypes.add(item);
                    itemQuantity.add(quantity);
                    itemMasks.add(true);
                    count++;
                    if(count == size) //As soon as inventory is full, stop reading the file
                        break;
                }
            }catch (FileNotFoundException fileNotFoundException){
                System.err.println("Application couldn't locate inventory record.");
            }catch (NumberFormatException numberFormatException){
                //If a record is corrupted, skip it
            }
        }
        //pad remaining items
        if(count<size){
            for(int i = count; i != size; i++){
                itemTypes.add(nItem);
                itemQuantity.add(0);
                itemMasks.add(false);
            }
        }
    }

    public Item getItemNumber(int location){
        if(location>0 && location<=itemTypes.size()){
            return itemTypes.get(location-1);
        }
        return new Null_Item();
    }
    public int getItemCount(){
        return itemTypes.size();
    }
    public Item[] getAllItems(){
        return itemTypes.toArray(new Item[itemTypes.size()]);
    }
    public boolean updateItemQuantity(Item item, int delta){
        int index = itemTypes.indexOf(item);
        if(index != -1){
            int quantity = itemQuantity.get(index), upd = quantity + delta;
            if(upd>=0){
                itemQuantity.set(index, upd);
                return true;
            }
        }
        return false;
    }
    public int getItemQuantity(Item item){
        int index = itemTypes.indexOf(item);
        if(index != -1)
            return itemQuantity.get(index);
        else
            return -1;
    }
    public boolean getMask(Item item){
        int index = itemTypes.indexOf(item);
        if(index != -1){
            return itemMasks.get(index);
        }
        return false;
    }
    public void setMask(Item item, boolean state){
        int index = itemTypes.indexOf(item);
        itemMasks.set(index, state);
    }
    public void resetMasks(boolean state){
        for(int i=0, size = itemMasks.size(); i != size; i++){
            itemMasks.set(i,state);
        }
    }
}
