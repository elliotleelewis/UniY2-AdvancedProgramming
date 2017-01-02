import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;
/**
 * @author Elliot Lewis
 */
class MainForm extends JFrame
{
	private static ArrayList<Employee> employees;
	private static Employee selectedEmployee, tempEmployee;
	private static int selectedEmployeeIndex = 0;
	private static JTextField empName, empEmail, empAddress, empPostcode, empNatInscNo, empTitle, empSalary;
	private static JRadioButton empGenderMale, empGenderFemale;
	private static JComboBox empDobDay, empDobMonth, empDobYear, empStartDay, empStartMonth, empStartYear;
	private static JButton empSave, empCancel, prevEmp, nextEmp;
	private static JLabel empCount, empImage;
	private static boolean editable = false;
	private static final int xPad = 10;
	private static final int yPad = 10;
	private static final int labelWidth = 70;
	private static final int labelHeight = 28;
	private static final int maxImageWidth = 150;
	private static final int maxImageHeight = labelHeight * 9;
	/**
	 * @throws SQLException
	 */
	MainForm() throws SQLException
	{
		super("Employee Database");
		setLayout(null);
		setSize((xPad * 2) + labelWidth + 250 + maxImageWidth, (yPad * 2) + labelHeight * 12);
		employees = EmployeeDAO.selectAllEmployees();
		selectedEmployee = employees.get(selectedEmployeeIndex);
		drawGUI();
	}
	/**
	 * @throws SQLException
	 */
	private void drawGUI() throws SQLException
	{
		String[] days = new String[31];
		String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		String[] years = new String[117];
		for(int i = 0; i < days.length; i++) {
			days[i] = String.valueOf(i + 1);
		}
		for(int i = 0; i < years.length; i++) {
			years[i] = String.valueOf(i + 1900);
		}
		// Menu Bar
		JMenuBar menuBar = new JMenuBar();
		// File Menu
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('f');
		JMenuItem miNew = fileMenu.add(new JMenuItem("New", 'n'));
		miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		miNew.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				tempEmployee = new Employee();
				setSelectedEmployee(tempEmployee);
				setEditable();
			}
		});
		JMenuItem miEdit = fileMenu.add(new JMenuItem("Edit", 'e'));
		miEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
		miEdit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setEditable();
			}
		});
		JMenuItem miRemove = fileMenu.add(new JMenuItem("Remove", 'r'));
		miRemove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));
		miRemove.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(JOptionPane.showConfirmDialog(null, "Are you sure?") == JOptionPane.OK_OPTION) {
					try {
						setUneditable();
						EmployeeDAO.deleteEmployeeById(getSelectedEmployee().getId());
						refreshEmployees(true);
					}
					catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		fileMenu.addSeparator();
		JMenuItem miQuit = fileMenu.add(new JMenuItem("Quit", 'q'));
		miQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.ALT_MASK));
		miQuit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(JOptionPane.showConfirmDialog(null, "Are you sure?") == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
		menuBar.add(fileMenu);
		// Record Menu
		JMenu recordMenu = new JMenu("Record");
		recordMenu.setMnemonic('r');
		JMenuItem miSearch = recordMenu.add(new JMenuItem("Search", 's'));
		miSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		miSearch.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						Search s = new Search();
						s.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						s.setAlwaysOnTop(true);
						s.setVisible(true);
						s.setLocationRelativeTo(null);
					}
				});
			}
		});
		JMenuItem miDisplay = recordMenu.add(new JMenuItem("Display", 'd'));
		miDisplay.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK));
		miDisplay.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setUneditable();
				setSelectedEmployee(getEmployees().get(0));
			}
		});
		menuBar.add(recordMenu);
		setJMenuBar(menuBar);
		// Name
		int row = 0;
		JLabel empNameL = new JLabel("Name:", SwingConstants.RIGHT);
		empNameL.setBounds(xPad, yPad + (labelHeight * row), labelWidth, labelHeight);
		add(empNameL);
		empName = new JTextField();
		empName.setEnabled(editable);
		empName.setBounds(xPad + labelWidth, yPad + (labelHeight * row), 250, labelHeight);
		add(empName);
		// Email
		row++;
		JLabel empEmailL = new JLabel("Email:", SwingConstants.RIGHT);
		empEmailL.setBounds(xPad, yPad + (labelHeight * row), labelWidth, labelHeight);
		add(empEmailL);
		empEmail = new JTextField();
		empEmail.setEnabled(editable);
		empEmail.setBounds(xPad + labelWidth, yPad + (labelHeight * row), 250, labelHeight);
		add(empEmail);
		// Address
		row++;
		JLabel empAddressL = new JLabel("Address:", SwingConstants.RIGHT);
		empAddressL.setBounds(xPad, yPad + (labelHeight * row), labelWidth, labelHeight);
		add(empAddressL);
		empAddress = new JTextField();
		empAddress.setEnabled(editable);
		empAddress.setBounds(xPad + labelWidth, yPad + (labelHeight * row), 250, labelHeight);
		add(empAddress);
		// Postcode
		row++;
		JLabel empPostcodeL = new JLabel("Postcode:", SwingConstants.RIGHT);
		empPostcodeL.setBounds(xPad, yPad + (labelHeight * row), labelWidth, labelHeight);
		add(empPostcodeL);
		empPostcode = new JTextField();
		empPostcode.setEnabled(editable);
		empPostcode.setBounds(xPad + labelWidth, yPad + (labelHeight * row), 250, labelHeight);
		add(empPostcode);
		// Gender
		row++;
		JLabel empGenderL = new JLabel("Gender:", SwingConstants.RIGHT);
		empGenderL.setBounds(xPad, yPad + (labelHeight * row), labelWidth, labelHeight);
		add(empGenderL);
		ButtonGroup empGender = new ButtonGroup();
		empGenderMale = new JRadioButton("Male");
		empGenderMale.setMnemonic('m');
		empGenderMale.setActionCommand("male");
		empGenderMale.setEnabled(editable);
		empGenderMale.setBounds(xPad + labelWidth, yPad + (labelHeight * row), 125, labelHeight);
		empGender.add(empGenderMale);
		add(empGenderMale);
		empGenderFemale = new JRadioButton("Female");
		empGenderFemale.setMnemonic('f');
		empGenderFemale.setActionCommand("female");
		empGenderFemale.setEnabled(editable);
		empGenderFemale.setBounds(xPad + labelWidth + 125, yPad + (labelHeight * row), 125, labelHeight);
		empGender.add(empGenderFemale);
		add(empGenderFemale);
		// Date of Birth
		row++;
		JLabel empDobL = new JLabel("DoB:", SwingConstants.RIGHT);
		empDobL.setBounds(xPad, yPad + (labelHeight * row), labelWidth, labelHeight);
		add(empDobL);
		empDobDay = new JComboBox(days);
		empDobDay.setEnabled(editable);
		empDobDay.setBounds(xPad + labelWidth, yPad + (labelHeight * row), 80, labelHeight);
		add(empDobDay);
		empDobMonth = new JComboBox(months);
		empDobMonth.setEnabled(editable);
		empDobMonth.setBounds(xPad + labelWidth + 80, yPad + (labelHeight * row), 80, labelHeight);
		add(empDobMonth);
		empDobYear = new JComboBox(years);
		empDobYear.setEnabled(editable);
		empDobYear.setBounds(xPad + labelWidth + 160, yPad + (labelHeight * row), 90, labelHeight);
		add(empDobYear);
		// National Insurance Number
		row++;
		JLabel empNatInscNoL = new JLabel("NiN:", SwingConstants.RIGHT);
		empNatInscNoL.setBounds(xPad, yPad + (labelHeight * row), labelWidth, labelHeight);
		add(empNatInscNoL);
		empNatInscNo = new JTextField();
		empNatInscNo.setEnabled(editable);
		empNatInscNo.setBounds(xPad + labelWidth, yPad + (labelHeight * row), 250, labelHeight);
		add(empNatInscNo);
		// Job Title
		row++;
		JLabel empTitleL = new JLabel("Job Title:", SwingConstants.RIGHT);
		empTitleL.setBounds(xPad, yPad + (labelHeight * row), labelWidth, labelHeight);
		add(empTitleL);
		empTitle = new JTextField();
		empTitle.setEnabled(editable);
		empTitle.setBounds(xPad + labelWidth, yPad + (labelHeight * row), 250, labelHeight);
		add(empTitle);
		// Salary
		row++;
		JLabel empSalaryL = new JLabel("Salary:", SwingConstants.RIGHT);
		empSalaryL.setBounds(xPad, yPad + (labelHeight * row), labelWidth, labelHeight);
		add(empSalaryL);
		empSalary = new JTextField();
		empSalary.setEnabled(editable);
		empSalary.setBounds(xPad + labelWidth, yPad + (labelHeight * row), 250, labelHeight);
		add(empSalary);
		// Start Date
		row++;
		JLabel empStartL = new JLabel("Start Date:", SwingConstants.RIGHT);
		empStartL.setBounds(xPad, yPad + (labelHeight * row), labelWidth, labelHeight);
		add(empStartL);
		empStartDay = new JComboBox(days);
		empStartDay.setEnabled(editable);
		empStartDay.setBounds(xPad + labelWidth, yPad + (labelHeight * row), 80, labelHeight);
		add(empStartDay);
		empStartMonth = new JComboBox(months);
		empStartMonth.setEnabled(editable);
		empStartMonth.setBounds(xPad + labelWidth + 80, yPad + (labelHeight * row), 80, labelHeight);
		add(empStartMonth);
		empStartYear = new JComboBox(years);
		empStartYear.setEnabled(editable);
		empStartYear.setBounds(xPad + labelWidth + 160, yPad + (labelHeight * row), 90, labelHeight);
		add(empStartYear);
		// Cancel Button
		row++;
		empCancel = new JButton("Cancel");
		empCancel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setUneditable();
				setSelectedEmployee(employees.get(selectedEmployeeIndex));
			}
		});
		empCancel.setEnabled(editable);
		empCancel.setVisible(editable);
		empCancel.setBounds(xPad, yPad + (labelHeight * row), (labelWidth + 250) / 2, labelHeight);
		add(empCancel);
		// Save Button
		empSave = new JButton("Save");
		empSave.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Input validation, make sure that email, actually is an email etc.
				if(JOptionPane.showConfirmDialog(null, "Are you sure?") == JOptionPane.OK_OPTION) {
					Employee e1 = getSelectedEmployee();
					// Input Validation
					// Name
					if(!empName.getText().isEmpty()) {
						e1.setName(empName.getText());
					}
					else {
						JOptionPane.showMessageDialog(null, "Please fill out all fields correctly.");
						return;
					}
					// Email
					if(!empEmail.getText().isEmpty()) {
						e1.setEmail(empEmail.getText());
					}
					else {
						JOptionPane.showMessageDialog(null, "Please fill out all fields correctly.");
						return;
					}
					// Address
					if(!empAddress.getText().isEmpty()) {
						e1.setAddress(empAddress.getText());
					}
					else {
						JOptionPane.showMessageDialog(null, "Please fill out all fields correctly.");
						return;
					}
					// Postcode
					if(!empPostcode.getText().isEmpty()) {
						e1.setPostcode(empPostcode.getText());
					}
					else {
						JOptionPane.showMessageDialog(null, "Please fill out all fields correctly.");
						return;
					}
					// Gender
					if(empGenderMale.isSelected()) {
						e1.setGender('M');
					}
					else if(empGenderFemale.isSelected()) {
						e1.setGender('F');
					}
					else {
						JOptionPane.showMessageDialog(null, "Please fill out all fields correctly.");
						return;
					}
					// Date of Birth
					String dayOfBirth = empDobDay.getSelectedItem().toString();
					if(dayOfBirth.length() == 1) {
						dayOfBirth = "0" + dayOfBirth;
					}
					String monthOfBirth = String.valueOf(empDobMonth.getSelectedIndex());
					if(monthOfBirth.length() == 1) {
						monthOfBirth = "0" + monthOfBirth;
					}
					String yearOfBirth = empDobYear.getSelectedItem().toString();
					e1.setDob(dayOfBirth + "-" + monthOfBirth + "-" + yearOfBirth);
					// National Insurance Number
					if(!empNatInscNo.getText().isEmpty()) {
						e1.setNatInscNo(empNatInscNo.getText());
					}
					else {
						JOptionPane.showMessageDialog(null, "Please fill out all fields correctly.");
						return;
					}
					// Job Title
					if(!empTitle.getText().isEmpty()) {
						e1.setTitle(empTitle.getText());
					}
					else {
						JOptionPane.showMessageDialog(null, "Please fill out all fields correctly.");
						return;
					}
					// Salary
					if(!empSalary.getText().isEmpty()) {
						e1.setSalary(empSalary.getText());
					}
					else {
						JOptionPane.showMessageDialog(null, "Please fill out all fields correctly.");
						return;
					}
					// Start Date
					String dayOfStart = empStartDay.getSelectedItem().toString();
					if(dayOfStart.length() == 1) {
						dayOfStart = "0" + dayOfStart;
					}
					String monthOfStart = String.valueOf(empStartMonth.getSelectedIndex());
					if(monthOfStart.length() == 1) {
						monthOfStart = "0" + monthOfStart;
					}
					String yearOfStart = empStartYear.getSelectedItem().toString();
					e1.setStartDate(dayOfStart + "-" + monthOfStart + "-" + yearOfStart);
					if(tempEmployee.getImage() != null) {
						e1.setImage(tempEmployee.getImage());
					}
					else {
						JOptionPane.showMessageDialog(null, "Please add an image.");
						return;
					}
					try {
						if(e1.getId() == null) {
							EmployeeDAO.insertEmployee(e1);
						}
						else {
							EmployeeDAO.updateEmployee(e1);
						}
						refreshEmployees(false);
					}
					catch(SQLException e2) {
						e2.printStackTrace();
					}
					setUneditable();
					if(e1.getId() == null) {
						setSelectedEmployee(employees.get(getEmployeeCount() - 1));
					}
					else {
						setSelectedEmployee(employees.get(selectedEmployeeIndex));
					}
				}
			}
		});
		empSave.setEnabled(editable);
		empSave.setVisible(editable);
		empSave.setBounds(xPad + ((labelWidth + 250) / 2), yPad + (labelHeight * row), (labelWidth + 250) / 2, labelHeight);
		add(empSave);
		// Previous Button
		prevEmp = new JButton("Previous");
		prevEmp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				loadPrevEmployee();
			}
		});
		prevEmp.setEnabled(!editable);
		prevEmp.setVisible(!editable);
		prevEmp.setBounds(xPad, yPad + (labelHeight * row), (labelWidth + 250 + maxImageWidth) / 2, labelHeight);
		add(prevEmp);
		// Next Button
		nextEmp = new JButton("Next");
		nextEmp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				loadNextEmployee();
			}
		});
		nextEmp.setEnabled(!editable);
		nextEmp.setVisible(!editable);
		nextEmp.setBounds(xPad + (labelWidth + 250 + maxImageWidth) / 2, yPad + (labelHeight * row), (labelWidth + 250 + maxImageWidth) / 2, labelHeight);
		add(nextEmp);
		// Employee Count
		empCount = new JLabel("Employee Count: " + getEmployeeCount(), SwingConstants.CENTER);
		empCount.setBounds(xPad + labelWidth + 250, yPad, maxImageWidth, labelHeight);
		add(empCount);
		// Image
		empImage = new JLabel("", SwingConstants.CENTER);
		empImage.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(editable) {
					FileDialog fileDialog = new FileDialog(MainForm.this, "Select Image", FileDialog.LOAD);
					fileDialog.setFilenameFilter(new FilenameFilter()
					{
						@Override
						public boolean accept(File dir, String name)
						{
							String[] acceptedFiletypes = {"jpeg", "jpg", "png"};
							for(String ext : acceptedFiletypes) {
								if(name.endsWith("." + ext)) {
									return true;
								}
							}
							return false;
						}
					});
					fileDialog.setVisible(true);
					String fileDirectory = fileDialog.getDirectory();
					String fileName = fileDialog.getFile();
					if(fileDirectory != null && fileName != null) {
						try {
							tempEmployee.setImage(ImageIO.read(new File(fileDirectory + fileName)));
						}
						catch(IOException e1) {
							JOptionPane.showMessageDialog(null, "Cannot load image into application.", "Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						empImage.setIcon(getScaledIcon(tempEmployee.getImage()));
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		empImage.setCursor(Cursor.getDefaultCursor());
		empImage.setBounds(xPad + labelWidth + 250, yPad + labelHeight, maxImageWidth, maxImageHeight);
		add(empImage);
		loadEmployee();
	}
	/**
	 *
	 */
	private static void loadEmployee()
	{
		try {
			Employee e = getSelectedEmployee();
			if(e.getName() != null) {
				empName.setText(e.getName());
			}
			else {
				empName.setText("");
			}
			if(e.getEmail() != null) {
				empEmail.setText(e.getEmail());
			}
			else {
				empEmail.setText("");
			}
			if(e.getAddress() != null) {
				empAddress.setText(e.getAddress());
			}
			else {
				empAddress.setText("");
			}
			if(e.getPostcode() != null) {
				empPostcode.setText(e.getPostcode());
			}
			else {
				empPostcode.setText("");
			}
			switch(e.getGender()) {
				case 'M':
					empGenderMale.setSelected(true);
					break;
				case 'F':
					empGenderFemale.setSelected(true);
					break;
				default:
					empGenderMale.setSelected(false);
					empGenderFemale.setSelected(false);
					break;
			}
			if(e.getDob() != null) {
				empDobDay.setSelectedIndex(Integer.parseInt(e.getDob().split("-")[0]) - 1);
				empDobMonth.setSelectedIndex(Integer.parseInt(e.getDob().split("-")[1]) - 1);
				empDobYear.setSelectedIndex(Integer.parseInt(e.getDob().split("-")[2]) - 1900);
			}
			else {
				empDobDay.setSelectedIndex(0);
				empDobMonth.setSelectedIndex(0);
				empDobYear.setSelectedIndex(0);
			}
			if(e.getNatInscNo() != null) {
				empNatInscNo.setText(e.getNatInscNo());
			}
			else {
				empNatInscNo.setText("");
			}
			if(e.getTitle() != null) {
				empTitle.setText(e.getTitle());
			}
			else {
				empTitle.setText("");
			}
			if(e.getSalary() != null) {
				empSalary.setText(e.getSalary());
			}
			else {
				empSalary.setText("");
			}
			if(e.getStartDate() != null) {
				empStartDay.setSelectedIndex(Integer.parseInt(e.getStartDate().split("-")[0]) - 1);
				empStartMonth.setSelectedIndex(Integer.parseInt(e.getStartDate().split("-")[1]) - 1);
				empStartYear.setSelectedIndex(Integer.parseInt(e.getStartDate().split("-")[2]) - 1900);
			}
			else {
				empStartDay.setSelectedIndex(0);
				empStartMonth.setSelectedIndex(0);
				empStartYear.setSelectedIndex(0);
			}
			empCount.setText("Employee Count: " + getEmployeeCount());
			Image image;
			if(e.getImage() != null) {
				image = e.getImage();
			}
			else {
				image = ImageIO.read(new File("res/noImg.png"));
			}
			empImage.setIcon(getScaledIcon(image));
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Cannot load employee into application.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	/**
	 *
	 */
	private static void loadNextEmployee()
	{
		selectedEmployeeIndex++;
		if(selectedEmployeeIndex > getEmployeeCount() - 1) {
			selectedEmployeeIndex = 0;
		}
		setSelectedEmployee(employees.get(selectedEmployeeIndex));
		loadEmployee();
	}
	/**
	 *
	 */
	private static void loadPrevEmployee()
	{
		selectedEmployeeIndex--;
		if(selectedEmployeeIndex < 0) {
			selectedEmployeeIndex = getEmployeeCount() - 1;
		}
		setSelectedEmployee(employees.get(selectedEmployeeIndex));
		loadEmployee();
	}
	/**
	 * @param reloadSelectedEmployee
	 *
	 * @throws SQLException
	 */
	private static void refreshEmployees(boolean reloadSelectedEmployee) throws SQLException
	{
		employees = EmployeeDAO.selectAllEmployees();
		if(reloadSelectedEmployee) {
			selectedEmployeeIndex = 0;
			setSelectedEmployee(employees.get(selectedEmployeeIndex));
		}
	}
	/**
	 * @return
	 */
	static ArrayList<Employee> getEmployees()
	{
		return employees;
	}
	/**
	 * @return
	 */
	static int getEmployeeCount()
	{
		return employees.size();
	}
	/**
	 * @return
	 */
	static Employee getSelectedEmployee()
	{
		return selectedEmployee;
	}
	/**
	 * @param employee
	 */
	static void setSelectedEmployee(Employee employee)
	{
		selectedEmployee = employee;
		loadEmployee();
	}
	/**
	 * @param index
	 */
	static void setSelectedEmployeeIndex(int index)
	{
		selectedEmployeeIndex = index;
		setSelectedEmployee(getEmployees().get(selectedEmployeeIndex));
	}
	/**
	 *
	 */
	static void setEditable()
	{
		editable = true;
		tempEmployee = new Employee(selectedEmployee);
		selectedEmployee = tempEmployee;
		prevEmp.setBounds(xPad, yPad + (labelHeight * 11), (labelWidth + 250 + maxImageWidth) / 2, labelHeight);
		nextEmp.setBounds(xPad + (labelWidth + 250 + maxImageWidth) / 2, yPad + (labelHeight * 11), (labelWidth + 250 + maxImageWidth) / 2, labelHeight);
		empImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		empImage.setBounds(xPad + labelWidth + 250, yPad + labelHeight, maxImageWidth, maxImageHeight + labelHeight);
		refreshEditState();
	}
	/**
	 *
	 */
	static void setUneditable()
	{
		editable = false;
		tempEmployee = null;
		prevEmp.setBounds(xPad, yPad + (labelHeight * 10), (labelWidth + 250 + maxImageWidth) / 2, labelHeight);
		nextEmp.setBounds(xPad + (labelWidth + 250 + maxImageWidth) / 2, yPad + (labelHeight * 10), (labelWidth + 250 + maxImageWidth) / 2, labelHeight);
		empImage.setCursor(Cursor.getDefaultCursor());
		empImage.setBounds(xPad + labelWidth + 250, yPad + labelHeight, maxImageWidth, maxImageHeight);
		refreshEditState();
	}
	/**
	 *
	 */
	private static void refreshEditState()
	{
		empName.setEnabled(editable);
		empEmail.setEnabled(editable);
		empAddress.setEnabled(editable);
		empPostcode.setEnabled(editable);
		empGenderMale.setEnabled(editable);
		empGenderFemale.setEnabled(editable);
		empDobDay.setEnabled(editable);
		empDobMonth.setEnabled(editable);
		empDobYear.setEnabled(editable);
		empNatInscNo.setEnabled(editable);
		empTitle.setEnabled(editable);
		empSalary.setEnabled(editable);
		empStartDay.setEnabled(editable);
		empStartMonth.setEnabled(editable);
		empStartYear.setEnabled(editable);
		empCancel.setEnabled(editable);
		empCancel.setVisible(editable);
		empSave.setEnabled(editable);
		empSave.setVisible(editable);
		prevEmp.setEnabled(!editable);
		prevEmp.setVisible(!editable);
		nextEmp.setEnabled(!editable);
		nextEmp.setVisible(!editable);
	}
	/**
	 * @param image
	 *
	 * @return
	 */
	private static ImageIcon getScaledIcon(Image image)
	{
		ImageIcon icon = new ImageIcon(image);
		int imgWidth = icon.getIconWidth();
		int imgHeight = icon.getIconHeight();
		double aspectRatio = (double) imgWidth / (double) imgHeight;
		double maxAspectRatio = (double) maxImageWidth / (double) maxImageHeight;
		int w, h;
		if(maxAspectRatio < aspectRatio) {
			w = maxImageWidth;
			h = (int) ((double) maxImageWidth / aspectRatio);
		}
		else {
			w = (int) ((double) maxImageHeight * aspectRatio);
			h = maxImageHeight;
		}
		image = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
}