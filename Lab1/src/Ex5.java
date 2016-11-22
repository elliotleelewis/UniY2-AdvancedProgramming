import java.util.Scanner;
public class Ex5
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter an amount: ");
		float amount = in.nextFloat();
		in.nextLine();
		System.out.print("Please enter an account type (S = Saver, D = Deposit, C = Child, L = Long): ");
		String type = in.nextLine();
		switch(type){
			case "S":
				amount *= 1.03;
				break;
			case "D":
				amount *= 1.005;
				break;
			case "C":
				amount *= 1.015;
				break;
			case "L":
				amount *= 1.04;
				break;
			default:
				System.out.println("Invalid input.");
				break;
		}
		System.out.println("Value after one year: " + amount);
		in.close();
	}
}