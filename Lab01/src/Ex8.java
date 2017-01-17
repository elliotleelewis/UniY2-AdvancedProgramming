import java.util.Scanner;
public class Ex8
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a temperature with a unit (i.e. 27C, 84F): ");
		String input = in.nextLine();
		char unit = input.toUpperCase().charAt(input.length() - 1);
		int value = Integer.parseInt(input.substring(0, input.length() - 1));
		switch(unit) {
			case 'C':
				System.out.println("Result: " + ((int) ((9F/5F) * value) + 32F) + "F");
				break;
			case 'F':
				System.out.println("Result: " + (int) ((5F/9F) * (((float) value) - 32F)) + "F");
				break;
			default:
				System.out.println("Invalid unit.");
				break;
		}
		in.close();
	}
}