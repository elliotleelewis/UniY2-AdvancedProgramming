public class Person
{
	private String name;
	private int age;
	public Person(Person person) {
		this(person.name, person.age);
	}
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public static Person newInstance(Person person) {
		return new Person(person.name, person.age);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String toString() {
		return name + ", " + age;
	}
}