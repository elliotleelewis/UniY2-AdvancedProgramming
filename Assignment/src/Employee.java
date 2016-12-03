import java.awt.*;
/**
 * @author Elliot Lewis
 */
class Employee extends Person
{
	private String email, title, id, salary, startDate;
	private Image image;
	/**
	 * Constructor for the {@link Employee} class. It calls the {@link Person#Person() constructor} of the super class.
	 */
	Employee()
	{
		super();
	}
	/**
	 * Constructor allowing the cloning of another {@link Employee employee}. Calls the {@link Person#Person(Person)
	 * constructor} of the super class and then sets all of the {@link Employee employee}'s properties to the ones of
	 * the {@link Employee employee} passed in through the {@link Employee} parameter.
	 *
	 * @param e {@link Employee} object to clone.
	 */
	Employee(Employee e)
	{
		super(e);
		setId(e.getId());
		setEmail(e.getEmail());
		setTitle(e.getTitle());
		setSalary(e.getSalary());
		setStartDate(e.getStartDate());
		setImage(e.getImage());
	}
	/**
	 * Gets the {@link Employee employee}'s email address.
	 *
	 * @return {@link Employee}'s email address.
	 */
	String getEmail()
	{
		return email;
	}
	/**
	 * Sets the {@link Employee employee}'s email address.
	 *
	 * @param email {@link Employee}'s new email address.
	 */
	void setEmail(String email)
	{
		if(email != null) this.email = email;
	}
	/**
	 * Gets the {@link Employee employee}'s job title.
	 *
	 * @return {@link Employee}'s job title.
	 */
	String getTitle()
	{
		return title;
	}
	/**
	 * Sets the {@link Employee employee}'s job title.
	 *
	 * @param title {@link Employee}'s new job title.
	 */
	void setTitle(String title)
	{
		if(title != null) this.title = title;
	}
	/**
	 * Gets the {@link Employee employee}'s ID.
	 *
	 * @return {@link Employee}'s ID.
	 */
	String getId()
	{
		return id;
	}
	/**
	 * Sets the {@link Employee employee}'s ID.
	 *
	 * @param id {@link Employee}'s new ID.
	 */
	void setId(String id)
	{
		if(id != null) this.id = id;
	}
	/**
	 * Gets the {@link Employee employee}'s salary.
	 *
	 * @return {@link Employee}'s salary.
	 */
	String getSalary()
	{
		return salary;
	}
	/**
	 * Sets the {@link Employee employee}'s salary.
	 *
	 * @param salary {@link Employee}'s new salary.
	 */
	void setSalary(String salary)
	{
		if(salary != null) this.salary = salary;
	}
	/**
	 * Gets the {@link Employee employee}'s start date.
	 *
	 * @return {@link Employee}'s start date.
	 */
	String getStartDate()
	{
		return startDate;
	}
	/**
	 * Sets the {@link Employee employee}'s start date.
	 *
	 * @param startDate {@link Employee}'s new start date.
	 */
	void setStartDate(String startDate)
	{
		if(startDate != null) this.startDate = startDate;
	}
	/**
	 * Gets the {@link Employee employee}'s image.
	 *
	 * @return {@link Employee}'s image.
	 */
	Image getImage()
	{
		return image;
	}
	/**
	 * Sets the {@link Employee employee}'s image.
	 *
	 * @param image {@link Employee}'s new image.
	 */
	void setImage(Image image)
	{
		if(image != null) this.image = image;
	}
}