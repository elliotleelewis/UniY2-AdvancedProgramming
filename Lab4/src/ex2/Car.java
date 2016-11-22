package ex2;
/**
 * This class extends the abstract class Vehicle.
 * @author Elliot
 */
public class Car extends Vehicle
{
	/**
	 * Constructor calls constructor of super class
	 * @param age
	 */
	public Car(int age)
	{
		super(age);
	}
	/**
	 * Returns the tax value for the car
	 * @return tax value
	 */
	@Override
	public int taxValue()
	{
		return super.getAge() * 25;
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
