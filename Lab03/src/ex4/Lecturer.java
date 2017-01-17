package ex4;
public class Lecturer extends Employee
{
	private String title;
	public Lecturer(String name, String address, int phone_number, String email_address, String title)
	{
		super(name, address, phone_number, email_address);
		this.title = title;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String toString()
	{
		String out = super.toString();
		if(getTitle() != null) {
			out += "Title: " + getTitle() + System.lineSeparator();
		}
		return out;
	}
}