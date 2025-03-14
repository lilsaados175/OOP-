import java.util.Objects;

public class Payment implements Billing{
    private Cart myCart;
    private double cartTotal;
    private double deliveryCost;
    private double Taxes=14/100;
    public Payment(Cart cart){
        cartTotal=0;
        deliveryCost=10;
        myCart=cart;
    }
    public double ShopCalc(){
        double totalCart = 0;
        for (Medication item : myCart.Buying) {
            totalCart += item.getPrice()*item.Reserved;
        }
        return totalCart;
    }
    public double DeliveryCalc(){
        return deliveryCost;
    }
    public double TotalCost(){
         return (ShopCalc()+DeliveryCalc())*(1+Taxes);
    }
    public void ProcessPayment(){
        for (Medication med : myCart.Buying) {
            myCart.removeMedfromInv(med);
        }
        System.out.format("%15d %15d %15d %15d","Receipt:\n","ItemName:", "ItemCount:", "ItemPrice:");
        for (Medication med : myCart.Buying) {
            System.out.format("%15d %15d %15d %15d",med.Name,med.Reserved, med.getPrice(), "\n");
        }
        System.out.format("%30d","Total: "+TotalCost());
        System.out.format("Thank you for shopping with us,"+myCart.myPatient.Name);
    }


}
