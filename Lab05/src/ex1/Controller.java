package ex1;
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
			String dbURL = "jdbc:sqlite:res/dvd.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
	public static void showAllRecords() throws SQLException
	{
		Connection conn = getDBConnection();
		Statement statement = null;
		ResultSet results = null;
		String query = "SELECT * FROM collection;";
		try {
			statement = conn.createStatement();
			results = statement.executeQuery(query);
			while(results.next()) {
				System.out.println(results.getString("ID") + " " + results.getString("Title") + " "
						+ results.getString("Genre") + " " + results.getString("Year"));
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
	public static void insertRecordIntoCollectionTable() throws Exception
	{
		Connection conn = getDBConnection();
		String query = "INSERT INTO collection (Title, Genre, Year) VALUES (?, ?, ?);";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, "Divergent");
		statement.setString(2, "Sci Fi");
		statement.setInt(3, 2014);
		statement.execute();
	}
	public static void main(String[] args) throws Exception
	{
		Controller.insertRecordIntoCollectionTable();
	}
}