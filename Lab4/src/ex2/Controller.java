package ex2;
/**
 * This controller class runs the main method for the application
 * @author Elliot
 */
public class Controller
{
	/**
	 * The main method of the application. Runs on program launch
	 * @param args Application arguments
	 */
	public static void main(String[] args)
	{
		Vehicle[] vehicles = new Vehicle[2];
		vehicles[0] = new Car(6);
		vehicles[1] = new Motorbike(5);
		for(Vehicle v: vehicles) {
			System.out.println(v.toString());
		}
	}
}