package ex4;
public class Administrator extends Employee
{
	private String officeHours;
	private int rank;
	public Administrator(String name, String address, int phone_number, String email_address)
	{
		super(name, address, phone_number, email_address);
	}
	public String getOfficeHours()
	{
		return officeHours;
	}
	public void setOfficeHours(String officeHours)
	{
		this.officeHours = officeHours;
	}
	public int getRank()
	{
		return rank;
	}
	public void setRank(int rank)
	{
		this.rank = rank;
	}
	public String toString()
	{
		String out = super.toString();
		if(getOfficeHours() != null) {
			out += "Office Hours: " + getOfficeHours() + System.lineSeparator();
		}
		out += "Rank: " + getRank() + System.lineSeparator();
		return out;
	}
}