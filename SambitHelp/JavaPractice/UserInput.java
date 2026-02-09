package start;
import java.util.Scanner;
public class UserInput {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter the name of the student: ");
		String name=s.nextLine();
		System.out.println(name);

	}

}
