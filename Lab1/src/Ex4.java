import java.util.Scanner;
public class Ex4
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int total = 0;
		while(true) {
			System.out.print("Please enter a number between 1 and 100 (enter -9999 to stop): ");
			int input = in.nextInt();
			if(input == -9999) {
				System.out.println("Total: " + total);
				in.close();
				return;
			}
			if(input < 1 || input > 100) {
				System.out.println("Invalid input!");
			}
			else {
				total += input;
			}
		}
	}
}