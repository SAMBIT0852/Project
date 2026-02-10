package OOP.Polymorphisim;

public class MethodOverloading {

    void sum(int a, int b) {
        System.out.println("The sum of two numbers is: " + (a + b));
    }

    void sum(int a, int b, int c) {
        System.out.println("The sum of three numbers is: " + (a + b + c));
    }

    public static void main(String[] args) {

        MethodOverloading obj = new MethodOverloading();

        obj.sum(10, 20);       
        obj.sum(10, 20, 40);    
    }
}
