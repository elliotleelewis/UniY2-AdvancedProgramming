package ex3;
import java.sql.*;
import java.util.ArrayList;
/**
 * Copyright Elliot Lewis © 2017
 */
class ContactDAO
{
	private static Connection c;
	ContactDAO() throws SQLException
	{
		c = DriverManager.getConnection("jdbc:sqlite:res/contactsdb.sqlite");
		// Reset DB back to sample data on launch.
		c.prepareStatement("DELETE FROM contacts WHERE ID>4").executeUpdate();
		c.prepareStatement("UPDATE sqlite_sequence SET seq=4 WHERE name='contacts'").executeUpdate();
	}
	static void closeConnection() throws SQLException
	{
		c.close();
	}
	static ArrayList<Contact> selectAllContacts() throws SQLException
	{
		ArrayList<Contact> contacts = new ArrayList<>();
		PreparedStatement statement = c.prepareStatement("SELECT * FROM contacts");
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			contacts.add(convertContact(resultSet));
		}
		return contacts;
	}
	static boolean insertContact(Contact contact) throws SQLException
	{
		PreparedStatement statement = c.prepareStatement("INSERT INTO contacts (Name, Email) VALUES (?, ?)");
		statement.setString(1, contact.getName());
		statement.setString(2, contact.getEmail());
		return statement.executeUpdate() > 0;
	}
	private static Contact convertContact(ResultSet resultSet) throws SQLException
	{
		String name = resultSet.getString("Name");
		String email = resultSet.getString("Email");
		return new Contact(name, email);
	}
}