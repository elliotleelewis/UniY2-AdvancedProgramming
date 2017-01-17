package ex3;
public class Controller
{
	public static void main(String[] args)
	{
		Person test1 = new Person("Tim Thompson", "Manchester", 69696969, "timpson@email.com");
		System.out.println(test1.toString());
		Student test2 = new Student("Levi Lewis", "Glasgow", 12312312, "lele@email.com", true);
		System.out.println(test2.toString());
	}
}