package start;
import java.util.Scanner;
public class LeapYear {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the year: ");
		int y= sc. nextInt();
		if(y%100==0)
		{
			if(y%400==0)
			{
				System.out.println(y +"is a leap year");
			}
			else
			{
				System.out.println(y+"is not a leap year");
			}
		}
		else
		{
			if(y%4==0)
			{
				System.out.println(y+ "is a leap year");
			}
			else
			{
				System.out.println(y+"is not a leap year");
			}
		}

	}

}
