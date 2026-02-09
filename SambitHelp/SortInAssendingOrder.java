package start;

public class SortInAssendingOrder {

	public static void main(String[] args) {
		int[]arr = {2,78,93,56,34,1,6};
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr.length-1-i;j++)
			{
				if(arr[j]>arr[j+1])
				{
					int b= arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=b;
							
				}
			}
		}
		for(int num : arr)
		{
			System.out.print(num + " ");
		}
		

	}

}
