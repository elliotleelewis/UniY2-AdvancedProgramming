package ex1;

import java.util.Arrays;

public class OverseasStudent extends Student
{
	private String homeCountry;
	private static String[] possibleCountries = {"Spain", "Nigeria", "France", "Germany"};
	public OverseasStudent(String name, int stuNumber, String homeCountry) throws Exception
	{
		super(name, stuNumber);
		if(Arrays.asList(possibleCountries).contains(homeCountry)) {
			this.homeCountry = homeCountry;
		}
		else {
			throw new Exception("Home Country is invalid.");
		}
	}
	public String getCountry()
	{
		return homeCountry;
	}
}