package ex2;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Employee extends Person
{
	private String department;
	private double salary;
	private Date startDate;
	private SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	public Employee(String name, String address, int phone_number, String email_address)
	{
		super(name, address, phone_number, email_address);
	}
	public Employee(String department, double salary, Date startDate)
	{
		super();
		this.department = department;
		this.salary = salary;
		this.startDate = startDate;
	}
	public String getDepartment()
	{
		return department;
	}
	public void setDepartment(String department)
	{
		this.department = department;
	}
	public double getSalary()
	{
		return salary;
	}
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	public Date getStartDate()
	{
		return startDate;
	}
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
	public String toString()
	{
		String out = "";
		if(getName() != null) {
			out += "Name: " + getName() + System.lineSeparator();
		}
		if(getAddress() != null) {
			out += "Address: " + getAddress() + System.lineSeparator();
		}
		if(getPhone() != 0) {
			out += "Phone: " + getPhone() + System.lineSeparator();	
		}
		if(getEmail() != null) {
			out += "Email: " + getEmail() + System.lineSeparator();
		}
		if(getDepartment() != null) {
			out += "Department: " + getDepartment() + System.lineSeparator();
		}
		if(getSalary() != 0d) {
			out += "Salary: £" + getSalary() + System.lineSeparator();
		}
		if(getStartDate() != null) {
			out += "Start Date: " + dateformat.format(getStartDate()) + System.lineSeparator();
		}
		out += System.lineSeparator();
		return out;
	}
}