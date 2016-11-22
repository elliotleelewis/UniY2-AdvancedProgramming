public class Person
{
	private String name, natInscNo, dob, address, postcode;
	private char gender;
	public Person()
	{
		//
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public char getGender()
	{
		return gender;
	}
	public void setGender(char gender)
	{
		this.gender = gender;
	}
	public String getNatInscNo()
	{
		return natInscNo;
	}
	public void setNatInscNo(String natInscNo)
	{
		this.natInscNo = natInscNo;
	}
	public String getDob()
	{
		return dob;
	}
	public void setDob(String dob)
	{
		this.dob = dob;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getPostcode()
	{
		return postcode;
	}
	public void setPostcode(String postcode)
	{
		this.postcode = postcode;
	}
	public String toString()
	{
		String out = "";
		if(getName() != null) {
			out += "Name: " + getName() + System.lineSeparator();
		}
		out += "Gender: " + getGender() + System.lineSeparator();
		if(getNatInscNo() != null) {
			out += "NatInscNo: " + getNatInscNo() + System.lineSeparator();
		}
		if(getDob() != null) {
			out += "DoB: " + getDob() + System.lineSeparator();
		}
		if(getAddress() != null) {
			out += "Address: " + getAddress() + System.lineSeparator();
		}
		if(getPostcode() != null) {
			out += "Address: " + getPostcode() + System.lineSeparator();
		}
		return out;
	}
}