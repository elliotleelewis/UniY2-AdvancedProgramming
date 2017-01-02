import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
/**
 * This class is the Database Access Object, its used to bridge the application and the database and is used whenever
 * data need to be created, retrieved, updated and deleted from the database.
 *
 * @author Elliot Lewis
 */
@SuppressWarnings({"unused", "WeakerAccess"})
class EmployeeDAO
{
	private static Connection c;
	private static Statement s;
	/**
	 * The {@link EmployeeDAO} constructor initialises the connection with the database and creates the statement that
	 * will be used by the queries within the class's functions.
	 */
	EmployeeDAO()
	{
		try {
			c = DriverManager.getConnection("jdbc:sqlite:res/empdb.sqlite");
			s = c.createStatement();
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
	static Statement getConnection()
	{
		return s;
	}
	/**
	 * Attempts to close the connection to the SQLite database.
	 *
	 * @throws SQLException
	 * 		If unable to close connection to the SQLite database.
	 */
	static void closeConnection() throws SQLException
	{
		c.close();
	}
	/**
	 * Converts ResultSet query results to {@link Employee} object.
	 *
	 * @param res
	 * 		ResultSet query results to convert.
	 *
	 * @return Converted ResultSet.
	 *
	 * @throws SQLException
	 * 		If column that's queried doesn't exist in the ResultSet.
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
		if(res.getBytes("Image") != null)
			e.setImage(Toolkit.getDefaultToolkit().createImage(res.getBytes("Image")));
		return e;
	}
	/**
	 * Converts a result set containing multiple employees, to an ArrayList containing {@link Employee} objects that
	 * represent the multiple employees.
	 *
	 * @param res
	 * 		The ResultSet to convert.
	 *
	 * @return The ArrayList of converted employees.
	 *
	 * @throws SQLException
	 * 		If the result set fails to iterate.
	 */
	private static ArrayList<Employee> convertEmployees(ResultSet res) throws SQLException
	{
		ArrayList<Employee> array = new ArrayList<Employee>();
		while(res.next()) {
			array.add(convertEmployee(res));
		}
		return array;
	}
	/**
	 * Selects all employees from the SQLite database.
	 *
	 * @return ArrayList containing {@link Employee} objects representing each database entity.
	 *
	 * @throws SQLException
	 * 		If the query fails.
	 */
	static ArrayList<Employee> selectAllEmployees() throws SQLException
	{
		ResultSet res = getConnection().executeQuery("SELECT * FROM employees");
		return convertEmployees(res);
	}
	/**
	 * Selects employee from the SQLite database with the name matching the one passed into the function.
	 *
	 * @param name
	 * 		The name to query for in the SQLite database.
	 *
	 * @return The Employee object representing the correct employee.
	 *
	 * @throws SQLException
	 * 		If the query fails.
	 */
	static Employee selectEmployeeByName(String name) throws SQLException
	{
		PreparedStatement s = c.prepareStatement("SELECT * FROM employees WHERE Name=? LIMIT 1");
		s.setString(1, name);
		ResultSet res = s.executeQuery();
		Employee e = null;
		while(res.next()) {
			e = convertEmployee(res);
		}
		return e;
	}
	/**
	 * Inserts {@link Employee} object into the SQLite database.
	 *
	 * @param e
	 * 		The {@link Employee} object to insert.
	 *
	 * @return A boolean representing the success of the query.
	 *
	 * @throws SQLException
	 * 		If query fails.
	 */
	static boolean insertEmployee(Employee e) throws SQLException
	{
		return insertEmployeeAtId(e, null);
	}
	/**
	 * Inserts {@link Employee} object into the SQLite database at a specified ID.
	 *
	 * @param e
	 * 		The {@link Employee} object to insert.
	 * @param id
	 * 		The {@link Employee#id ID} to insert the {@link Employee} at.
	 *
	 * @return A boolean representing the success of the query.
	 *
	 * @throws SQLException
	 * 		If query fails.
	 */
	static boolean insertEmployeeAtId(Employee e, String id) throws SQLException
	{
		PreparedStatement s;
		if(id != null) {
			s = c.prepareStatement("INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			s.setString(1, id);
		}
		else {
			s = c.prepareStatement("INSERT INTO employees (Name, Gender, DOB, Address, Postcode, NIN, JobTitle, StartDate, Salary, Email, Image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		}
		int offset = (id != null) ? 1 : 0;
		s.setString(1 + offset, e.getName());
		s.setString(2 + offset, String.valueOf(e.getGender()));
		s.setString(3 + offset, e.getDob());
		s.setString(4 + offset, e.getAddress());
		s.setString(5 + offset, e.getPostcode());
		s.setString(6 + offset, e.getNatInscNo());
		s.setString(7 + offset, e.getTitle());
		s.setString(8 + offset, e.getStartDate());
		s.setString(9 + offset, e.getSalary());
		s.setString(10 + offset, e.getEmail());
		s.setBytes(11 + offset, convertImage(e.getImage()));
		return s.execute();
	}
	/**
	 * This function publishes an updated employee to the SQLite database.
	 *
	 * @param e
	 * 		The employee to update.
	 *
	 * @return A boolean representing the success of the query.
	 *
	 * @throws SQLException
	 * 		If query fails.
	 */
	static int updateEmployee(Employee e) throws SQLException
	{
		PreparedStatement s = c.prepareStatement("UPDATE employees SET Name=?, Gender=?, DOB=?, Address=?, Postcode=?, NIN=?, JobTitle=?, StartDate=?, Salary=?, Email=?, Image=? WHERE ID=?");
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
		s.setBytes(11, convertImage(e.getImage()));
		s.setString(12, e.getId());
		return s.executeUpdate();
	}
	/**
	 * Deletes an employee from the SQLite database that has the {@link Employee#id ID} passed in the parameter.
	 *
	 * @param id
	 * 		The {@link Employee#id ID} of the employee to delete.
	 *
	 * @return A boolean representing the success of the query.
	 *
	 * @throws SQLException
	 * 		If query fails.
	 */
	static boolean deleteEmployeeById(String id) throws SQLException
	{
		PreparedStatement s = c.prepareStatement("DELETE FROM employees WHERE ID=?");
		s.setString(1, id);
		return s.executeUpdate() > 0;
	}
	/**
	 * This function converts an image object to a byte array that can be stored in an SQLite database.
	 *
	 * @param image
	 * 		The image to be converted.
	 *
	 * @return The output byte array.
	 */
	private static byte[] convertImage(Image image)
	{
		ByteArrayOutputStream baos = null;
		BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		try {
			baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "png", baos);
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				baos.close();
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		return baos.toByteArray();
	}
}