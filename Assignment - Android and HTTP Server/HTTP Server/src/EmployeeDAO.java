import java.sql.*;
import java.util.*;
import javax.swing.*;
/**
 * This class is the Database Access Object, its used to bridge the application and the database and is used whenever
 * data need to be created, retrieved, updated and deleted from the database.
 *
 * @author Elliot Lewis
 * @version 1.0
 */
@SuppressWarnings({"unused", "WeakerAccess"})
class EmployeeDAO
{
	private static Connection c;
	/**
	 * The {@link EmployeeDAO} constructor initialises the connection with the database and creates the statement that
	 * will be used by the queries within the class's functions.
	 */
	EmployeeDAO(String dbPath)
	{
		try {
			c = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to connect to DB.");
			e.printStackTrace();
		}
	}
	/**
	 * Returns the connection to the SQLite database.
	 *
	 * @return Connection to the SQLite database.
	 */
	static Connection getConnection()
	{
		return c;
	}
	/**
	 * Attempts to close the connection to the SQLite database.
	 *
	 * @throws SQLException If unable to close connection to the SQLite database.
	 */
	static void closeConnection() throws SQLException
	{
		c.close();
	}
	/**
	 * Converts ResultSet query results to {@link Employee} object.
	 *
	 * @param res ResultSet query results to convert.
	 * @return Converted ResultSet.
	 * @throws SQLException If column that's queried doesn't exist in the ResultSet.
	 */
	private static Employee convertEmployee(ResultSet res) throws SQLException
	{
		Employee e = new Employee();
		e.setId(res.getString("ID"));
		e.setName(res.getString("Name"));
		e.setGender(res.getString("Gender").charAt(0));
		e.setDob(res.getString("DOB"));
		e.setAddress(res.getString("Address"));
		e.setPostcode(res.getString("Postcode"));
		e.setNatInscNo(res.getString("NIN"));
		e.setTitle(res.getString("JobTitle"));
		e.setStartDate(res.getString("StartDate"));
		e.setSalary(res.getString("Salary"));
		e.setEmail(res.getString("Email"));
		return e;
	}
	/**
	 * Converts a result set containing multiple employees, to an ArrayList containing {@link Employee} objects that
	 * represent the multiple employees.
	 *
	 * @param res The ResultSet to convert.
	 * @return The ArrayList of converted employees.
	 * @throws SQLException If the result set fails to iterate.
	 */
	private static ArrayList<Employee> convertEmployees(ResultSet res) throws SQLException
	{
		ArrayList<Employee> array = new ArrayList<>();
		while(res.next()) {
			array.add(convertEmployee(res));
		}
		return array;
	}
	/**
	 * Selects all employees from the SQLite database.
	 *
	 * @return ArrayList containing {@link Employee} objects representing each database entity.
	 * @throws SQLException If the query fails.
	 */
	static ArrayList<Employee> showAllRecords() throws SQLException
	{
		ResultSet res = getConnection().createStatement().executeQuery("SELECT * FROM employees");
		return convertEmployees(res);
	}
	static Employee selectEmployeeById(String id) throws SQLException
	{
		PreparedStatement s = getConnection().prepareStatement("SELECT * FROM employees WHERE ID = ?");
		s.setString(1, id);
		ResultSet res = s.executeQuery();
		res.next();
		return convertEmployee(res);
	}
	/**
	 * Inserts {@link Employee} object into the SQLite database.
	 *
	 * @param e The {@link Employee} object to insert.
	 * @return A boolean representing the success of the query.
	 * @throws SQLException If query fails.
	 */
	static boolean insertEmployee(Employee e) throws SQLException
	{
		PreparedStatement s = getConnection().prepareStatement("INSERT INTO employees (Name, Gender, DOB, Address, Postcode, NIN, JobTitle, StartDate, Salary, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		s.setString(1, e.getName());
		s.setString(2, String.valueOf(e.getGender()));
		s.setString(3, e.getDob());
		s.setString(4, e.getAddress());
		s.setString(5, e.getPostcode());
		s.setString(6, e.getNatInscNo());
		s.setString(7, e.getTitle());
		s.setString(8, e.getStartDate());
		s.setString(9, e.getSalary());
		s.setString(10, e.getEmail());
		return s.execute();
	}
	/**
	 * This function publishes an updated employee to the SQLite database.
	 *
	 * @param e The employee to update.
	 * @return A boolean representing the success of the query.
	 * @throws SQLException If query fails.
	 */
	static int updateEmployee(Employee e) throws SQLException
	{
		PreparedStatement s = getConnection().prepareStatement("UPDATE employees SET Name=?, Gender=?, DOB=?, Address=?, Postcode=?, NIN=?, JobTitle=?, StartDate=?, Salary=?, Email=? WHERE ID=?");
		s.setString(1, e.getName());
		s.setString(2, String.valueOf(e.getGender()));
		s.setString(3, e.getDob());
		s.setString(4, e.getAddress());
		s.setString(5, e.getPostcode());
		s.setString(6, e.getNatInscNo());
		s.setString(7, e.getTitle());
		s.setString(8, e.getStartDate());
		s.setString(9, e.getSalary());
		s.setString(10, e.getEmail());
		s.setString(11, e.getId());
		return s.executeUpdate();
	}
	/**
	 * Deletes an employee from the SQLite database that has the {@link Employee#id ID} passed in the parameter.
	 *
	 * @param id The {@link Employee#id ID} of the employee to delete.
	 * @return A boolean representing the success of the query.
	 * @throws SQLException If query fails.
	 */
	static boolean deleteEmployeeById(String id) throws SQLException
	{
		PreparedStatement s = getConnection().prepareStatement("DELETE FROM employees WHERE ID=?");
		s.setString(1, id);
		return s.executeUpdate() > 0;
	}
}