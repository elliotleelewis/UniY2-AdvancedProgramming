package ex1;
public class Student
{
	private String name;
	private int stuNumber;
	public Student(String name, int stuNumber) throws IndexOutOfBoundsException
	{
		this.name = name;
		if(stuNumber >= 1 && stuNumber <= 10000){
			this.stuNumber = stuNumber;
		}
		else{
			throw new IndexOutOfBoundsException("Student number must be between 1 and 10,000 inclusively.");
		}
	}
	public String getName()
	{
		return name;
	}
	public int getNum()
	{
		return stuNumber;
	}
}