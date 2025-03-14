public class Medication {
     public String Name;
     public String Type;
     public double Price;
     public int Stock;
     public int Reserved;
    public Medication(String name, String type, double price, int quantity){
        Name=name;
        Type=type;
        Price=price;
        Stock=quantity;
        Reserved=0;
    }
    public void addStock(int n){Stock+=n;}
    public void setStock(int stock){Stock=stock;}
    public int getStock(){return Stock;}
    public void setName(String name){Name=name;}
    public void setType(String type){Type=type;}
    public void setPrice(double price){Price=price;}
    public String getName(){return Name;}
    public String getType(){return Type;}
    public double getPrice(){return Price;}

}
