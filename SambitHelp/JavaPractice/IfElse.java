package start;

public class IfElse {

	public static void main(String[] args) {
		int number[]= {1,3,2,4,6,5,8,9,10};
		int even=0 , odd=0;
		for(int i=0;i<number.length;i++)
		{
			if(number[i]%2==0)
			{
				even+=1;
			}
			else
			{
				odd+=1;
			}
		}
		System.out.println("the numbeer of even number is : "+even);
		System.out.println("the numbeer of odd number is : "+odd);
			
		
			
		
	}

}
