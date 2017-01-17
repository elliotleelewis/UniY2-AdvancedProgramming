import java.util.Scanner;
public class Ex9
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a value: £");
		float value = in.nextFloat();
		in.nextLine();
		System.out.print("Please enter a currency (USD, EUR, AUD): ");
		String currency = in.nextLine();
		switch(currency) {
			case "USD":
				System.out.println("Result: $" + (value * 1.5F));
				break;
			case "EUR":
				System.out.println("Result: €" + (value * 1.4F));
				break;
			case "AUD":
				System.out.println("Result: $" + (value * 1.9F));
				break;
			default:
				System.out.println("Invalid currency.");
				break;
		}
		in.close();
	}
}