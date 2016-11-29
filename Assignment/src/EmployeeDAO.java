import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
/**
 * @author Elliot Lewis
 */
@SuppressWarnings({"unused", "WeakerAccess"})
class EmployeeDAO
{
	private static Connection c;
	private static Statement s;
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
	static Statement getConnection()
	{
		return s;
	}
	static void closeConnection()
	{
		try {
			c.close();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable to close connection to DB.");
			e.printStackTrace();
		}
	}
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
	private static ArrayList<Employee> convertEmployees(ResultSet res) throws SQLException
	{
		ArrayList<Employee> array = new ArrayList<Employee>();
		while(res.next()) {
			array.add(convertEmployee(res));
		}
		return array;
	}
	static ArrayList<Employee> selectAllEmployees() throws SQLException
	{
		ResultSet res = getConnection().executeQuery("SELECT * FROM employees");
		return convertEmployees(res);
	}
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
	static boolean insertEmployee(Employee e) throws SQLException
	{
		return insertEmployeeAtId(e, null);
	}
	static boolean insertEmployeeAtId(Employee e, String id) throws SQLException
	{
		PreparedStatement s;
		if(id == null) {
			s = c.prepareStatement("INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		}
		else {
			s = c.prepareStatement("INSERT INTO employees (Name, Gender, DOB, Address, Postcode, NIN, JobTitle, StartDate, Salary, Email, Image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		}
		int offset = (id == null) ? 0 : 1;
		s.setString(1 + offset, id);
		s.setString(2 + offset, e.getName());
		s.setString(3 + offset, String.valueOf(e.getGender()));
		s.setString(4 + offset, e.getDob());
		s.setString(5 + offset, e.getAddress());
		s.setString(6 + offset, e.getPostcode());
		s.setString(7 + offset, e.getNatInscNo());
		s.setString(8 + offset, e.getTitle());
		s.setString(9 + offset, e.getStartDate());
		s.setString(10 + offset, e.getSalary());
		s.setString(11 + offset, e.getEmail());
		ByteArrayOutputStream baos = null;
		BufferedImage bi = new BufferedImage(e.getImage().getWidth(null), e.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.drawImage(e.getImage(), 0, 0, null);
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
		s.setBytes(12 + offset, baos.toByteArray());
		return s.execute();
	}
	static boolean deleteEmployeeById(String id) throws SQLException
	{
		PreparedStatement s = c.prepareStatement("DELETE FROM employees WHERE ID=?");
		s.setString(1, id);
		return s.executeUpdate() > 0;
	}
}