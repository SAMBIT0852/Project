package OOP.Inheritance;
class Person{
    void eat(){
        System.out.println("Person is eating");
    }
}
class Student extends Person{
    void study(){
        System.out.println("Student is studying");
    }
}
class Teacher extends Student{
    void teach(){
        System.out.println("Teacher is teaching");
    }
}

public class Multilevel {
    public static void main(String[] args) {
        Teacher t=new Teacher();
        t.eat();
        t.study();
        t.teach();
    }
    
}
