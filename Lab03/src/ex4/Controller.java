package ex4;
public class Controller
{
	public static void main(String[] args)
	{
		Administrator test1 = new Administrator("Jeff Jeffries", "Canterbury", 56756756, "jeffjeff@email.com");
		test1.setOfficeHours("Mon-Fri 9am-5pm");
		System.out.println(test1.toString());
		Lecturer test2 = new Lecturer("Claire Clark", "Newcastle", 99887766, "cla.squared@email.com", "Professor");
		System.out.println(test2.toString());
	}
}