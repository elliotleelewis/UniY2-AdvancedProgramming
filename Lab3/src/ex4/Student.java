package ex4;
public class Student extends Person
{
	private boolean enrolled;
	public Student(String name, String address, int phone_number, String email_address, boolean enrolled)
	{
		super(name, address, phone_number, email_address);
		this.enrolled = enrolled;
	}
	public boolean getEnrolled()
	{
		return enrolled;
	}
	public void setEnrolled(boolean enrolled)
	{
		this.enrolled = enrolled;
	}
	public String toString()
	{
		String out = super.toString();
		out += "Enrolled: " + getEnrolled() + System.lineSeparator();
		return out;
	}
}