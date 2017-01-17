package ex1;
import java.util.Arrays;
public class Controller
{
	public static void main(String[] args)
	{
		Person[] people = new Person[4];
		people[0] = new Person("Elliot", "Lewis", 19);
		people[3] = new Person("Brian", "Arnold", 35);
		people[1] = new Person("Steven", "Little", 4);
		people[2] = new Person("Tiffany", "Hughes", 26);
		System.out.println("Unsorted:");
		for(Person p: people) {
			System.out.println(p.toString());
		}
		Arrays.sort(people);
		System.out.println();
		System.out.println("Sorted:");
		for(Person p: people) {
			System.out.println(p.toString());
		}
	}
}