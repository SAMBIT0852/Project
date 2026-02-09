# JavaFullCourse
JAVA

Definition	Java is a high-level, robust, object-oriented, and secure programming language as well as a platform.
Development History
    Developed by Sun Microsystems (now under Oracle) in 1995.
    Creator: James Gosling, often called the father of Java.
    Originally named Oak; renamed to Java due to existing trademark.
------------------------------------------
Object-Oriented Programming (OOP) in Java:
------------------------------------------
    Core Concept
    Java is object-oriented. Programs are built using objects, which contain data (variables) and behavior (methods).

OOP makes programs easy to understand, reusable and easy to maintain
    1.Encapsulation:- Encapsuling of data and method and give protection using private.
    2.Inheritance:- One class uses the properties of the classes like parent child relationship.
    3.Polymorphism:- Same method name, different behavior.
    4.Abstraction:- Hiding complex information using abstract class and interface
------------------------------
Java PATH Environment Variable:-
------------------------------
What Is Java PATH?
    The Java PATH is an environment variable that says the operating system where  is the Java tools (java, javac) are located. It allows  to run Java commands without typing the full path every time.
Why Is Java PATH Required?
    The OS does not know where Java is installed. So, we set the PATH to help the system find java and javac commands.
When Is Setting PATH Not Required?
    If the Java file is saved inside the JDK/bin folder, PATH is not needed because the tools are already in the current directory.
When Is Setting PATH Mandatory?
    If the Java file is outside the JDK/bin folder, then setting the PATH is mandatory to run Java commands from anywhere.
------------
Variables:
------------
    A variable is a identifiers that denotes a strong location used to store a data value.The value can change during the execution of the program.
    Java variables are of three type such as :
        Local variables
        Instance variables
        Static variables
--------------
Data Type:-
--------------
    In JAVA, data types specify the different sizes and values that can be stored in the variable or constants.
    Data types are of two  types such as Primitive(Numeric and Non Numeric ) and Nonprimitive(Class, Array and interface.)
---------------
Java KeyWords:-
---------------
	Key words are the reserved word used in java programming . These keywords are not used as a variables in program.
    EX- int, String, boolean, Double, float etc.
-----------------------------
Control Statement in java :-
-----------------------------
    Java compiler execute the code in top-bottom approach. The code executed as they appear in the program.
    -The control flow is of 3 types such as : Decision making, LoopStatement and jump-statement.
------------------
Decision making:-
------------------
    In Java "if" statement is uded to evaluate a condition. Its gives a boolean vallue after Execution of the program.
    It is of 4 tupes:
        Simple if statement
        if-else statement
        if-else-if ladder
        Nested if-statement
-------------------
Loop Statement:-
-------------------
    Instated of writing the same logic multiple time to execute the result jva gives us Loop which used to execute multiple time depending the condition it given and while the condition fails the loop stops its execution.
    In Java Loop are of 4 types:
        "For" Loop.
        "For-each"  Loop.
        "While" Loop.
        "do-While" Loop.
 
    For Loop:- In this loop We Innitialize the loop first, then the condition and then the incriment or 
                decrement in the same line of the code.It is used for fixed number.

    For-each Loop:- this is a  modified version of for loop where it is used in most of the data structures such as array and collections. Where we donot need to innitalize the variables.

    While Loop:-While the destination is unknown the while loop is used to iterate.
                Here the innitialization takes place outside the loop.

    do-While Loop:-The do-while loop checks the condition at the end of the loop after executing the loop statements. When the number of iteration is not known and we have to execute the loop at least once, we can use do-while loop.
--------------------
Jump Statement:-
--------------------
    In jumpstatement the current execution of the program is trnsfered to the next statement based upon some statements.
    It is of two types such as break statement and continue statement.

    In break statement is used to break the current execution of thr program to shift in to the next flow and it is uded in side a loop or inside a switch statement.

    In Continue statement the execution of the current program simply bypass and move to the next next part.
---------------------------------
Object Oriented Programing(OOP):
---------------------------------
    In Java all things are treated as object means a real world entity and programing means the methodology or approch to solve the problem.

    It is of Fourtypes Such as :

    1.Inheritance:- When one class acquires the properties and behaviour of the super class or parent 
                    class is known as Inheritance it is like a parent child relation ship.

                    -It is of 5 types such as :-
                        -Single Inheritance:- when one base class is derived from one parent class only.
                        -Multiple Inheritance:- When one basae class is derived from more than one 
                                                parent class. It is not possible practicallty so java
                                                uses the concept of interface to achive this.
                        -Multilevel Inheritance:-when a class is derived from another derived class.
                        -Hiarchical Inheritance:- When multiple derived class is in herit from the single
                                                    base class.
                        -Hybrid Inheritance:- Combination of tweo or more types of inheritance.




