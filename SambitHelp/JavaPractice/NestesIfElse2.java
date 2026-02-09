package start;
import java.util.Scanner;
public class NestesIfElse2 {

	public static void main(String[] args) {
		System.out.println("Enter the vallue of x: ");
		Scanner sc= new Scanner(System.in);
		
		int x,y;
		x= sc.nextInt();
		
		if(x>0) {
			if(x==0)
			{
				y=0;
			}
			else
			{
				y=1;
			}
		}
		else
		{
			y=-1;
		}
		System.out.println("the vallue of y is : "+y);

	}

}
