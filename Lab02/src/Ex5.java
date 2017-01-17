public class Ex5
{
	public static void main(String[] args)
	{
		Person p = new Person("Elliot", 19);
		System.out.println("Person: " + p);
		Person clone = new Person(p);
		System.out.println("Clone: " + clone);
		clone.setAge(75);
		System.out.println("Edited Clone: " + clone);
		Person altClone = Person.newInstance(p);
		System.out.println("Alt Clone: " + altClone);
	}
}