import java.sql.*;
public class Controller
{
	/**
	 * @author Elliot Lewis
	 */
	public static void main(String[] args)
	{
		EmployeeDAO edao = new EmployeeDAO();
		//Statement s = edao.getConnection();
		try {
			for(Employee e: edao.selectAllEmployees()) {
				System.out.println(e.getName());
			}
			System.out.println(edao.selectEmployeeByName("Elliot Lewis").getPostcode());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}