package ex2;
/**
 * This class extends the abstract class Vehicle.
 * @author Elliot
 */
public class Motorbike extends Vehicle
{
	/**
	 * Constructor calls constructor of super class
	 * @param age
	 */
	public Motorbike(int age)
	{
		super(age);
	}
	/**
	 * Returns the tax value for the motorbike
	 * @return tax value
	 */
	@Override
	public int taxValue()
	{
		return super.getAge() * 15;
	}
	/**
	 * Returns the type of vehicle
	 * @return vehicle type
	 */
	@Override
	public String vehicleType()
	{
		return this.getClass().getSimpleName();
	}
}