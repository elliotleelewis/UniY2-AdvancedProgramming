package ex2;
import java.util.Date;
public class Controller
{
	public static void main(String[] args)
	{
		Person test = new Person("James", "world", 1234, "james@email.com");
		test.setAddress("London");
		System.out.println(test.toString());
		Employee test2 = new Employee("Computer Science", 20000d, new Date(1431216000000l));
		test2.setName("Bob Barker");
		System.out.println(test2.toString());
	}
}