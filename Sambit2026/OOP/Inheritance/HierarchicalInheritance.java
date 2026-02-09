package OOP.Inheritance;

class Animal {
    void eat() {
        System.out.println("Animal eats food");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks vovo");
    }
}

class Cat extends Animal {
    void meow() {
        System.out.println("Cat  says meo meo");
    }
}

public class HierarchicalInheritance {
    public static void main(String[] args) {

        Dog d = new Dog();
        d.eat();
        d.bark();

        Cat c = new Cat();
        c.eat();
        c.meow();
    }
}
