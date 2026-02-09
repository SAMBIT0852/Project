package OOP.Inheritance;
class Vehicle{
    void start(){
        System.out.println("Vehicle is starting");
    }
}
class Car extends Vehicle{
    void start(){
        System.out.println("Car is starting");
    }
}

public class Single {
    public static void main(String[] args) {
        Car c=new Car();
        c.start();
    }
    
}
