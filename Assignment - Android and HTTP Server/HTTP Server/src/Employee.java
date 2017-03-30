/**
 * The {@link Employee} class is used to store details about an individual employee in the system. It works very
 * similarly to a model in the MVC design pattern.
 *
 * @author Elliot Lewis
 * @version 1.0
 */
class Employee extends Person
{
	private String email, title, id, salary, startDate;
	/**
	 * Constructor for the {@link Employee} class. It calls the {@link Person#Person() constructor} of the super class.
	 */
	Employee()
	{
		super();
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
}