package ex3;
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
		String out = "";
		if(getName() != null) {
			out += "Name: " + getName() + System.lineSeparator();
		}
		if(getAddress() != null) {
			out += "Address: " + getAddress() + System.lineSeparator();
		}
		if(getPhone() != 0) {
			out += "Phone: " + getPhone() + System.lineSeparator();	
		}
		if(getEmail() != null) {
			out += "Email: " + getEmail() + System.lineSeparator();
		}
		out += "Enrolled: " + getEnrolled() + System.lineSeparator();
		out += System.lineSeparator();
		return out;
	}
}