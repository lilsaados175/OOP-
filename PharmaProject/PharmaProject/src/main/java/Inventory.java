import java.util.ArrayList;

public class Inventory {
    Patient CurrentPatient;
    static public ArrayList<Medication> Inventory = new ArrayList<Medication>();
    static public void InventoryADD(String name, String type, double price, int quantity){
        boolean itemexists = false;
        Medication med= new Medication(name,type,price,quantity);
        for (Medication item : Inventory) {
            if(item.equals(med)){
                item.addStock(quantity);

                itemexists = true;
                break;
            }
        }
        if (!itemexists){
            Inventory.add(med);
        }
    }
    static public void InventoryUPDATE(Medication toUpdate, String name, String type, double price, int quantity){
        //takes Medication toUpdate from SearchMed function and updates its information
        Medication newMed= new Medication(name,type,price,quantity);
        for (Medication item : Inventory) {
            if(item.equals(toUpdate)){
                //searches for Med to Update in Inventory, once found saves its location, and replaces with new med
                int x= Inventory.indexOf(item);
                Inventory.remove(item);
                Inventory.add(x,newMed);
                break;
            }
        }
    }
    static public void InventoryREMOVE(Medication med){
        for (Medication inv : Inventory) {
            if(inv.equals(med)){
                Inventory.remove(inv);
                break;
            }
            System.out.println("Could not find medication to remove");
        }
    }

    static public boolean SearchBool(String drugName) {
        boolean foundBool=false;
        for (Medication med : Inventory) {
            if (med.Name==drugName) {
                foundBool=true;
                break; // Exit the loop once the medication is found
            }
        }
        return foundBool;
    }
    static public Medication SearchMed(String drugName) {
        Medication foundMed = null;
        for (Medication med : Inventory) {
            if (med.Name==drugName) {
                foundMed = med;
                break; // Exit the loop once the medication is found
            }
        }
        return foundMed;
    }
    static public String SearchString(String drugName){
        String searchText="This medicine is not available in our inventory.";
        Medication foundMed = null;
        for (Medication med : Inventory) {
            if (med.Name==drugName) {
                foundMed = med;
                searchText = "We have "+foundMed.Stock+" items of "+foundMed.Name+" in our inventory";
                break; // Exit the loop once the medication is found
            }
        }
        return searchText;
    }


}