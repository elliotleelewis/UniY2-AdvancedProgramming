import javax.swing.*;
import java.sql.*;
/**
 * The {@link Controller} class contains the main method and is the class that's run when the program is launched.
 *
 * @author Elliot Lewis
 * @version 1.0
 */
public class Controller
{
	/**
	 * The main method is run when the program is launched. It sets the properties and look and feel of the application,
	 * as well as initialising the {@link EmployeeDAO} class and finally, launching the main form.
	 *
	 * @param args
	 * 		Program arguments. Needed for main method but unused by application.
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
					try {
						EmployeeDAO.closeConnection();
					}
					catch(SQLException e1) {
						e1.printStackTrace();
					}
					System.exit(0);
				}
			}
		});
	}
}