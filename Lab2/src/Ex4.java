import java.util.Random;
public class Ex4
{
	static Random random = new Random();
	public static void main(String[] args)
	{
		BankAccount account = new BankAccount(10);
		float deposit = random.nextFloat() * 50;
		System.out.println("Deposit: " + deposit);
		account.deposit(deposit);
		float withdraw = random.nextFloat() * 50;
		System.out.println("Withdraw: " + withdraw);
		account.widthdraw(withdraw);
		System.out.println("Balance: " + account.getBalance());
	}
}