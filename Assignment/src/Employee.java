public class Employee extends Person
{
	private String email, title, id, salary, startDate;
	public Employee()
	{
		super();
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getSalary()
	{
		return salary;
	}
	public void setSalary(String salary)
	{
		this.salary = salary;
	}
	public String getStartDate()
	{
		return startDate;
	}
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	public String toString()
	{
		String out = super.toString();
		if(getEmail() != null) {
			out += "Email: " + getEmail() + System.lineSeparator();
		}
		if(getTitle() != null) {
			out += "Email: " + getTitle() + System.lineSeparator();
		}
		if(getId() != null) {
			out += "Email: " + getId() + System.lineSeparator();
		}
		if(getSalary() != null) {
			out += "Email: " + getSalary() + System.lineSeparator();
		}
		if(getStartDate() != null) {
			out += "Email: " + getStartDate() + System.lineSeparator();
		}
		return out;
	}
}