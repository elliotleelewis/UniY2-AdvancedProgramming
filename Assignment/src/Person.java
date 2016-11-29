/**
 * @author Elliot Lewis
 */
class Person
{
	private String name, natInscNo, dob, address, postcode;
	private char gender;
	String getName()
	{
		return name;
	}
	void setName(String name)
	{
		if(name != null) this.name = name;
	}
	char getGender()
	{
		return gender;
	}
	void setGender(char gender)
	{
		this.gender = gender;
	}
	String getNatInscNo()
	{
		return natInscNo;
	}
	void setNatInscNo(String natInscNo)
	{
		if(natInscNo != null) this.natInscNo = natInscNo;
	}
	String getDob()
	{
		return dob;
	}
	void setDob(String dob)
	{
		if(dob != null) this.dob = dob;
	}
	String getAddress()
	{
		return address;
	}
	void setAddress(String address)
	{
		if(address != null) this.address = address;
	}
	String getPostcode()
	{
		return postcode;
	}
	void setPostcode(String postcode)
	{
		if(postcode != null) this.postcode = postcode;
	}
	public String toString()
	{
		String out = "";
		if(getName() != null) {
			out += "Name: " + getName() + System.getProperty("line.separator");
		}
		out += "Gender: " + getGender() + System.getProperty("line.separator");
		if(getNatInscNo() != null) {
			out += "NatInscNo: " + getNatInscNo() + System.getProperty("line.separator");
		}
		if(getDob() != null) {
			out += "DoB: " + getDob() + System.getProperty("line.separator");
		}
		if(getAddress() != null) {
			out += "Address: " + getAddress() + System.getProperty("line.separator");
		}
		if(getPostcode() != null) {
			out += "Address: " + getPostcode() + System.getProperty("line.separator");
		}
		return out;
	}
}