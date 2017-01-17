package ex2;
import java.sql.*;
public class Controller
{
	public static Connection getDBConnection()
	{
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		}
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = "jdbc:sqlite:res/shopdb.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
	public static void showAllCustomers() throws Exception
	{
		Connection conn = getDBConnection();
		Statement statement = null;
		ResultSet results = null;
		String query = "SELECT * FROM customers;";
		try {
			statement = conn.createStatement();
			results = statement.executeQuery(query);
			while(results.next()) {
				System.out.println(results.getString("CustomerID") + " " + results.getString("CustomerName") + " "
						+ results.getString("CustomerEmail"));
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(results != null) {
				results.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	public static void showAllOrders() throws Exception
	{
		Connection conn = getDBConnection();
		Statement statement = null;
		ResultSet results = null;
		String query = "SELECT * FROM orders;";
		try {
			statement = conn.createStatement();
			results = statement.executeQuery(query);
			while(results.next()) {
				System.out.println(results.getString("OrderID") + " " + results.getString("ProductID") + " "
						+ results.getString("CustomerID") + " " + results.getString("OrderDate"));
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(results != null) {
				results.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	public static void showOrdersForCustomer(int i) throws Exception
	{
		Connection conn = getDBConnection();
		PreparedStatement statement = null;
		ResultSet results = null;
		String query = "SELECT * FROM orders INNER JOIN customers ON orders.CustomerID = customers.CustomerID WHERE customers.CustomerID = ?;";
		try {
			statement = conn.prepareStatement(query);
			statement.setInt(1, i);
			results = statement.executeQuery();
			while(results.next()) {
				System.out.println(results.getString("CustomerName") + " " + results.getString("ProductID") + " "
						+ results.getString("OrderDate"));
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(results != null) {
				results.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	public static void showProductOrdersList() throws Exception
	{
		Connection conn = getDBConnection();
		Statement statement = null;
		ResultSet results = null;
		String query = "SELECT * FROM orders INNER JOIN customers ON orders.CustomerID = customers.CustomerID;";
		try {
			statement = conn.createStatement();
			results = statement.executeQuery(query);
			while(results.next()) {
				System.out.println(results.getString("ProductID") + " " + results.getString("CustomerName") + " "
						+ results.getString("OrderDate"));
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(results != null) {
				results.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	public static void main(String[] args) throws Exception
	{
		Controller.showProductOrdersList();
	}
}