import java.lang.classfile.Superclass;
import java.util.ArrayList;

public class Patient extends Person{
    static public ArrayList<Patient> PatientList = new ArrayList<Patient>();
    private String Address;
    private int Telenum;
    private String Email;

    public Patient(String name, int age, String address, int telenum, String email){
        super.Name=name;
        super.Age=age;
        Address=address;
        Telenum=telenum;
        Email=email;
        PatientList.add(this);
    }
    public void setName(String name){Name=name;}
    public void setAddress(String address){Address=address;}
    public void setEmail(String email){Email=email;}
    public void setPhoneNum(int telenum){Telenum=telenum;}

    public String getName(){return this.Name;}
    public int getAge(){return this.Age;}
    public String getAddress(){return Address;}
    public String getEmail(){return Email;}
    public int getTelenum(){return Telenum;}
}
