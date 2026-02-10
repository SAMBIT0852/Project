package OOP.Polymorphisim;

class Parent {

    void display() {
        System.out.println("This is the parent class method.");
    }
}

class Child extends Parent {

    @Override
    void display() {
        System.out.println("This is the child class .");
    }
}

public class MethodOverriding {

    public static void main(String[] args) {

        Parent obj = new Child(); 
        obj.display();
    }
}
