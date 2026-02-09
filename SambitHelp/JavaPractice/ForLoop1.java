package start;
import java.util.Scanner;
public class ForLoop1 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number: ");
		int n= sc.nextInt();
		int i=1,sum=0;
		
		for(i=1;i<=n;i++)
		{
			sum+=i;	
		}
		System.out.println("The sum of the number is : "+sum);
		
		

	}

}
