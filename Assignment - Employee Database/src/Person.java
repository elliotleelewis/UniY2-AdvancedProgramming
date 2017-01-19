/**
 * The {@link Person} class is used to store details about an individual people stored within the system. It works very
 * similarly to a model in the MVC design pattern.
 *
 * @author Elliot Lewis
 * @version 1.0
 */
class Person
{
	private String name, natInscNo, dob, address, postcode;
	private char gender;
	/**
	 * Constructor for the {@link Person} class.
	 */
	Person()
	{
	}
	/**
	 * Constructor allowing the cloning of another {@link Person person}. It sets all of the {@link Person person}'s
	 * properties to the ones of the {@link Person person} passed in through the {@link Person} parameter.
	 *
	 * @param p
	 * 		{@link Person} object to clone.
	 */
	Person(Person p)
	{
		setName(p.getName());
		setGender(p.getGender());
		setNatInscNo(p.getNatInscNo());
		setDob(p.getDob());
		setAddress(p.getAddress());
		setPostcode(p.getPostcode());
	}
	/**
	 * Gets the {@link Person employee}'s name.
	 *
	 * @return {@link Person}'s name.
	 */
	String getName()
	{
		return name;
	}
	/**
	 * Sets the {@link Person employee}'s name.
	 *
	 * @param name
	 * 		{@link Person}'s new name.
	 */
	void setName(String name)
	{
		if(name != null) this.name = name;
	}
	/**
	 * Gets the {@link Person employee}'s gender.
	 *
	 * @return {@link Person}'s gender.
	 */
	char getGender()
	{
		return gender;
	}
	/**
	 * Sets the {@link Person employee}'s gender.
	 *
	 * @param gender
	 * 		{@link Person}'s new gender.
	 */
	void setGender(char gender)
	{
		this.gender = gender;
	}
	/**
	 * Gets the {@link Person employee}'s national insurance number.
	 *
	 * @return {@link Person}'s national insurance number.
	 */
	String getNatInscNo()
	{
		return natInscNo;
	}
	/**
	 * Sets the {@link Person employee}'s national insurance number.
	 *
	 * @param natInscNo
	 * 		{@link Person}'s new national insurance number.
	 */
	void setNatInscNo(String natInscNo)
	{
		if(natInscNo != null) this.natInscNo = natInscNo;
	}
	/**
	 * Gets the {@link Person employee}'s date of birth.
	 *
	 * @return {@link Person}'s date of birth.
	 */
	String getDob()
	{
		return dob;
	}
	/**
	 * Sets the {@link Person employee}'s date of birth.
	 *
	 * @param dob
	 * 		{@link Person}'s new date of birth.
	 */
	void setDob(String dob)
	{
		if(dob != null) this.dob = dob;
	}
	/**
	 * Gets the {@link Person employee}'s address.
	 *
	 * @return {@link Person}'s address.
	 */
	String getAddress()
	{
		return address;
	}
	/**
	 * Sets the {@link Person employee}'s address.
	 *
	 * @param address
	 * 		{@link Person}'s new address.
	 */
	void setAddress(String address)
	{
		if(address != null) this.address = address;
	}
	/**
	 * Gets the {@link Person employee}'s postcode.
	 *
	 * @return {@link Person}'s postcode.
	 */
	String getPostcode()
	{
		return postcode;
	}
	/**
	 * Sets the {@link Person employee}'s postcode.
	 *
	 * @param postcode
	 * 		{@link Person}'s new postcode.
	 */
	void setPostcode(String postcode)
	{
		if(postcode != null) this.postcode = postcode;
	}
	/**
	 * @return {@link Person} object represented as a string. Specifically, their {@link Person#name name}.
	 */
	public String toString()
	{
		return getName();
	}
}