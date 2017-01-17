import java.util.Random;
public class Ex7
{
	private static void bubblesort(int[] a)
	{
		for(int i = 0; i < a.length; i++) {
			for(int j = 1; j < (a.length - i); j++) {
				if(a[j-1] > a[j]) {
					swap(a, j-1, j);
				}
			}
		}
	}
	private static void swap(int[] a, int x, int y)
	{
		int tempStore = a[x];
		a[x] = a[y];
		a[y] = tempStore;
	}
	public static void main(String[] args)
	{
		Random random = new Random();
		int[] array = new int[50];
		for(int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(100);
		}
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		bubblesort(array);
		System.out.println();
		System.out.println();
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
}