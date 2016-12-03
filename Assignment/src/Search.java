import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
 * This class is the class that controls the GUI of the search menu of the application. It allows users to search
 * through the employees by their name.
 *
 * @author Elliot Lewis
 */
class Search extends JFrame
{
	private static JTextField search;
	private static JList results;
	private static final int xPad = 10;
	private static final int yPad = 10;
	private static final int labelWidth = 50;
	private static final int labelHeight = 28;
	/**
	 *
	 */
	Search()
	{
		super("Search");
		setLayout(null);
		setSize((xPad * 2) + 200, (yPad * 2) + (labelHeight * 9));
		drawGUI();
	}
	/**
	 *
	 */
	private void drawGUI()
	{
		int row = 0;
		// Search
		JLabel searchL = new JLabel("Search:", SwingConstants.RIGHT);
		searchL.setBounds(xPad, yPad + (labelHeight * row), labelWidth, labelHeight);
		add(searchL);
		search = new JTextField();
		search.addKeyListener(new KeyListener()
		{
			/**
			 *
			 */
			@Override
			public void keyTyped(KeyEvent e)
			{
				ArrayList<Employee> employees = MainForm.getEmployees();
				ArrayList<Employee> matches = new ArrayList<Employee>();
				String query = search.getText();
				for(Employee emp : employees) {
					if(emp.getName().toLowerCase().contains(query.toLowerCase())) {
						matches.add(emp);
					}
				}
				results.setListData(matches.toArray());
			}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
		search.setBounds(xPad + labelWidth, yPad + (labelHeight * row), 200 - labelWidth, labelHeight);
		add(search);
		// Results
		row++;
		results = new JList();
		results.setListData(MainForm.getEmployees().toArray());
		results.setBounds(xPad, yPad + (labelHeight * row), 200, labelHeight * 6);
		add(results);
		// Select Button
		row += 6;
		JButton select = new JButton("Select");
		select.addActionListener(new ActionListener()
		{
			/**
			 *
			 */
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Employee emp = (Employee) results.getSelectedValue();
				int index = MainForm.getEmployees().indexOf(emp);
				MainForm.setSelectedEmployeeIndex(index);
				Search.this.dispose();
			}
		});
		select.setBounds(xPad, yPad + (labelHeight * row), 100, labelHeight);
		add(select);
		// Cancel Button
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener()
		{
			/**
			 *
			 */
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Search.this.dispose();
			}
		});
		cancel.setBounds(xPad + 100, yPad + (labelHeight * row), 100, labelHeight);
		add(cancel);
	}
}