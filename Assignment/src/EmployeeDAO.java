import java.sql.*;
import java.util.*;
import javax.swing.*;
public class EmployeeDAO
{
	private Connection c;
	private Statement s;
	public EmployeeDAO()
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
	public Statement getConnection()
	{
		return s;
	}
	public void closeConnection()
	{
		try {
			c.close();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to close connection to DB.");
			e.printStackTrace();
		}
	}
	public ArrayList<Employee> selectAllEmployees() throws SQLException
	{
		ResultSet res = getConnection().executeQuery("SELECT * From employees");
		ArrayList<Employee> array = new ArrayList<Employee>();
		while(res.next()) {
			Employee e = new Employee();
			if(res.getString("ID") != null) {
				e.setId(res.getString("ID"));
			}
			if(res.getString("Name") != null) {
				e.setName(res.getString("Name"));
			}
			if(res.getString("Gender") != null) {
				e.setGender(res.getString("Gender").charAt(0));
			}
			if(res.getString("DOB") != null) {
				e.setDob(res.getString("DOB"));
			}
			if(res.getString("Address") != null) {
				e.setAddress(res.getString("Address"));
			}
			if(res.getString("Postcode") != null) {
				e.setPostcode(res.getString("Postcode"));
			}
			if(res.getString("NIN") != null) {
				e.setNatInscNo(res.getString("NIN"));
			}
			if(res.getString("JobTitle") != null) {
				e.setTitle(res.getString("JobTitle"));
			}
			if(res.getString("StartDate") != null) {
				e.setStartDate(res.getString("StartDate"));
			}
			if(res.getString("Salary") != null) {
				e.setSalary(res.getString("Salary"));
			}
			if(res.getString("Email") != null) {
				e.setEmail(res.getString("Email"));
			}
			array.add(e);
		}
		return array;
	}
	public Employee selectEmployeeByName(String name) throws SQLException
	{
		PreparedStatement s = c.prepareStatement("SELECT * FROM employees WHERE Name=? LIMIT 1");
		s.setString(1, name);
		ResultSet res = s.executeQuery();
		Employee e = new Employee();
		while(res.next()) {
			if(res.getString("ID") != null) {
				e.setId(res.getString("ID"));
			}
			if(res.getString("Name") != null) {
				e.setName(res.getString("Name"));
			}
			if(res.getString("Gender") != null) {
				e.setGender(res.getString("Gender").charAt(0));
			}
			if(res.getString("DOB") != null) {
				e.setDob(res.getString("DOB"));
			}
			if(res.getString("Address") != null) {
				e.setAddress(res.getString("Address"));
			}
			if(res.getString("Postcode") != null) {
				e.setPostcode(res.getString("Postcode"));
			}
			if(res.getString("NIN") != null) {
				e.setNatInscNo(res.getString("NIN"));
			}
			if(res.getString("JobTitle") != null) {
				e.setTitle(res.getString("JobTitle"));
			}
			if(res.getString("StartDate") != null) {
				e.setStartDate(res.getString("StartDate"));
			}
			if(res.getString("Salary") != null) {
				e.setSalary(res.getString("Salary"));
			}
			if(res.getString("Email") != null) {
				e.setEmail(res.getString("Email"));
			}
		}
		return e;
	}
	public boolean insertEmployee(Employee e)
	{
		return true;
	}
}