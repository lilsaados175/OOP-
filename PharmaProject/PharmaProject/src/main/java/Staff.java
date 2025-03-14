import java.util.ArrayList;

public class Staff extends Person{
    static public ArrayList<Staff> StaffList = new ArrayList<Staff>();

    private String ID;
    private String Password;
    private double Salary;
    public Staff(String name, int age, String id, String password){
        super.Name=name;
        super.Age=age;
        ID=id;
        Password=password;
        StaffList.add(this);
    }

    public String PrintInfo(){
        return super.printinfo() + "\nId: "+ID+"\nPassword :"+Password+"\nSalary: "+Salary;
    }
    public double CalcYearlySalary(){
        return Salary*12;
    }
    public void setName(String name){Name=name;}
    public String getName(){return Name;}
    public void setID(String id){ID=id;}
    public String getID(){return ID;}
    public void setPassword(String password){Password=password;}
    public String getPassword(){return Password;}
    public void setSalary(String password){Password=password;}
    public double getSalary(){return Salary;}

}
