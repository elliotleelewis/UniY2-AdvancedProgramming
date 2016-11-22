public class Ex6
{
	public static void main(String[] args)
	{
		int[] arry = {1, 2, 3, 4, 5, 6};
		System.out.println(arry[1]);
		System.out.println();
		for(int i = 0; i < arry.length; i++) {
			System.out.println(i);
		}
		System.out.println();
		for(int i = 0; i < arry.length; i++) {
			arry[i] = i * 2;
		}
		System.out.println();
		for(int val: arry) {
			System.out.println(val);
		}
	}
}