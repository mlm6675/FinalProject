package FactoryMethodPattern;

import FactoryMethodPattern.Items.Null_Item;

public class ItemFactoryImp implements ItemFactory {
    @Override
    public Item getItemByName(String className) {
        try{
            Class loader = Class.forName("FactoryMethodPattern.Items."+className);
            return (Item) loader.getConstructor().newInstance();
        }catch (ClassNotFoundException classNotFoundException){
            System.err.println("Couldn't locate the source class. Make sure it's a member of FactoryMethodPattern.Items package");
        }catch (NoSuchMethodException noSuchMethodException){
            System.err.println("The desired class is missing default constructor.");
        }catch (Exception ex){
            System.err.println(ex);
        }
        return new Null_Item();
    }
}
