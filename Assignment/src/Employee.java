import java.awt.*;
/**
 * @author Elliot Lewis
 */
class Employee extends Person
{
	private String email, title, id, salary, startDate;
	private Image image;
	Employee()
	{
		super();
	}
	String getEmail()
	{
		return email;
	}
	void setEmail(String email)
	{
		if(email != null) this.email = email;
	}
	String getTitle()
	{
		return title;
	}
	void setTitle(String title)
	{
		if(title != null) this.title = title;
	}
	String getId()
	{
		return id;
	}
	void setId(String id)
	{
		if(id != null) this.id = id;
	}
	String getSalary()
	{
		return salary;
	}
	void setSalary(String salary)
	{
		if(salary != null) this.salary = salary;
	}
	String getStartDate()
	{
		return startDate;
	}
	void setStartDate(String startDate)
	{
		if(startDate != null) this.startDate = startDate;
	}
	Image getImage()
	{
		return image;
	}
	void setImage(Image image)
	{
		if(image != null) this.image = image;
	}
	public String toString()
	{
		String out = super.toString();
		if(getEmail() != null) {
			out += "Email: " + getEmail() + System.getProperty("line.separator");
		}
		if(getTitle() != null) {
			out += "Title: " + getTitle() + System.getProperty("line.separator");
		}
		if(getId() != null) {
			out += "ID: " + getId() + System.getProperty("line.separator");
		}
		if(getSalary() != null) {
			out += "Salary: " + getSalary() + System.getProperty("line.separator");
		}
		if(getStartDate() != null) {
			out += "StartDate: " + getStartDate() + System.getProperty("line.separator");
		}
		return out;
	}
}