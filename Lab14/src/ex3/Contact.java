package ex3;
/**
 * Copyright Elliot Lewis © 2017
 */
public class Contact
{
	String name, email;
	public Contact(String name, String email)
	{
		this.name = name;
		this.email = email;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
}