public class Pharmacy {
    static public String Name;
    static public String Address;
    static public int Telenum;
    public Pharmacy(String name, String address, int telenum){
        Name=name;
        Address=address;
        Telenum=telenum;
    }
    public String ContactUs(){
        return "Name: "+Name+"\nAddress :"+Address+"\nTeleNum:"+ Telenum;
    }
}
