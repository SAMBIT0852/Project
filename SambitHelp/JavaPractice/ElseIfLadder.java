package start;
import  java.util.Scanner;
public class ElseIfLadder {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Eneter the numbver of student: ");
		int n=sc.nextInt();
		int h=0,f=0,s=0,t=0,fa=0;
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter the mark obtained by  each student:  ");
			int m=sc.nextInt();
			
			if(m>=80 && m<=100)
			{
				h++;
			}
			else if(m>=60 && m<=79)
			{
				f++;
			}
			else if(m>=50 && m<=59)
			{
				s++;
			}
			else if(m>40 && m<=49)
			{
				t++;
			}
			else if (m>=0 && m<=40)
			{
				fa++;
			}
			else
			{
				System.out.println("The student doesnot apper in the exam");
			}
			
				
			
				
		}
		System.out.println(h + "These student get the honour degree");
		System.out.println(f + "These student get the first degree");
		System.out.println(s + "These student get the second degree");
		System.out.println(t + "These student get the third degree");
		System.out.println(fa + "These student fail in the exam");

	}

}
