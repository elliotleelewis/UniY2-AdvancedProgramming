public class BankAccount
{
	private double balance;
	public BankAccount() {
	}
	public BankAccount(double balance) {
		this.balance = balance;
	}
	public void deposit(double amount) {
		balance += amount;
	}
	public void widthdraw(double amount) {
		balance -= amount;
	}
	public double getBalance() {
		return balance;
	}
}