package start;
import java.util.Scanner;
public class WhileLoop1 {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter the number: ");
		int n=sc.nextInt();
		int i=0,sum=0;
		
		while(i<=n)
		{
			sum+=i;
			i++;
					
					
		}
		System.out.println("The sum of the number is : "+ sum);
		
	}

}
