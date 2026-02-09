package OOP.Inheritance;
interface Father {
    void fatherWork();
}

interface Mother {
    void motherWork();
}

class Child implements Father, Mother {

    public void fatherWork() {
        System.out.println("Father gives  Money");
    }

    public void motherWork() {
        System.out.println("Mother give Food");
    }

    void childWork() {
        System.out.println("Child go to school");
    }

    public static void main(String[] args) {

        Child c = new Child();

        c.fatherWork();
        c.motherWork();
        c.childWork();
    }
}
