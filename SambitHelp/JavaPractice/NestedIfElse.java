package start;

public class NestedIfElse {

	public static void main(String[] args) {
		int a=20,b=49,c=67;
		if(a>b)
		{
			if(a>c)
			{
				System.out.println(a+ "is largest among these number");
			}
			else
			{
				System.out.println(c+ "is largest among these number");
			}
		}
		if(c>b)
		{
			System.out.println(c+ "is largest among these number");
		}
		else
		{
			System.out.println(b+"is largest among these number");
		}
	}

}
