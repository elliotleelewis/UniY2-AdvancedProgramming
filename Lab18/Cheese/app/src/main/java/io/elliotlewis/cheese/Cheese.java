package io.elliotlewis.cheese;
import java.io.*;
/**
 * Elliot Lewis © 2017
 */
class Cheese implements Serializable
{
	private String name;
	private String details;
	Cheese(String name, String details)
	{
		this.name = name;
		this.details = details;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDetails()
	{
		return details;
	}
	public void setDetails(String details)
	{
		this.details = details;
	}
}