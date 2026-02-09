package OOP.Inheritance;

class Person {
    void showPerson() {
        System.out.println("This is a person");
    }
}

interface Student {
    void showStudent();
}

interface Sports {
    void showSports();
}

class CollegeStudent extends Person implements Student, Sports {

    public void showStudent() {
        System.out.println("Person is a student");
    }

    public void showSports() {
        System.out.println("Student plays sports");
    }
}

public class HybridInheritance {
    public static void main(String[] args) {

        CollegeStudent cs = new CollegeStudent();

        cs.showPerson();   
        cs.showStudent(); 
        cs.showSports();   
    }
}
