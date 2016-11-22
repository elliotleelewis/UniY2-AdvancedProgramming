package ex1;
class Person implements Comparable<Object>
{
	private String firstName;
	private String lastName;
	private int age;
	public Person(String firstName, String lastName, int age)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public int getAge()
	{
		return age;
	}
	public int compareTo(Object anotherPerson) throws ClassCastException
	{
		if(!(anotherPerson instanceof Person))
			throw new ClassCastException("A Person object expected.");
		int anotherPersonAge = ((Person) anotherPerson).getAge();
		return this.age - anotherPersonAge;
	}
	public String toString()
	{
		return getFirstName() + " " + getLastName() + ", " + getAge();
	}
}