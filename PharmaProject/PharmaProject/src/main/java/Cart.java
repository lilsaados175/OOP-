import java.util.ArrayList;
import java.util.Objects;

public class Cart {
    Patient myPatient;
    public ArrayList<Medication> Buying = new ArrayList<Medication>();
    public Cart(Patient patient){
        myPatient=patient;
    }
    public void addtoCart(Medication med, int quantity){
        for (Medication inv : Inventory.Inventory) {
            if (inv.equals(med)) {
                if(inv.getStock()>=quantity){
                    med.Reserved=quantity;
                    Buying.add(med); //add to cart if available
                }
                else {
                    new Exception ("Stock not available");
                    System.out.println("Stock not available");
                }
                break; // Exit the loop once the medication is found
            }
        }
        System.out.println("Item not found in inventory.");
    }
    public void removefromCart(Medication med){
        for (Medication cart : Buying) {
            if (cart.equals(med)) {
                Buying.remove(med); //add to cart if available
                break;
            }
        }
        System.out.println("Item not found in cart.");
    }
    public void removeMedfromInv(Medication med){
        for (Medication inv : Inventory.Inventory) {
            if (inv.equals(med)) {
                inv.Stock -= med.Reserved; //decrement stock if avaiable
                System.out.println("Decremented Inventory of "+med.getName()+" by"+med.Reserved);
                break; // Exit the loop once the medication is found
            }
        }
        System.out.println("Cannot decrement Inventory of "+med.getName()+" by"+med.Reserved);
    }
}
