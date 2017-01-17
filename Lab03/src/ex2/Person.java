package ex2;
public class Person
{
	private String name, address, email_address;
	private int phone_number;
	public Person()
	{
		//
	}
	public Person(String name, String address, int phone_number, String email_address)
	{
		this.name = name;
		this.address = address;
		this.phone_number = phone_number;
		this.email_address = email_address;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public int getPhone()
	{
		return phone_number;
	}
	public void setPhone(int phone_number)
	{
		this.phone_number = phone_number;
	}
	public String getEmail()
	{
		return email_address;
	}
	public void setEmail(String email_address)
	{
		this.email_address = email_address;
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
		out += System.lineSeparator();
		return out;
	}
}