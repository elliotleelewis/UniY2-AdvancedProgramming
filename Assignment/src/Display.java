import javax.swing.*;
import java.awt.*;
import java.sql.*;
/**
 * @author Elliot Lewis
 */
class Display extends JFrame
{
	Display() throws SQLException
	{
		super("Employee Database");
		setLayout(new FlowLayout());
		setSize(500, 600);
		drawGUI();
	}
	private void drawGUI() throws SQLException
	{
		Employee e = EmployeeDAO.selectAllEmployees().get(1);
		System.out.println(e.getName());
		ImageIcon icon = new ImageIcon(e.getImage());
		JLabel test = new JLabel(icon);
		add(test);
	}
}