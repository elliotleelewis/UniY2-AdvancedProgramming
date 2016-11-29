import javax.swing.*;
import java.sql.*;
/**
 * @author Elliot Lewis
 */
public class Controller
{
	public static void main(String[] args)
	{
		new EmployeeDAO();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Display d = new Display();
					d.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					d.setVisible(true);
					d.setLocationRelativeTo(null);
				}
				catch(SQLException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
		});
	}
}