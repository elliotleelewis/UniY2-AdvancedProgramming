import javax.swing.*;
import java.sql.*;
/**
 * @author Elliot Lewis
 * @version 1.0
 */
public class Controller
{
	/**
	 *
	 */
	public static void main(String[] args)
	{
		try {
			// Makes program use mac menu bar at top of screen for the JMenuBar when program is run on macOS.
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			// Makes the program's GUI fit the styling of the OS that its running on.
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		new EmployeeDAO();
		SwingUtilities.invokeLater(new Runnable()
		{
			/**
			 *
			 */
			@Override
			public void run()
			{
				try {
					MainForm form = new MainForm();
					form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					form.setVisible(true);
					form.setResizable(false);
					form.setLocationRelativeTo(null);
				}
				catch(SQLException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
		});
	}
}