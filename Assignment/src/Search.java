import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
 * This class is the class that controls the GUI of the search menu of the application. It allows users to search
 * through the employees by their name.
 *
 * @author Elliot Lewis
 * @version 1.0
 */
class Search extends JFrame
{
	private static JTextField search;
	private static JList results;
	private static JButton select;
	private static final int xPad = 10;
	private static final int yPad = 10;
	private static final int labelWidth = 50;
	private static final int labelHeight = 28;
	/**
	 * The {@link Search} constructor initialises the GUI window and calls the {@link Search#drawGUI()} function.
	 */
	Search()
	{
		super("Search");
		setLayout(null);
		setSize((xPad * 2) + 200, (yPad * 2) + (labelHeight * 9));
		drawGUI();
	}
	/**
	 * Draws each GUI element by first initialising the component, then it sets its properties including the elements
	 * boundaries, next it adds any relevant action listeners, before finally adding the element to the GUI.
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
				select.setEnabled(query.length() == -1);
				results.setListData(matches.toArray());
			}
			@Override
			public void keyPressed(KeyEvent e)
			{
			}
			@Override
			public void keyReleased(KeyEvent e)
			{
			}
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
		select = new JButton("Select");
		select.addActionListener(new ActionListener()
		{
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