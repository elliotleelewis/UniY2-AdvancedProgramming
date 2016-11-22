package ex2;
/**
 * This abstract class is the interface I created for the Car and Motorbike classes to extend
 * @author Elliot
 */
public abstract class Vehicle
{
	private int age;
	/**
	 * Constructor of abstract class allows instances of child classes to define the age parameter of this object
	 * @param age
	 */
	public Vehicle(int age)
	{
		this.age = age;
	}
	/**
	 * Calculates tax value of the vehicle
	 * @return tax value
	 */
	public abstract int taxValue();
	/**
	 * Returns the string of the vehicle type
	 * @return vehicle type
	 */
	public abstract String vehicleType();
	/**
	 * @return vehicle age
	 */
	public int getAge()
	{
		return age;
	}
	/**
	 * @return string representing object contents.
	 */
	public String toString()
	{
		String out = "Type: " + vehicleType() + System.lineSeparator();
		out += "Age: " + getAge() + System.lineSeparator();
		out += "Tax: £" + taxValue() + System.lineSeparator();
		return out;
	}
}