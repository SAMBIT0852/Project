package OOP.Inheritance;


class Address {    
    String city,state,country;    
       
    public Address(String city, String state, String country) {    
       this.city = city;    
       this.state = state;    
       this.country = country;    
    }    
 }    
   
 class Emp {    
       int id;    
       String name;    
       Address address;  
       
    public Emp(int id, String name, Address address) {    
       this.id = id;    
       this.name = name;    
       this.address = address;    
    }    
       
    void display() {    
     System.out.println(id + " " + name);    
     System.out.println(address.city + " " + address.state + " " + address.country);    
    }    
 }    
   
 public class aggrigation {  
     public static void main(String[] args) {    
        Address address1 = new Address("BBSR", "ODISHA", "INDIA");    
        Address address2 = new Address("PURI", "ODISHA", "INDIA");    
        Emp e = new Emp(100, "RAM", address1);    
        Emp e2 = new Emp(101, "AMAR", address2);    
              
        e.display();    
        e2.display();      
     }    
 }
 
 